package br.com.randserver.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.randserver.BaseController;
import br.com.randserver.Response;
import br.com.randserver.dto.WordsDTO;
import br.com.randserver.service.IWords;

@Controller
@RequestMapping("words")
public class WordsController extends BaseController<WordsDTO> {

	@Autowired
	private IWords iWords;

	@RequestMapping(path = "/generateSentence", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Async
	public CompletableFuture<List<WordsDTO>> generateSentence(int numberOfWords) {

		try {

			List<WordsDTO> words = iWords.generateSentence(numberOfWords);
			return CompletableFuture.completedFuture(words);
		} catch (Exception e) {
			return null;
		}
	}

	@RequestMapping(path = "/listAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<WordsDTO> listAll() {

		try {
			List<WordsDTO> words = iWords.listAll();
			return words;
		} catch (Exception e) {
			return null;
		}
	}

}
