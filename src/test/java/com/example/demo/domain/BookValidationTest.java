package com.example.demo.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

public class BookValidationTest {
	private static Validator validator;

	@BeforeAll
	static void setup() {
		validator = Validation.buildDefaultValidatorFactory().getValidator();
	}

	@Test
	void testForAllValidFields() {
		Set<ConstraintViolation<Book>> violations = validator.validate(new Book("1234567890", "Title", "Author", 9.90));
		assertThat(violations).isEmpty();
	}
	
	@Test
	void testForInvalidFields() {
		Set<ConstraintViolation<Book>> violations = validator.validate(new Book("123456789a", "Title", "Author", 9.90));
		assertThat(violations).hasSize(1);
		assertThat(violations.iterator().next().getMessage()).isEqualTo("The ISBN format must be valid.");
	}

}
