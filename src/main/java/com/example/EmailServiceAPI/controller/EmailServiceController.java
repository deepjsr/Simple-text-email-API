package com.example.EmailServiceAPI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.EmailServiceAPI.model.EmailServiceAPIModel;
import com.example.EmailServiceAPI.service.EmaiServiceAPIService;

@RestController
public class EmailServiceController {


	@Autowired
	private EmaiServiceAPIService emaiServiceAPIService;

	@RequestMapping("/welcome")
	public String welcome() {
		return "welcmoe to the server";
	}

	@PostMapping("/sendemail")
	public ResponseEntity<?> sendEamil(@RequestBody EmailServiceAPIModel request) {
		
	Boolean	r=emaiServiceAPIService.sendEmail(request.getSubject(), request.getMessage(), request.getTo());
//		 Boolean r=emaiServiceAPIService.sendEmail(request.getSubject(),request.getMessage(),request.getTo());
		System.out.println(request);
		
		if (r) {
			return ResponseEntity.ok("Done..");
		}else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Uneble to send email");
		}
	}
}
