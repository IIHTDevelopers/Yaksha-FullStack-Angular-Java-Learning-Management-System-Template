package com.example.LMS.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.LMS.entity.LMS;

@Repository
public interface LMSDAO extends JpaRepository<LMS, Long> {

	
}
