package flight_booking_assign;

import java.util.ArrayList;
import java.util.List;

public class UserData {
	private String name,mobNum,govId,dob,email,password;
	private List<Journey> journeys = new ArrayList<>();
	public UserData(String name, String mobNum, String govId, String dob, String email, String password) {
		super();
		this.name = name;
		this.mobNum = mobNum;
		this.govId = govId;
		this.dob = dob;
		this.email = email;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobNum() {
		return mobNum;
	}

	public void setMobNum(String mobNum) {
		this.mobNum = mobNum;
	}

	public String getGovId() {
		return govId;
	}

	public void setGovId(String govId) {
		this.govId = govId;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Journey> getFlightBookings() {	
		return journeys;
	}

	public void setFlightBookings(Journey journey) {
		this.journeys.add(journey);
	}
	
	
}
