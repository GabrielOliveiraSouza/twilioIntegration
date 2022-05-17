package br.com.gabriel.twilioIntegration.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin; 
import com.google.gson.JsonArray;

import br.com.gabriel.twilioIntegration.dto.ApiPlacar;
import br.com.gabriel.twilioIntegration.service.ApiPlacarService;

@RestController  
@RequestMapping("/placar-ao-vivo")
@CrossOrigin(origins = "http://localhost:4200")
public class ApiPlacarController {

	@Autowired
	private ApiPlacarService apiPlacarService;
	
	@GetMapping(value="games", produces=MediaType.APPLICATION_JSON_VALUE)
	public ArrayList<ApiPlacar> getLiveGames() throws IOException {
		 
		 return apiPlacarService.getLiveGames();
		
	}
	

	@GetMapping(value="campeonatos", produces=MediaType.APPLICATION_JSON_VALUE)
	public HashMap<String, ArrayList<ApiPlacar>> getGamesByCampeonatos() throws IOException {
		 
		 return apiPlacarService.getGamesByCampeonatos();
		
	}
	
	
}
