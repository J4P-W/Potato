package DevPaw.api;


import com.google.gson.Gson;

import DevPaw.api.classes.Alliance;
import DevPaw.api.classes.City;
import DevPaw.api.classes.Military;
import DevPaw.api.classes.Nation;
import DevPaw.api.classes.AllianceMembers.AllianceMembers;
import DevPaw.api.classes.Alliances.Alliances;
import DevPaw.api.classes.Nations.Nations;
import DevPaw.api.classes.TradePrice.TradePrice;
import DevPaw.api.classes.War.War;
import DevPaw.api.classes.Wars.Wars;
import DevPaw.utilities.SpeedUtils;
import utils.general.MaxMap;

public class PaWAPI {
	
	public static MaxMap<Integer, Object> buffer;
	
	public static void setBufferSize(int buffersize) {
		buffer = new MaxMap<Integer, Object>(buffersize);
	}
	
	public void store(Object o) {
		buffer.put(/*o.getClass().getSimpleName()*/ 1,o);
		System.out.println("Current Buffer size: "+buffer.size());
	}
	
	public int getAPID(Object o) {
		switch(o.getClass().getSimpleName()) {
		case "War":
			return 0;
		case "City":
			return 1;
		case "Nation":
			return 2;
		case "Alliance":
			return 3;
		case "AllianceMembers":
			return 4;
		case "Wars":
			return 5;
		case "Nations":
			return 6;
		case "Alliances":
			return 7;
		case "Military":
			return 8;
		case "TradePrice":
			return 9;
			
		}
		return -1;
	}
	
	
	public static War getWar(String id) { // 0
		return new Gson().fromJson(SpeedUtils.speedURL("https://politicsandwar.com/api/war/"+id), War.class);
	}
	public static City getCity(String id) { // 1
    	return new Gson().fromJson(SpeedUtils.speedURL("https://politicsandwar.com/api/city/id="+id), City.class);
    }
	public static Nation getNation(String id) { // 2
    	return new Gson().fromJson(SpeedUtils.speedURL("https://politicsandwar.com/api/nation/id="+id), Nation.class);
    }
	public static Alliance getAlliance(String id) { // 3
    	return new Gson().fromJson(SpeedUtils.speedURL("https://politicsandwar.com/api/alliance/id="+id), Alliance.class);
    }
	public static AllianceMembers getAllianceMembers(String id, String key) { // 4
		return new Gson().fromJson(SpeedUtils.speedURL(String.format("http://politicsandwar.com/api/alliance-members/?allianceid=%s&key=%s",id,key)), AllianceMembers.class);
	}
	public static Wars getWars() { // 5
    	return new Gson().fromJson(SpeedUtils.speedURL("http://politicsandwar.com/api/wars/"), Wars.class);
    }
	public static Nations getNations() { // 6
    	return new Gson().fromJson(SpeedUtils.speedURL("https://politicsandwar.com/api/nations/"), Nations.class);
    }
	public static Alliances genAlliances() { // 7
    	return new Gson().fromJson(SpeedUtils.speedURL("https://politicsandwar.com/api/alliances/"), Alliances.class);
    }
	public static Military getMilitary(String nationid) { // 8
		return new Military(nationid);
	}
	public static TradePrice getTradePrice(String resource) { // 9
		return new Gson().fromJson(SpeedUtils.speedURL("https://politicsandwar.com/api/tradeprice/resource="+(resource.toLowerCase())), TradePrice.class);
	}
	
}
