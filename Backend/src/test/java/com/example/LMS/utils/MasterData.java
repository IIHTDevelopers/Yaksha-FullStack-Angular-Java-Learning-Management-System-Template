package com.example.LMS.utils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.example.LMS.dto.LMSDTO;
import com.example.LMS.entity.LMS;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class MasterData {

	public static LMSDTO getLMSDto() {
		LMSDTO lmsDTO = new LMSDTO();
		lmsDTO.setId(1L);
		lmsDTO.setTitle("Spring");
		lmsDTO.setDescription("Java Framework");
		lmsDTO.setInstructor("Tech Academy");
		lmsDTO.setDuration("2 months");
		lmsDTO.setStartDate(LocalDate.now());
		lmsDTO.setEndDate(LocalDate.now().plusDays(10));
		lmsDTO.setSyllabus("Spring boot");
		return lmsDTO;
	}

	public static LMS getLMS() {
		LMS lms = new LMS();
		lms.setId(1L);
		lms.setTitle("Spring");
		lms.setDescription("Java Framework");
		lms.setInstructor("Tech Academy");
		lms.setDuration("2 months");
		lms.setStartDate(LocalDate.now());
		lms.setEndDate(LocalDate.now().plusDays(10));
		lms.setSyllabus("Spring boot");
		return lms;
	}

	public static List<LMS> getLMSList() {
		List<LMS> lmss = new ArrayList<>();
		LMS lms = new LMS();
		lms.setId(1L);
		lms.setTitle("Spring");
		lms.setDescription("Java Framework");
		lms.setInstructor("Tech Academy");
		lms.setDuration("2 months");
		lms.setStartDate(LocalDate.now());
		lms.setEndDate(LocalDate.now().plusDays(10));
		lms.setSyllabus("Spring boot");
		lmss.add(lms);

		lms = new LMS();
		lms.setId(2L);
		lms.setTitle("COA");
		lms.setDescription("Computer Architecture");
		lms.setInstructor("Tech Academy");
		lms.setDuration("1 months");
		lms.setStartDate(LocalDate.now());
		lms.setEndDate(LocalDate.now().plusDays(10));
		lms.setSyllabus("Microprocessor");
		lmss.add(lms);

		return lmss;
	}
	
	public static List<LMSDTO> getLMSDTOList() {
		List<LMSDTO> lmss = new ArrayList<>();
		LMSDTO lms = new LMSDTO();
		lms.setId(1L);
		lms.setTitle("Spring");
		lms.setDescription("Java Framework");
		lms.setInstructor("Tech Academy");
		lms.setDuration("2 months");
		lms.setStartDate(LocalDate.now());
		lms.setEndDate(LocalDate.now().plusDays(10));
		lms.setSyllabus("Spring boot");
		lmss.add(lms);

		lms = new LMSDTO();
		lms.setId(2L);
		lms.setTitle("COA");
		lms.setDescription("Computer Architecture");
		lms.setInstructor("Tech Academy");
		lms.setDuration("1 months");
		lms.setStartDate(LocalDate.now());
		lms.setEndDate(LocalDate.now().plusDays(10));
		lms.setSyllabus("Microprocessor");
		lmss.add(lms);

		return lmss;
	}

	public static String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			mapper.registerModule(new JavaTimeModule());
			mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
			final String jsonContent = mapper.writeValueAsString(obj);

			return jsonContent;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static String withSizeRandomString(int size) {
		String s = "";
		for (int i = 0; i < size; i++) {
			s.concat("A");
		}
		return s;
	}

}
