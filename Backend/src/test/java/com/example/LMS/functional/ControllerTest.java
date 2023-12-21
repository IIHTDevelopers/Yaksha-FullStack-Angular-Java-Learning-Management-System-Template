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
	public void testCreateLMS() throws Exception {
		LMSDTO lmsDTO = MasterData.getLMSDto();
		LMS savedLMS = MasterData.getLMS();
		savedLMS.setId(lmsDTO.getId());

		when(lmsService.createLMS(any())).thenReturn(lmsDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/lms").content(MasterData.asJsonString(lmsDTO))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(savedLMS)) ? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testCreateLMSIsServiceMethodCalled() throws Exception {
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
	public void testGetAllLMSs() throws Exception {
		List<LMSDTO> LMSs = MasterData.getLMSDTOList();

		when(this.lmsService.getAllLMS()).thenReturn(LMSs);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/lms").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(LMSs)) ? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testGetAllLMSsIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		List<LMS> LMSs = MasterData.getLMSList();

		when(this.lmsService.getAllLMS()).then(new Answer<List<LMS>>() {

			@Override
			public List<LMS> answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return LMSs;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/lms/").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	@Test
	public void testGetLMSById() throws Exception {
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
	public void testGetLMSByIdIsServiceMethodCalled() throws Exception {
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
	public void testUpdateLMS() throws Exception {
		LMSDTO LMSDTO = MasterData.getLMSDto();
		LMS updatedLMS = MasterData.getLMS();
		updatedLMS.setId(LMSDTO.getId());

		when(this.lmsService.updateLMS(eq(LMSDTO.getId()), any())).thenReturn(LMSDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/lms/" + LMSDTO.getId())
				.content(MasterData.asJsonString(updatedLMS)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(updatedLMS)) ? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testUpdateLMSIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		LMSDTO lmsDTO = MasterData.getLMSDto();
		LMS lmsLMS = MasterData.getLMS();
		lmsLMS.setId(lmsDTO.getId());

		when(this.lmsService.updateLMS(eq(lmsDTO.getId()), any())).then(new Answer<LMSDTO>() {

			@Override
			public LMSDTO answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return lmsDTO;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/lms/" + lmsDTO.getId())
				.content(MasterData.asJsonString(lmsLMS)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	@Test
	public void testDeleteLMS() throws Exception {
		LMSDTO LMSDTO = MasterData.getLMSDto();
		when(this.lmsService.deleteLMS(LMSDTO.getId())).thenReturn(true);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/lms/" + LMSDTO.getId())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals("true") ? "true" : "false"), businessTestFile);

	}

	@Test
	public void testDeleteLMSIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		LMSDTO LMSDTO = MasterData.getLMSDto();
		when(this.lmsService.deleteLMS(LMSDTO.getId())).then(new Answer<Boolean>() {

			@Override
			public Boolean answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return true;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/lms/" + LMSDTO.getId())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	@Test
	public void testSearchLMSsByTitle() throws Exception {
		List<LMS> LMSs = MasterData.getLMSList();
		List<LMSDTO> LMSsDTO = MasterData.getLMSDTOList();
		String title = "Spring";
		when(this.lmsService.searchLMSByTitle(title)).thenReturn(LMSsDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/lms/search").queryParam("title", title)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(LMSs)) ? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testSearchLMSsByTitleIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		List<LMS> LMSs = MasterData.getLMSList();
		String title = "Spring";
		when(this.lmsService.searchLMSByTitle(title)).then(new Answer<List<LMS>>() {

			@Override
			public List<LMS> answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return LMSs;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/lms/search").queryParam("title", title)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	
	@Test
	public void testSearchLMSsByWithoutFilter() throws Exception {
		List<LMS> LMSs = MasterData.getLMSList();
		List<LMSDTO> LMSsDTO = MasterData.getLMSDTOList();
		when(this.lmsService.getAllLMS()).thenReturn(LMSsDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/lms/search")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(LMSs)) ? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testSearchLMSsByWithoutFilterIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		List<LMS> LMSs = MasterData.getLMSList();
		when(this.lmsService.getAllLMS()).then(new Answer<List<LMS>>() {

			@Override
			public List<LMS> answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return LMSs;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/lms/search")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

}
