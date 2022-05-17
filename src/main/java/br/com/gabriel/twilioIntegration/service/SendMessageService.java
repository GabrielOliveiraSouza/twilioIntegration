package br.com.gabriel.twilioIntegration.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.exception.TwilioException;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;

@Service
public class SendMessageService {
	
	@Value("${app.AUTH_TOKEN}")
	private String AUTH_TOKEN;
	
	@Value("${app.AUTH_SID}")
	private String AUTH_SID;

	
	public String SendMessage() {
		
		String successSend = AUTH_SID + "  "+ AUTH_TOKEN;
		
		
		
		
		try {
			Twilio.init(AUTH_SID, AUTH_TOKEN);
			
//		
//		  Message message = CreateMessage("11034851192","11961576051", "VO TE AMO")
//	            .create();
			 Message message = Message.creator( 
		                new com.twilio.type.PhoneNumber("whatsapp:+5511969536102"), 
		                new com.twilio.type.PhoneNumber("whatsapp:+5511961576051"),  
		                "Gabriel vc é um gostoso")      
		            .create(); 
		 
		        System.out.println(message.getSid()); 
		  successSend = message.toString();
		  
		}catch(TwilioException e) {
			e.printStackTrace();
		}
		
		return successSend;
	}
	
	public MessageCreator CreateMessage(String to, String from, String messageSend ) {
		
		String defaultPhone = "whatsapp:+55";
		
		MessageCreator message = Message.creator(
				new PhoneNumber(defaultPhone + to),
				new PhoneNumber(defaultPhone + from),
				messageSend);
		
		return message;
	}
	
}
