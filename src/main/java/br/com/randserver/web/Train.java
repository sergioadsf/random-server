package br.com.randserver.web;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;

public class Train {

	private static Train TRAIN = null;
	private MaxentTagger tagger;

	private Train() {
		tagger = new MaxentTagger(TrainListener.class.getResourceAsStream("/models/english-left3words-distsim.tagger"));
	}

	public static Train getInstance() {
		if (TRAIN == null) {
			TRAIN = new Train();
		}
		
		return TRAIN;
	}

	public MaxentTagger getTagger() {
		return tagger;
	}

}
