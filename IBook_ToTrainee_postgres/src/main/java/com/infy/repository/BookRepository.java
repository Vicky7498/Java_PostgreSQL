package com.infy.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.infy.entity.Book;
import com.infy.exception.InfyBookException;

public interface BookRepository extends CrudRepository<Book, Integer> {
	public List<Book> getBookByAuthorName(String authorName) throws InfyBookException;

	public List<Book> getBookGreaterThanEqualToPrice(Integer price) throws InfyBookException;

	public List<Book> getBookLessThanPrice(Integer price) throws InfyBookException;

	public List<Book> bookPublishedBetweenYear(LocalDate stratYear, LocalDate endYear) throws InfyBookException;

	public List<Book> bookPublishedAfterYear(LocalDate year) throws InfyBookException;

	public List<Book> getBookByAuthorNameAndPublisher(String authorName, String publisher) throws InfyBookException;
}