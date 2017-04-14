package io.dexter.entities.atm;

/**
 * @author ericramos
 *
 */
public class AtmDTO {
	private String street;
	private String housenumber;
	private String postalcode;
	private String city;
	
	
	
	public AtmDTO(String street, String housenumber, String postalcode, String city) {
		super();
		this.street = street;
		this.housenumber = housenumber;
		this.postalcode = postalcode;
		this.city = city;
	}
	
	
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getHousenumber() {
		return housenumber;
	}
	public void setHousenumber(String housenumber) {
		this.housenumber = housenumber;
	}
	public String getPostalcode() {
		return postalcode;
	}
	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	

}
