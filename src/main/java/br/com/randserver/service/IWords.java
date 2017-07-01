package br.com.randserver.service;

import java.util.List;

import br.com.randserver.dto.WordsDTO;

public interface IWords {

	List<WordsDTO> generateSentence(int numberOfWords);

	List<WordsDTO> listAll();
}
