package br.com.gabriel.twilioIntegration.dto;

public class ApiPlacar {

	
	private String time_casa;
	private String time_fora;
	private Integer gols_casa;
	private Integer gols_fora;
	private String status;
	private String campeonato;
	
	
	public String getTime_casa() {
		return time_casa;
	}
	public void setTime_casa(String time_casa) {
		this.time_casa = time_casa;
	}
	public String getTime_fora() {
		return time_fora;
	}
	public void setTime_fora(String time_fora) {
		this.time_fora = time_fora;
	}
	public Integer getGols_casa() {
		return gols_casa;
	}
	public void setGols_casa(Integer gols_casa) {
		this.gols_casa = gols_casa;
	}
	public Integer getGols_fora() {
		return gols_fora;
	}
	public void setGols_fora(Integer gols_fora) {
		this.gols_fora = gols_fora;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCampeonato() {
		return campeonato;
	}
	public void setCampeonato(String campeonato) {
		this.campeonato = campeonato;
	}
	 
	
	
}
