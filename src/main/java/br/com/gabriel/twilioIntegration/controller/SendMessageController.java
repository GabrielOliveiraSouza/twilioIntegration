package br.com.gabriel.twilioIntegration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import br.com.gabriel.twilioIntegration.service.SendMessageService;

@RestController
public class SendMessageController {

	
	@Autowired
	SendMessageService sendMessageService;
	
	@GetMapping(value="sendMessage")
	public String SendMessage() {
		return sendMessageService.SendMessage();
	}
}
