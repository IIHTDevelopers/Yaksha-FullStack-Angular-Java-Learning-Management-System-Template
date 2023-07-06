package com.example.LMS.exception;

import static com.example.LMS.utils.TestUtils.currentTest;
import static com.example.LMS.utils.TestUtils.exceptionTestFile;
import static com.example.LMS.utils.TestUtils.testReport;
import static com.example.LMS.utils.TestUtils.yakshaAssert;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.LMS.controller.LMSController;
import com.example.LMS.dto.LMSDTO;
import com.example.LMS.service.LMSService;
import com.example.LMS.utils.MasterData;

@WebMvcTest(LMSController.class)
@AutoConfigureMockMvc
public class ExceptionTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private LMSService lmsService;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testCreateUserInvalidDataException() throws Exception {
		LMSDTO lmsDTO = MasterData.getLMSDto();
		lmsDTO.setTitle(null);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/lms").content(MasterData.asJsonString(lmsDTO))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"),
				exceptionTestFile);

	}

	@Test
	public void testCreateUserWithStartDateAfterEndDateCustomException() throws Exception {
		LMSDTO lmsDTO = MasterData.getLMSDto();
		// start date is after 10days from today's date
		lmsDTO.setStartDate(LocalDate.now().plusDays(10));
		// end date is today's date
		lmsDTO.setEndDate(LocalDate.now());

		RestExceptionHandler.ErrorResponse errorResponse = new RestExceptionHandler.ErrorResponse(
				HttpStatus.BAD_REQUEST.value(), "start Date should before or equal to end date");

		when(lmsService.createLMS(any()))
				.thenThrow(new CustomException("start Date should before or equal to end date"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/lms").content(MasterData.asJsonString(lmsDTO))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(errorResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);
	}

	@Test
	public void testUpdateUserInvalidDataException() throws Exception {
		LMSDTO lmsDTO = MasterData.getLMSDto();
		lmsDTO.setTitle(null);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/lms/" + lmsDTO.getId())
				.content(MasterData.asJsonString(lmsDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"),
				exceptionTestFile);

	}

	@Test
	public void testUpdateUserWithStartDateAfterEndDateCustomException() throws Exception {
		LMSDTO lmsDTO = MasterData.getLMSDto();
		lmsDTO.setStartDate(LocalDate.now().plusDays(10));
		lmsDTO.setEndDate(LocalDate.now());

		RestExceptionHandler.ErrorResponse errorResponse = new RestExceptionHandler.ErrorResponse(
				HttpStatus.BAD_REQUEST.value(), "start Date should before or equal to end date");

		when(lmsService.updateLMS(eq(lmsDTO.getId()), any()))
				.thenThrow(new CustomException("start Date should before or equal to end date"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/lms/" + lmsDTO.getId())
				.content(MasterData.asJsonString(lmsDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(errorResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);
	}

	@Test
	public void testGetUserByIdResourceNotFoundException() throws Exception {
		LMSDTO lmsDTO = MasterData.getLMSDto();
		RestExceptionHandler.ErrorResponse exResponse = new RestExceptionHandler.ErrorResponse(
				HttpStatus.BAD_REQUEST.value(), "LMS with " + lmsDTO.getId() + " not found");

		when(this.lmsService.getLMSById(lmsDTO.getId()))
				.thenThrow(new ResourceNotFoundException("LMS with " + lmsDTO.getId() + " not found"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/lms/" + lmsDTO.getId())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);

	}

	@Test
	public void testDeleteUserByIdResourceNotFoundException() throws Exception {
		LMSDTO lmsDTO = MasterData.getLMSDto();
		lmsDTO.setId(23424234234L);
		RestExceptionHandler.ErrorResponse exResponse = new RestExceptionHandler.ErrorResponse(
				HttpStatus.BAD_REQUEST.value(), "LMS with " + lmsDTO.getId() + " not found");

		when(this.lmsService.deleteLMS(lmsDTO.getId()))
				.thenThrow(new ResourceNotFoundException("LMS with " + lmsDTO.getId() + " not found"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/lms/" + lmsDTO.getId())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);

	}

	@Test
	public void testUpdateUserByIdResourceNotFoundException() throws Exception {
		LMSDTO lmsDTO = MasterData.getLMSDto();
		RestExceptionHandler.ErrorResponse exResponse = new RestExceptionHandler.ErrorResponse(
				HttpStatus.BAD_REQUEST.value(), "LMS with " + lmsDTO.getId() + " not found");

		when(this.lmsService.updateLMS(eq(lmsDTO.getId()), any()))
				.thenThrow(new ResourceNotFoundException("LMS with " + lmsDTO.getId() + " not found"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/lms/" + lmsDTO.getId())
				.content(MasterData.asJsonString(lmsDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);

	}
}
