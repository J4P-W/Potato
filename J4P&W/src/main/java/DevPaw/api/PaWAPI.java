package DevPaw.api;


import java.io.Serializable;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

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
import DevPaw.api.classes.War.WarData;
import DevPaw.api.classes.Wars.Wars;
import DevPaw.api.exceptions.UnsuccessfullAPIException;
import DevPaw.utilities.DevReader;
import utils.general.MaxMap;

public class PaWAPI implements Serializable {
	private static final long serialVersionUID = 7225535953181327957L;
	private MaxMap<String, Object> buffer;
	private MaxMap<String, Instant> death;
	protected boolean reg;
	private DevReader r;
	private TemporalUnit t;
	private int value;
	public PaWAPI(DevReader r) {
		buffer = new MaxMap<String, Object>(50);
		death = new MaxMap<String, Instant>(50);
		reg = true;
		this.r = r;
		this.t = ChronoUnit.HOURS;
		this.value = 1;
	}
	public PaWAPI() {
		buffer = new MaxMap<String, Object>(50);
		death = new MaxMap<String, Instant>(50);
		reg = true;
		this.r = new DevReader(System.out);
		this.t = ChronoUnit.HOURS;
		this.value = 1;
	}
	public PaWAPI(int buffersize) {
		buffer = new MaxMap<String, Object>(buffersize);
		death = new MaxMap<String, Instant>(buffersize);
		reg = true;
		this.r = new DevReader(System.out);
		this.t = ChronoUnit.HOURS;
		this.value = 1;
	}
	public PaWAPI(DevReader r, int buffersize) {
		buffer = new MaxMap<String, Object>(buffersize);
		death = new MaxMap<String, Instant>(buffersize);
		reg = true;
		this.r = r;
		this.t = ChronoUnit.HOURS;
		this.value = 1;
		
	}
	public void setTime(int value, TemporalUnit t) {
		this.t = t;
		this.value = value;
	}
	
	public void setToTest() {
		reg = false;
	}
	public void setToReg() {
		reg = true;
	}
	
	public void store(String url,Object o) {
		buffer.put(url,o);
		death.put(url, Instant.now().plus(value, t));
	}
	protected static Gson gson = new Gson();
	protected synchronized <C> Object getAPI(String url, Class<C> c) {
		Object o;
		if(!buffer.containsKey(url) || death.get(url).isBefore(Instant.now())) {
			if(buffer.containsKey(url)) {
				death.remove(url);
				buffer.remove(url);
				System.out.println("Timeout request!");
			}
			System.out.println("New API Request! " + url);
			o = gson.fromJson(r.speedURL((reg?"https://politicsandwar.com/api/":"https://test.politicsandwar.com/api/")+url), c);
			store(url,o);
		}
		else {
			o = buffer.get(url);
		}
		return o;
	}
	public boolean inBuffer(String url) {
		return buffer.containsKey((reg?"https://politicsandwar.com/api/":"https://test.politicsandwar.com/api/")+url);
	}
	public War getWar(String id) throws UnsuccessfullAPIException {War o = (War) getAPI("war/"+id, War.class);if(!o.success) {throw new UnsuccessfullAPIException("Call to War api for id="+id+" was unsuccessfull");}return o;}
	public City getCity(String id) throws UnsuccessfullAPIException {City c = (City) getAPI("city/id="+id, City.class);if(!c.success) {throw new UnsuccessfullAPIException("Call to City api for id="+id+" was unsuccessfull");}return c;}
	public Nation getNation(String id) throws UnsuccessfullAPIException {Nation o =  (Nation) getAPI("nation/id="+id, Nation.class);if(!o.success) {throw new UnsuccessfullAPIException("Call to Nation api for id="+id+" was unsuccessfull");}return o;}
	public Alliance getAlliance(String id) throws UnsuccessfullAPIException {Alliance o =  (Alliance) getAPI("alliance/id="+id, Alliance.class);if(!o.success) {throw new UnsuccessfullAPIException("Call to Alliance api for id="+id+" was unsuccessfull");}return o;}
	public AllianceMembers getAllianceMembers(String id, String key) throws UnsuccessfullAPIException {AllianceMembers o =  (AllianceMembers) getAPI(String.format("alliance-members/?allianceid=%s&key=%s",id,key), AllianceMembers.class);if(!o.success) {throw new UnsuccessfullAPIException("Call to AllianceMembers api for id="+id+" was unsuccessfull");}return o;}
	public Wars getWars(int amount) throws UnsuccessfullAPIException {Wars o =  (Wars) getAPI("wars/"+amount, Wars.class);if(!o.success) {throw new UnsuccessfullAPIException("Call to Wars api was unsuccessfull");}return o;}
	public Wars getWars() throws UnsuccessfullAPIException {Wars o =  (Wars) getAPI("wars/", Wars.class);if(!o.success) {throw new UnsuccessfullAPIException("Call to Wars api was unsuccessfull");}return o;}
	public Nations getNations() throws UnsuccessfullAPIException {Nations o =  (Nations) getAPI("nations/", Nations.class);if(!o.success) {throw new UnsuccessfullAPIException("Call to Nations api was unsuccessfull");}return o;}
	public Alliances getAlliances() throws UnsuccessfullAPIException {Alliances o =  (Alliances) getAPI("alliances/", Alliances.class);if(!o.success) {throw new UnsuccessfullAPIException("Call to Alliances api was unsuccessfull");}return o;}
	public Military getMilitary(String nationid) throws UnsuccessfullAPIException {return new Military(nationid);}
	public TradePrice getTradePrice(String resource) {return (TradePrice) getAPI("tradeprice/resource="+(resource.toLowerCase()), TradePrice.class);}
	public WarData getWarData(String id) throws UnsuccessfullAPIException {War o =  (War) getAPI("war/"+id, War.class);if(!o.success) {throw new UnsuccessfullAPIException("Call to War api for id="+id+" was unsuccessfull");}return o.war.get(0);}
}
