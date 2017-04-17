package io.dexter.services.dispatcher.entities;

/**
 * City entity from service provider
 * 
 * @author ericramos
 *
 */
public class City {
	private String name;
	
	public City(){
		
	}
	public City(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

}
