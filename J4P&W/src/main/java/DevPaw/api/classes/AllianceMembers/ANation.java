package DevPaw.api.classes.AllianceMembers;

import java.util.HashMap;
import java.util.Map;

public class ANation {

	public int nationid;
	public String nation;
	public String leader;
	public String warPolicy;
	public String color;
	public String alliance;
	public int allianceid;
	public int allianceposition;
	public int cities;
	public int offensivewars;
	public int defensivewars;
	public String score;
	public String vacmode;
	public int minutessinceactive;
	public String infrastructure;
	public int cityprojecttimerturns;
	public String bauxiteworks;
	public String ironworks;
	public String armsstockpile;
	public String emgasreserve;
	public String massirrigation;
	public String inttradecenter;
	public String missilepad;
	public String nuclearresfac;
	public String irondome;
	public String vitaldefsys;
	public String intagncy;
	public String uraniumenrich;
	public String propbureau;
	public String cenciveng;
	public String money;
	public String food;
	public String coal;
	public String oil;
	public String uranium;
	public String bauxite;
	public String iron;
	public String lead;
	public String gasoline;
	public String munitions;
	public String aluminum;
	public String steel;
	public String credits;
	public String soldiers;
	public String tanks;
	public String aircraft;
	public String ships;
	public String missiles;
	public String nukes;
	public String spies;
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}
	
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}
