package com.infy.entity;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//Mention schema name along with table name by refering  the Demo-CRUD operations using PostgreSQL
@Entity
@Table(name = "Book", schema = "db_infybook")
public class Book {
	@Id
	private Integer bookId;
	private String title;
	private String authorName;
	private LocalDate publishedYear;
	private String publisher;
	private Long isbn;
	private Integer price;

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public LocalDate getPublishedYear() {
		return publishedYear;
	}

	public void setPublishedYear(LocalDate publishedYear) {
		this.publishedYear = publishedYear;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Long getIsbn() {
		return isbn;
	}

	public void setIsbn(Long isbn) {
		this.isbn = isbn;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.getBookId() == null) ? 0 : this.getBookId().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (this.getBookId() == null) {
			if (other.getBookId() != null) {
				return false;
			} else if (!this.getBookId().equals(other.getBookId())) {
				return false;
			}
		}
		return true;
	}

	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", title=" + title + ", authorName=" + authorName + ", publishedYear="
				+ publishedYear + ", publisher=" + publisher + ", isbn=" + isbn + ", price=" + price + "]";
	}

}
