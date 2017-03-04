package io.dexter.entities.atm;

public class Address {
	private String street;
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

	public void setPostalcode(String postalconde) {
		this.postalcode = postalconde;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public GeoLocation getGeoLocation() {
		return geoLocation;
	}

	public void setGeoLocation(GeoLocation geoLocation) {
		this.geoLocation = geoLocation;
	}

	private String housenumber;
	private String postalcode;
	private String city;
	
	private GeoLocation geoLocation;

}

//{"address":
//{"street":"Plesmanlaan","housenumber":"174","postalcode":"2497 CC","city":"DEN HAAG","geoLocation":{"lat":"52.039047","lng":"4.366424"}},"distance":0,"type":"ING"}