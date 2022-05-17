package br.com.gabriel.twilioIntegration.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

public class Utils {

	public byte[] getBytesFromFile(String filePath) throws IOException {

		byte[] encoded = Files.readAllBytes(Paths.get(filePath));

		return encoded;
	}
	
	public JsonArray changeStringToJson(String txt) {
		return new Gson().fromJson(txt, JsonArray.class);
	}
	
	

}
