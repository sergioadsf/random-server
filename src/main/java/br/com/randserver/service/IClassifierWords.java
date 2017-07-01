package br.com.randserver.service;

import java.util.List;

import br.com.randserver.dto.WordsDTO;

public interface IClassifierWords {

	List<WordsDTO> get(String words) throws Exception;

}
