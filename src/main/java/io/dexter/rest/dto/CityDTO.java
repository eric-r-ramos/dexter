package io.dexter.rest.dto;

public class CityDTO {
	private String name;
	
	public CityDTO(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
