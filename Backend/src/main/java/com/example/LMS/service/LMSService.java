package com.example.LMS.service;

import java.util.List;

import com.example.LMS.dto.LMSDTO;

public interface LMSService {
	
    LMSDTO createLMS(LMSDTO lmsDTO);
    LMSDTO getLMSById(Long id);
    List<LMSDTO> getAllLMS();
    List<LMSDTO> searchLMSByTitle(String title);
    LMSDTO updateLMS(Long id, LMSDTO lmsDTO);
    boolean deleteLMS(Long id);
}
