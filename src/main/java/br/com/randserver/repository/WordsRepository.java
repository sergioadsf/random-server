package br.com.randserver.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.randserver.entity.Words;

public interface WordsRepository extends CrudRepository<Words, Long> {

	List<Words> findAllByOrderByIdAsc();
}
