package br.com.randserver.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.randserver.BaseController;
import br.com.randserver.dto.WordsDTO;
import br.com.randserver.exception.ServerException;
import br.com.randserver.service.IWords;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("words")
public class WordsController extends BaseController<WordsDTO> {

	@Autowired
	private IWords iWords;

	@Async
	@GetMapping(path = "/generateSentence", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public CompletableFuture<List<WordsDTO>> generateSentence(int numberOfWords) throws ServerException {

		try {

			List<WordsDTO> words = iWords.generateSentence(numberOfWords);
			return CompletableFuture.completedFuture(words);
		} catch (Exception e) {
			throw new ServerException("An error ocurred, and we were unable to coninue.");
		}
	}

	@Async
	@GetMapping(path = "/listAll", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public CompletableFuture<List<WordsDTO>> listAll() throws ServerException {

		try {
			List<WordsDTO> words = iWords.listAll();
			return CompletableFuture.completedFuture(words);
		} catch (Exception e) {
			throw new ServerException("An error ocurred, and we were unable to coninue.");
		}
	}
}
