package com.spring.jta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.jta.bean.HdfcAccount;
import com.spring.jta.bean.SbiAccount;
import com.spring.jta.dao.BankDao;

@RestController
@RequestMapping("/api")
public class AccountController {

	@Autowired
	BankDao<SbiAccount> sa;

	@Autowired
	BankDao<HdfcAccount> ha;

	@GetMapping(value = "/sa", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getSbiAmounts() throws Exception {
		return new ResponseEntity<>(ha.getAll(), HttpStatus.OK);
	}

	@GetMapping(value = "/ha", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getHdfcAmounts() throws Exception {
		return new ResponseEntity<>(sa.getAll(), HttpStatus.OK);
	}
}
