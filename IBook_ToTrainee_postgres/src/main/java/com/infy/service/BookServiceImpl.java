package com.infy.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.infy.dto.BookDTO;
import com.infy.entity.Book;
import com.infy.exception.InfyBookException;
import com.infy.repository.BookRepository;
import com.infy.validator.Validator;
import jakarta.transaction.Transactional;

@Service(value = "bookService")
@Transactional
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;

	@Override
	public BookDTO getBookDetails(Integer bookId) throws InfyBookException {
		Optional<Book> optional = bookRepository.findById(bookId);
		Book book = optional.orElseThrow(() -> new InfyBookException("Service.BOOK_DETAILS_NOT_FOUND"));
		BookDTO bookDto = new BookDTO();
		bookDto.setBookId(book.getBookId());
		bookDto.setAuthorName(book.getAuthorName());
		bookDto.setIsbn(book.getIsbn());
		bookDto.setPrice(book.getPrice());
		bookDto.setPublishedYear(book.getPublishedYear());
		bookDto.setPublisher(book.getPublisher());
		bookDto.setTitle(book.getTitle());
		return bookDto;
	}

	@Override
	public void addBook(BookDTO bookDTO) throws InfyBookException {
		try {
			Validator.validate(bookDTO);
		} catch (InfyBookException exception) {
			throw new InfyBookException(exception.getMessage());
		}
		Optional<Book> optional = bookRepository.findById(bookDTO.getBookId());
		if (optional.isPresent()) {
			throw new InfyBookException("Service.BOOK_ALREADY_PRESENT");
		}
		Book book = new Book();
		book.setAuthorName(bookDTO.getAuthorName());
		book.setBookId(bookDTO.getBookId());
		book.setIsbn(bookDTO.getIsbn());
		book.setPrice(bookDTO.getPrice());
		book.setPublishedYear(bookDTO.getPublishedYear());
		book.setPublisher(bookDTO.getPublisher());
		book.setTitle(bookDTO.getTitle());
		bookRepository.save(book);
		// return book.getTitle();
	}

	@Override
	public List<BookDTO> getBookByAuthorName(String authorName) throws InfyBookException {
		List<Book> books = bookRepository.getBookByAuthorName(authorName);
		if (books == null || books.isEmpty()) {
			throw new InfyBookException("Service.BOOK_NOT_FOUND");
		}
		List<BookDTO> bookDtos = booksDetails(books);
		return bookDtos;
	}

	@Override
	public List<BookDTO> getBookGreaterThanEqualToPrice(Integer price) throws InfyBookException {
		List<Book> books = bookRepository.getBookGreaterThanEqualToPrice(price);
		if (books == null || books.isEmpty()) {
			throw new InfyBookException("Service.BOOK_NOT_FOUND");
		}
		List<BookDTO> bookDtos = booksDetails(books);
		return bookDtos;
	}

	@Override
	public List<BookDTO> getBookLessThanPrice(Integer price) throws InfyBookException {
		List<Book> books = bookRepository.getBookLessThanPrice(price);
		if (books == null || books.isEmpty()) {
			throw new InfyBookException("Service.BOOK_NOT_FOUND");
		}
		List<BookDTO> bookDtos = booksDetails(books);
		return bookDtos;
	}

	@Override
	public List<BookDTO> bookPublishedBetweenYear(LocalDate startYear, LocalDate endYear) throws InfyBookException {
		List<Book> books = bookRepository.bookPublishedBetweenYear(startYear, endYear);
		if (books == null || books.isEmpty()) {
			throw new InfyBookException("Service.BOOK_NOT_FOUND");
		}
		List<BookDTO> bookDtos = booksDetails(books);
		return bookDtos;
	}

	@Override
	public List<BookDTO> bookPublishedAfterYear(LocalDate year) throws InfyBookException {
		List<Book> books = bookRepository.bookPublishedAfterYear(year);
		if (books == null || books.isEmpty()) {
			throw new InfyBookException("Service.BOOK_NOT_FOUND");
		}
		List<BookDTO> bookDtos = booksDetails(books);
		return bookDtos;
	}

	@Override
	public List<BookDTO> getBookByAuthorNameAndPublisher(String authorName, String publisher) throws InfyBookException {
		List<Book> books = bookRepository.getBookByAuthorNameAndPublisher(authorName, publisher);
		if (books == null || books.isEmpty()) {
			throw new InfyBookException("Service.BOOK_NOT_FOUND");
		}
		List<BookDTO> bookDtos = booksDetails(books);
		return bookDtos;
	}

	@Override
	public void updateBookPrice(Integer bookId, Integer price) throws InfyBookException {
		Optional<Book> optional = bookRepository.findById(bookId);
		Book book = optional.orElseThrow(() -> new InfyBookException("Service.BOOK_NOT_FOUND"));
		book.setPrice(price);
	}

	@Override
	public void deleteBook(Integer bookId) throws InfyBookException {
		Optional<Book> optional = bookRepository.findById(bookId);
		Book book = optional.orElseThrow(() -> new InfyBookException("Service.BOOK_NOT_FOUND"));
		bookRepository.deleteById(book.getBookId());
	}

	public List<BookDTO> booksDetails(List<Book> books) {
		List<BookDTO> bookDtos = new ArrayList<BookDTO>();
		books.forEach(book -> {
			BookDTO bookDto = new BookDTO();
			bookDto.setAuthorName(book.getAuthorName());
			bookDto.setBookId(book.getBookId());
			bookDto.setIsbn(book.getIsbn());
			bookDto.setPrice(book.getPrice());
			bookDto.setPublishedYear(book.getPublishedYear());
			bookDto.setPublisher(book.getPublisher());
			bookDto.setTitle(book.getTitle());
			bookDtos.add(bookDto);
		});
		return bookDtos;
	}
}
