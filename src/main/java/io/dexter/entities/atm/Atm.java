package io.dexter.entities.atm;

public class Atm {
	private Address address;
	private int distance;
	private String type;
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	

}
//{"address":{"street":"Plesmanlaan","housenumber":"174","postalcode":"2497 CC","city":"DEN HAAG","geoLocation":{"lat":"52.039047","lng":"4.366424"}},"distance":0,"type":"ING"}