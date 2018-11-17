package DevPaw.api.classes;

import DevPaw.api.PaWAPI;

public class Military {
	public Military(Nation n) {
		sold = Integer.parseInt(n.soldiers);
		tank = Integer.parseInt(n.tanks);
		jets = Integer.parseInt(n.aircraft);
		ship = Integer.parseInt(n.ships);
		
		miss = Integer.parseInt(n.missiles);
		nuke = Integer.parseInt(n.nukes);
		String[] cities = n.cityids.toArray(new String[n.cities]);
		for(String ca:cities) {
			City c = PaWAPI.getCity(ca);
			barr += Integer.parseInt(c.imp_barracks);
			fact += Integer.parseInt(c.imp_factory);
			hang += Integer.parseInt(c.imp_hangar);
			drys += Integer.parseInt(c.imp_drydock);
			infra += Double.parseDouble(c.infrastructure);
		}
		misp = n.missilelpad.equalsIgnoreCase("1");
		nukp = n.nuclearresfac.equalsIgnoreCase("1");
		score = Double.parseDouble(n.score);
		prep = (sold/(barr*5000)+tank/(fact*250)+jets/(hang*18)+ship/(drys*5))/4;
				
		this.cities = cities.length;
	}
	public Military(String nid) {
		Nation n = PaWAPI.getNation(nid);
		sold = Integer.parseInt(n.soldiers);
		tank = Integer.parseInt(n.tanks);
		jets = Integer.parseInt(n.aircraft);
		ship = Integer.parseInt(n.ships);
		
		miss = Integer.parseInt(n.missiles);
		nuke = Integer.parseInt(n.nukes);
		String[] cities = n.cityids.toArray(new String[n.cities]);
		for(String ca:cities) {
			City c = PaWAPI.getCity(ca);
			barr += Integer.parseInt(c.imp_barracks);
			fact += Integer.parseInt(c.imp_factory);
			hang += Integer.parseInt(c.imp_hangar);
			drys += Integer.parseInt(c.imp_drydock);
			infra += Double.parseDouble(c.infrastructure);
		}
		misp = n.missilelpad.equalsIgnoreCase("1");
		nukp = n.nuclearresfac.equalsIgnoreCase("1");
		score = Double.parseDouble(n.score);
		prep = (sold/(barr*5000+Double.MIN_NORMAL)+tank/(fact*250+Double.MIN_NORMAL)+jets/(hang*18+Double.MIN_NORMAL)+ship/(drys*5+Double.MIN_NORMAL))/4;
				
		this.cities = cities.length;
	}
	public double prep;
	
	public int infra;
	public int cities;
	
	public double score;
	
	public int barr;
	public int sold;
	
	public int fact;
	public int tank;
	
	public int hang;
	public int jets;
	
	public int drys;
	public int ship;
	
	public boolean misp;
	public int miss;

	public boolean nukp;
	public int nuke;
	
	
	
}
