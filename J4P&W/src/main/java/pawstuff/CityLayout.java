package pawstuff;

import com.google.gson.Gson;

import api.classes.City;

public class CityLayout {
	public int infraNeeded;
	public int impTotal;
	public int impCoalpower;
	public int impOilpower;
	public int impWindpower;
	public int impNuclearpower;
	public int impCoalmine;
	public int impOilwell;
	public int impUramine;
	public int impLeadmine;
	public int impIronmine;
	public int impBauxitemine;
	public int impFarm;
	public int impGasrefinery;
	public int impAluminumrefinery;
	public int impMunitionsfactory;
	public int impSteelmill;
	public int impPolicestation;
	public int impHospital;
	public int impRecyclingcenter;
	public int impSubway;
	public int impSupermarket;
	public int impBank;
	public int impMall;
	public int impStadium;
	public int impBarracks;
	public int impFactory;
	public int impHangars;
	public int impDrydock;
	public static CityLayout getCityLayout(String json) {
		return new Gson().fromJson(json, CityLayout.class);
	}
	
	public boolean audit(City c) {
		if(infraNeeded == Integer.parseInt(c.infrastructure))
			return true;
		else
			return false;
	}
	
	
}
