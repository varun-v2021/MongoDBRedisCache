package com.example.onlineservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebServicesController {
	@Autowired
	BookRepository repository;
	@Autowired
	MongoTemplate mongoTemplate;

	@RequestMapping(value = "/book", method = RequestMethod.POST)
	public Book saveBook(Book book) {
		return repository.save(book);
	}

	@RequestMapping(value = "/book/{title}", method = RequestMethod.GET)
	@Cacheable("Book") //Redis Server will log as a client connected because of this
	public Book findBookByTitle(@PathVariable String title) {
		Book insertedBook = repository.findByTitle(title);
		return insertedBook;
	}
}
