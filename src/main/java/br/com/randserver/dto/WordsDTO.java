package br.com.randserver.dto;

import br.com.randserver.BaseDTO;
import br.com.randserver.entity.Words;

@SuppressWarnings("serial")
public class WordsDTO implements BaseDTO {

	private Long id;

	private String word;

	private String type;

	private String entity;

	public WordsDTO(String word, String type) {
		super();
		this.word = word;
		this.type = type;
	}

	public WordsDTO(Long id, String word, String type, String entity) {
		super();
		this.id = id;
		this.word = word;
		this.type = type;
		this.entity = entity;
	}

	public WordsDTO(Words word) {
		this(word.getId(), word.getWord(), word.getType(), word.getEntity());
	}

	public String getWord() {
		return word;
	}

	public String getType() {
		return type;
	}

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public Long getId() {
		return id;
	}

}
