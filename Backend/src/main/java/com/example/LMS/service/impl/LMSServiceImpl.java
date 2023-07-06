package com.example.LMS.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LMS.dto.LMSDTO;
import com.example.LMS.repo.LMSDAO;
import com.example.LMS.service.LMSService;

@Service
public class LMSServiceImpl implements LMSService {
	
	@Autowired
    private final LMSDAO lmsDAO;
    
    @Autowired
    public LMSServiceImpl(LMSDAO lmsDAO) {
        this.lmsDAO = lmsDAO;
    }
    
    @Override
    public LMSDTO createLMS(LMSDTO lmsDTO) {
    	return null;
    }
    
    @Override
    public LMSDTO getLMSById(Long id) {
    	return null;
    }
    
    @Override
    public List<LMSDTO> getAllLMS() {
    	return null;
    }
    
    @Override
    public List<LMSDTO> searchLMSByTitle(String title) {
    	return null;
    }
    
    @Override
    public LMSDTO updateLMS(Long id, LMSDTO lmsDTO) {
    	return null;
    }
    
    @Override
    public boolean deleteLMS(Long id) {
    	return false;
    }


}


