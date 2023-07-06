package com.example.LMS.boundary;

import static com.example.LMS.utils.TestUtils.boundaryTestFile;
import static com.example.LMS.utils.TestUtils.currentTest;
import static com.example.LMS.utils.TestUtils.testReport;
import static com.example.LMS.utils.TestUtils.yakshaAssert;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.LMS.dto.LMSDTO;
import com.example.LMS.utils.MasterData;

@ExtendWith(SpringExtension.class)
public class BoundaryTest {

	private static Validator validator;

	@BeforeAll
	public static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testTitleNotNull() throws Exception {
		LMSDTO lmsDTO = MasterData.getLMSDto();
		lmsDTO.setTitle(null);
		Set<ConstraintViolation<LMSDTO>> violations = validator.validate(lmsDTO);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testTitleMaxHundred() throws Exception {
		LMSDTO lmsDTO = MasterData.getLMSDto();
		lmsDTO.setTitle(MasterData.withSizeRandomString(101));
		Set<ConstraintViolation<LMSDTO>> violations = validator.validate(lmsDTO);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testTitleNotBlank() throws Exception {
		LMSDTO lmsDTO = MasterData.getLMSDto();
		lmsDTO.setTitle("");
		Set<ConstraintViolation<LMSDTO>> violations = validator.validate(lmsDTO);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testDescriptionNotNull() throws Exception {
		LMSDTO lmsDTO = MasterData.getLMSDto();
		lmsDTO.setDescription(null);
		Set<ConstraintViolation<LMSDTO>> violations = validator.validate(lmsDTO);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testInstructorNotNull() throws Exception {
		LMSDTO lmsDTO = MasterData.getLMSDto();
		lmsDTO.setInstructor(null);
		Set<ConstraintViolation<LMSDTO>> violations = validator.validate(lmsDTO);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testDurationNotNull() throws Exception {
		LMSDTO lmsDTO = MasterData.getLMSDto();
		lmsDTO.setDuration(null);
		Set<ConstraintViolation<LMSDTO>> violations = validator.validate(lmsDTO);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testStartDateNotNull() throws Exception {
		LMSDTO lmsDTO = MasterData.getLMSDto();
		lmsDTO.setStartDate(null);
		Set<ConstraintViolation<LMSDTO>> violations = validator.validate(lmsDTO);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

}
