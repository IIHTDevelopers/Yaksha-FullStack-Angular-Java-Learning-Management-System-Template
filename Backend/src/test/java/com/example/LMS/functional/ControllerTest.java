package com.example.LMS.functional;

import static com.example.LMS.utils.TestUtils.businessTestFile;
import static com.example.LMS.utils.TestUtils.currentTest;
import static com.example.LMS.utils.TestUtils.testReport;
import static com.example.LMS.utils.TestUtils.yakshaAssert;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.LMS.controller.LMSController;
import com.example.LMS.dto.LMSDTO;
import com.example.LMS.entity.LMS;
import com.example.LMS.service.LMSService;
import com.example.LMS.utils.MasterData;

@WebMvcTest(LMSController.class)
@AutoConfigureMockMvc
public class ControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private LMSService lmsService;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testCreateUser() throws Exception {
		LMSDTO lmsDTO = MasterData.getLMSDto();
		LMS savedUser = MasterData.getLMS();
		savedUser.setId(lmsDTO.getId());

		when(lmsService.createLMS(any())).thenReturn(lmsDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/lms").content(MasterData.asJsonString(lmsDTO))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(savedUser)) ? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testCreateUserIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		LMSDTO lmsDTO = MasterData.getLMSDto();
		LMS savedLms = MasterData.getLMS();
		savedLms.setId(lmsDTO.getId());

		when(this.lmsService.createLMS(any())).then(new Answer<LMSDTO>() {

			@Override
			public LMSDTO answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return lmsDTO;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/lms").content(MasterData.asJsonString(lmsDTO))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	@Test
	public void testGetAllUsers() throws Exception {
		List<LMSDTO> users = MasterData.getLMSDTOList();

		when(this.lmsService.getAllLMS()).thenReturn(users);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/lms").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(users)) ? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testGetAllUsersIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		List<LMS> users = MasterData.getLMSList();

		when(this.lmsService.getAllLMS()).then(new Answer<List<LMS>>() {

			@Override
			public List<LMS> answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return users;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/lms/").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	@Test
	public void testGetUserById() throws Exception {
		LMSDTO lmsDTO = MasterData.getLMSDto();
		when(this.lmsService.getLMSById(lmsDTO.getId())).thenReturn(lmsDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/lms/" + lmsDTO.getId())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(lmsDTO)) ? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testGetUserByIdIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		LMS lms = MasterData.getLMS();
		LMSDTO lmsDTO = MasterData.getLMSDto();
		when(this.lmsService.getLMSById(lmsDTO.getId())).then(new Answer<LMSDTO>() {

			@Override
			public LMSDTO answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return lmsDTO;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/lms/" + lmsDTO.getId())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	@Test
	public void testUpdateUser() throws Exception {
		LMSDTO userDTO = MasterData.getLMSDto();
		LMS updatedUser = MasterData.getLMS();
		updatedUser.setId(userDTO.getId());

		when(this.lmsService.updateLMS(eq(userDTO.getId()), any())).thenReturn(userDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/lms/" + userDTO.getId())
				.content(MasterData.asJsonString(updatedUser)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(updatedUser)) ? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testUpdateUserIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		LMSDTO lmsDTO = MasterData.getLMSDto();
		LMS lmsUser = MasterData.getLMS();
		lmsUser.setId(lmsDTO.getId());

		when(this.lmsService.updateLMS(eq(lmsDTO.getId()), any())).then(new Answer<LMSDTO>() {

			@Override
			public LMSDTO answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return lmsDTO;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/lms/" + lmsDTO.getId())
				.content(MasterData.asJsonString(lmsUser)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	@Test
	public void testDeleteUser() throws Exception {
		LMSDTO userDTO = MasterData.getLMSDto();
		when(this.lmsService.deleteLMS(userDTO.getId())).thenReturn(true);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/lms/" + userDTO.getId())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals("true") ? "true" : "false"), businessTestFile);

	}

	@Test
	public void testDeleteUserIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		LMSDTO userDTO = MasterData.getLMSDto();
		when(this.lmsService.deleteLMS(userDTO.getId())).then(new Answer<Boolean>() {

			@Override
			public Boolean answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return true;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/lms/" + userDTO.getId())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	@Test
	public void testSearchUsersByTitle() throws Exception {
		List<LMS> users = MasterData.getLMSList();
		List<LMSDTO> usersDTO = MasterData.getLMSDTOList();
		String title = "Spring";
		when(this.lmsService.searchLMSByTitle(title)).thenReturn(usersDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/lms/search").queryParam("title", title)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(users)) ? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testSearchUsersByTitleIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		List<LMS> users = MasterData.getLMSList();
		String title = "Spring";
		when(this.lmsService.searchLMSByTitle(title)).then(new Answer<List<LMS>>() {

			@Override
			public List<LMS> answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return users;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/lms/search").queryParam("title", title)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	
	@Test
	public void testSearchUsersByWithoutFilter() throws Exception {
		List<LMS> users = MasterData.getLMSList();
		List<LMSDTO> usersDTO = MasterData.getLMSDTOList();
		when(this.lmsService.getAllLMS()).thenReturn(usersDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/lms/search")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(users)) ? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testSearchUsersByWithoutFilterIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		List<LMS> users = MasterData.getLMSList();
		when(this.lmsService.getAllLMS()).then(new Answer<List<LMS>>() {

			@Override
			public List<LMS> answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return users;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/lms/search")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

}
