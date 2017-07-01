package br.com.randserver.service.impl;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.randserver.dto.WordsDTO;
import br.com.randserver.service.IClassifierWords;
import br.com.randserver.web.Train;
import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.ling.SentenceUtils;
import edu.stanford.nlp.ling.TaggedWord;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;

@Service
public class ClassifierWords implements IClassifierWords {

	static String serializedClassifier = "classifiers/english.all.3class.distsim.crf.ser.gz";

	static AbstractSequenceClassifier<CoreLabel> classifier = CRFClassifier
			.getClassifierNoExceptions(serializedClassifier);

	public List<WordsDTO> get(String words) throws Exception {

		if (words == null || words.equals("")) {
			return new ArrayList<>();
		}

		List<WordsDTO> listWords = new ArrayList<>();
		WordsDTO word = null;
		List<List<HasWord>> sentences = MaxentTagger.tokenizeText(new StringReader(words));
		for (List<HasWord> sentence : sentences) {
			List<TaggedWord> tSentence = Train.getInstance().getTagger().tagSentence(sentence);
			String valuesStr = SentenceUtils.listToString(tSentence, false);
			if (valuesStr != null) {
				String[] values = valuesStr.split(" ");
				for (String valueStr : values) {
					String[] value = valueStr.split("/");
					word = new WordsDTO(value[0], value[1]);
					if (word.getType().equals("NNP")) {
						classifierNER(word);
					}
					listWords.add(word);
				}
			}
		}

		return listWords;
	}

	private void classifierNER(WordsDTO word) throws IOException {
		word.setEntity(classifier.classifyToString(word.getWord()).split("/")[1]);
	}
}