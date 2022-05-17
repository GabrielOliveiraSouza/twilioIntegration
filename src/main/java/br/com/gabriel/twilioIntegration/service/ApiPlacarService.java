package br.com.gabriel.twilioIntegration.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import br.com.gabriel.twilioIntegration.dto.ApiPlacar;
import br.com.gabriel.twilioIntegration.utils.Utils;

@Service
public class ApiPlacarService {

	private Utils utils;

	@Value("${app.PATH_LIVE_GAMES}")
	String path;
	
	private ApiPlacar apiPlacar;

	public ArrayList<ApiPlacar>  getLiveGames() throws IOException {
		
		utils = new Utils();
		
		byte[] bytes = utils.getBytesFromFile(path); 
		
		String liveGamestxt = new String(bytes, "UTF-8");
		
		JsonArray liveGamesJson = utils.changeStringToJson(liveGamestxt);
		
		ArrayList<ApiPlacar> apiPlacarList = new ArrayList<ApiPlacar>();

		for (JsonElement games : liveGamesJson) {
			if (!games.toString().equals("0")) {
				
				apiPlacar = new ApiPlacar();
				JsonObject gamesObject = games.getAsJsonObject();
				
				apiPlacar.setTime_casa(gamesObject.get("time_casa").getAsString());
				apiPlacar.setGols_casa(gamesObject.get("gols_casa").getAsInt());
				apiPlacar.setTime_fora(gamesObject.get("time_fora").getAsString());
				apiPlacar.setGols_fora(gamesObject.get("gols_fora").getAsInt());
				apiPlacar.setStatus(gamesObject.get("status_jogo").getAsString());
				apiPlacar.setCampeonato(gamesObject.get("campeonato").getAsString());
				
				
				apiPlacarList.add(apiPlacar);
				
			}

		}

		return apiPlacarList;
	}

	public HashMap<String, ArrayList<ApiPlacar>> getGamesByCampeonatos() throws IOException{
		
		
		HashMap<String, ArrayList<ApiPlacar>> returnGames = new HashMap<String, ArrayList<ApiPlacar>>();
		ArrayList<ApiPlacar> liveGames =this.getLiveGames();  
		
		 for (ApiPlacar jogos : liveGames) {
			 if(!returnGames.containsKey(jogos.getCampeonato())) {		
				 ArrayList<ApiPlacar> novoCampeonato = new ArrayList<ApiPlacar>();
				 novoCampeonato.add(jogos);
				 returnGames.put(jogos.getCampeonato(), novoCampeonato);
				 
			 }else {
				 returnGames.get(jogos.getCampeonato()).add(jogos);
			 }
			
				 
		}
		 
		
		return returnGames;
	}
	
}
