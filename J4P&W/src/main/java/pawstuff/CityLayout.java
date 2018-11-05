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
	    return infraNeeded == Integer.parseInt(c.infrastructure) && impCoalpower == Integer.parseInt(c.imp_coalpower)
	      && impOilpower == Integer.parseInt(c.imp_oilpower) && impWindpower == Integer.parseInt(c.imp_windpower)
	      && impNuclearpower == Integer.parseInt(c.imp_nuclearpower) && impCoalmine == Integer.parseInt(c.imp_coalmine)
	      && impOilwell == Integer.parseInt(c.imp_oilwell) && impUramine == Integer.parseInt(c.imp_uramine)
	      && impLeadmine == Integer.parseInt(c.imp_leadmine) && impIronmine == Integer.parseInt(c.imp_ironmine)
	      && impBauxitemine == Integer.parseInt(c.imp_bauxitemine) && impFarm == Integer.parseInt(c.imp_farm) 
	      && impGasrefinery == Integer.parseInt(c.imp_gasrefinery) && impAluminumrefinery == Integer.parseInt(c.imp_aluminumrefinery)
	      && impMunitionsfactory == Integer.parseInt(c.imp_munitionsfactory) && impSteelmill == Integer.parseInt(c.imp_steelmill)
	      && impPolicestation == Integer.parseInt(c.imp_policestation) && impHospital == Integer.parseInt(c.imp_hospital)
	      && impRecyclingcenter == Integer.parseInt(c.imp_recyclingcenter) && impSubway == Integer.parseInt(c.imp_subway)
	      && impSupermarket == Integer.parseInt(c.imp_supermarket) && impBank == Integer.parseInt(c.imp_bank)
	      && impMall == Integer.parseInt(c.imp_mall) && impStadium == Integer.parseInt(c.imp_stadium)
	      && impBarracks == Integer.parseInt(c.imp_barracks) && impFactory == Integer.parseInt(c.imp_factory)
	      && impHangars == Integer.parseInt(c.imp_hangar) && impDrydock == Integer.parseInt(c.imp_drydock);
	  }
}
