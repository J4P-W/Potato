package utils.miscellaneous;

import java.io.Serializable;

import com.google.gson.Gson;

import DevPaw.api.classes.City;



public class CityLayout  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2306489760170426392L;
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
	
	public boolean audit(City city) {
	    return infraNeeded >= Double.parseDouble(city.infrastructure) && impCoalpower >= Integer.parseInt(city.imp_coalpower)
	      && impOilpower >= Integer.parseInt(city.imp_oilpower) && impWindpower >= Integer.parseInt(city.imp_windpower)
	      && impNuclearpower >= Integer.parseInt(city.imp_nuclearpower) && impCoalmine >= Integer.parseInt(city.imp_coalmine)
	      && impOilwell >= Integer.parseInt(city.imp_oilwell) && impUramine >= Integer.parseInt(city.imp_uramine)
	      && impLeadmine >= Integer.parseInt(city.imp_leadmine) && impIronmine >= Integer.parseInt(city.imp_ironmine)
	      && impBauxitemine >= Integer.parseInt(city.imp_bauxitemine) && impFarm >= Integer.parseInt(city.imp_farm) 
	      && impGasrefinery >= Integer.parseInt(city.imp_gasrefinery) && impAluminumrefinery >= Integer.parseInt(city.imp_aluminumrefinery)
	      && impMunitionsfactory >= Integer.parseInt(city.imp_munitionsfactory) && impSteelmill >= Integer.parseInt(city.imp_steelmill)
	      && impPolicestation >= Integer.parseInt(city.imp_policestation) && impHospital >= Integer.parseInt(city.imp_hospital)
	      && impRecyclingcenter >= Integer.parseInt(city.imp_recyclingcenter) && impSubway >= Integer.parseInt(city.imp_subway)
	      && impSupermarket >= Integer.parseInt(city.imp_supermarket) && impBank >= Integer.parseInt(city.imp_bank)
	      && impMall >= Integer.parseInt(city.imp_mall) && impStadium >= Integer.parseInt(city.imp_stadium)
	      && impBarracks >= Integer.parseInt(city.imp_barracks) && impFactory >= Integer.parseInt(city.imp_factory)
	      && impHangars >= Integer.parseInt(city.imp_hangar) && impDrydock >= Integer.parseInt(city.imp_drydock);
	  }
}
