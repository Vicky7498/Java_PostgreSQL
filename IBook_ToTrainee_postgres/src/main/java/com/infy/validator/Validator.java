package com.infy.validator;

import java.time.LocalDate;

import com.infy.dto.BookDTO;
import com.infy.exception.InfyBookException;

public class Validator {

	public static void validate(BookDTO bookDTO) throws InfyBookException {
		if (!validateYear(bookDTO.getPublishedYear())) {
			throw new InfyBookException("Validator.INVALID_YEAR");
		}
	}

	public static boolean validateYear(LocalDate year) {
		if (LocalDate.now().isBefore(year)) {
			return false;
		} else {
			return true;
		}
	}

}
