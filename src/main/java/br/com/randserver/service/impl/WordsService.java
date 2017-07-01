package br.com.randserver.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.randserver.dto.WordsDTO;
import br.com.randserver.repository.WordsRepository;
import br.com.randserver.service.IClassifierWords;
import br.com.randserver.service.IWords;

@Service
public class WordsService implements IWords {

	@Autowired
	private IClassifierWords iClassifierWords;

	@Autowired
	private WordsRepository wordsRepository;

	@Override
	public List<WordsDTO> generateSentence(int numberOfWords) {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(WordsService.class.getResourceAsStream("/dictionary.txt")));

		StringBuilder phrase = new StringBuilder();
		try {
			String strLine;
			List<String> filearray = new ArrayList<String>();

			while ((strLine = br.readLine()) != null) {
				filearray.add(strLine);
			}

			for (int i = 0; i < numberOfWords; i++) {
				Random rn = new Random();
				int randomWord = rn.nextInt(filearray.size());
				phrase.append(String.format("%s ", filearray.get(randomWord)));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			return iClassifierWords.get(phrase.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Collections.emptyList();
	}

	@Override
	@Transactional(readOnly = true)
	public List<WordsDTO> listAll() {
		List<WordsDTO> list = new ArrayList<>();
		wordsRepository.findAllByOrderByIdAsc().forEach((word) -> list.add(new WordsDTO(word)));
		return list;
	}

}
