package com.example.LMS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.example.LMS.service.LMSService;

@RestController
@CrossOrigin
public class LMSController {
	
	@Autowired
	private final LMSService lmsService;

	
	public LMSController(LMSService lmsService) {
		this.lmsService = lmsService;
	}


}
