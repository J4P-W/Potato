package DevPaw.api.classes;

import java.util.List;

import DevPaw.api.PaWAPI;
import DevPaw.api.util.ResourceGen;




public class Nation {
	    public List<String> cityids;
	    public int cityprojecttimerturns;
	    public boolean success;
	    public String nationid;
	    public String name;
	    public String prename;
	    public String continent;
	    public String socialpolicy;
	    public String color;
	    public int minutessinceactive;
	    public String uniqueid;
	    public String government;
	    public String domestic_policy;
	    public String war_policy;
	    public String founded;
	    public int daysold;
	    public String alliance;
	    public String allianceposition;
	    public String allianceid;
	    public String flagurl;
	    public String leadername;
	    public String title;
	    public String ecopolicy;
	    public String approvalrating;
	    public String nationrank;
	    public String nationrankstrings;
	    public int nrtotal;
	    public int cities;
	    public String latitude;
	    public String longitude;
	    public String score;
	    public String population;
	    public String gdp;
	    public double totalinfrastructure;
	    public double landarea;
	    public String soldiers;
	    public String soldiercasualties;
	    public String soldierskilled;
	    public String tanks;
	    public String tankcasualties;
	    public String tankskilled;
	    public String aircraft;
	    public String aircraftcasualties;
	    public String aircraftkilled;
	    public String ships;
	    public String shipcasualties;
	    public String shipskilled;
	    public String missiles;
	    public String missilelaunched;
	    public String missileseaten;
	    public String nukes;
	    public String nukeslaunched;
	    public String nukeseaten;
	    public String infdesttot;
	    public String infraLost;
	    public String moneyLooted;
	    public String ironworks;
	    public String bauxiteworks;
	    public String armsstockpile;
	    public String emgasreserve;
	    public String massirrigation;
	    public String inttradecenter;
	    public String missilelpad;
	    public String nuclearresfac;
	    public String irondome;
	    public String vitaldefsys;
	    public String intagncy;
	    public String uraniumenrich;
	    public String propbureau;
	    public String cenciveng;
	    public String vmode;
	    public int offensivewars;
	    public int defensivewars;
	    public List<Object> offensivewar_ids;
	    public List<Object> defensivewar_ids;
	    public int beige_turns_left;
	    public double radiation_index;
	    public String season;
	    
	    public ResourceGen getRevenue() {
	    	try {
	    		if(!success)
	    			return null;
	    		int allianceID = Integer.parseInt(allianceid);
	    		String color1 = PaWAPI.getAlliance(allianceID+"").color;
	    		String color2 = color;
	    		double boost = 1.0001;
	    		if(color1.equalsIgnoreCase(color2))
	    			boost = 1.0303;
	    		else if(color2.equalsIgnoreCase("beige"))
	    			boost = 1.0505;
	    		String[] cities = cityids.toArray(new String[cityids.size()]);
	    		if(!(cities.length > 10))
	    			boost += 0.1;
	    		int pop = Integer.parseInt(population);
	    		ResourceGen rg = new ResourceGen();
	    		boolean hasIronworks = ironworks.contains("1");
	    		boolean hasBauxworks = bauxiteworks.contains("1");
	    		boolean hasArmsStock = armsstockpile.contains("1");
	    		boolean hasGasRes = emgasreserve.contains("1");
	    		boolean hasMass = massirrigation.contains("1");
	    		boolean hasUran = uraniumenrich.contains("1");
	    		for(String ca:cities) {
	    			City c = PaWAPI.getCity(ca);
	    			rg.monet += (((725.0 * c.commerce/50.0) +725.0)*c.population/1000.0)*boost;
	    			//System.out.println("Money: " + (((725.0 * getInteger("commerce",apiRaw)/50.0) +725.0)*getInteger("population",apiRaw)/1000.0)*boost);
	    			//System.out.println(""+apiRaw);
	    			
	    			double infra = totalinfrastructure;
	    			double uinfra = infra;
	    			boolean powered = c.powered.equalsIgnoreCase("Yes");
	    			double land;
	    			try {
	    				land = Double.parseDouble(c.land);
	    			} catch(Exception e) {
	    				e.printStackTrace();
	    				land = 0;
	    				//System.out.println(apiRaw);
	    			}
	    			
	    			//1.2 per 100 infra, 500 max
	    			if(Integer.parseInt(c.imp_coalpower)!=0) {
	    					for(int x = Integer.parseInt(c.imp_coalpower); x > 0; x--) 
	    						for(int y = 0; y < 5; y++)
		    						if(uinfra >= 100) {
		    							uinfra-=100;
		    							rg.coal -= 1.2;
		    						}
		    						else {
		    							rg.coal -= 1.2;
		    							uinfra = 0;
		    						}
	    					rg.monet -= 1200*Integer.parseInt(c.imp_coalpower);
	    			}
	    			//1.2 per 100 infra, 500 max
		    		if(Integer.parseInt(c.imp_oilpower)!=0) {
		    			for(int x = Integer.parseInt(c.imp_oilpower); x > 0; x--) 
		    				for(int y = 0; y < 5; y++)
								if(uinfra >= 100) {
									uinfra-=100;
									rg.oil -= 1.2;
								}
								else {
									rg.oil -= 1.2;
									uinfra = 0;
								}
		    			rg.monet -= 1800*Integer.parseInt(c.imp_oilpower);
		    		}
		    		//1.2 per 1k infra 2k max
		    		if(Integer.parseInt(c.imp_nuclearpower)!=0) {
		    			for(int x = Integer.parseInt(c.imp_nuclearpower); x > 0; x--) 
		    				for(int y = 0; y < 2; y++)
								if(uinfra >= 1000) {
									uinfra-=1000;
									rg.uran -= 1.2;
								}
								else {
									rg.uran -= 1.2;
									uinfra = 0;
								}
		    			rg.monet -= 10500*Integer.parseInt(c.imp_nuclearpower);
		    		}
		    		if(Integer.parseInt(c.imp_windpower)!=0) 
		    			rg.monet -= 500*Integer.parseInt(c.imp_windpower);
		    		//4.16666667
		    		
		    		if(Integer.parseInt(c.imp_coalmine)!=0)  {
		    			rg.coal += formula(Integer.parseInt(c.imp_coalmine),12,3); //not sure
		    			rg.monet -= 400*Integer.parseInt(c.imp_coalmine);
		    		}
		    		if(Integer.parseInt(c.imp_oilwell)!=0)  {
		    			rg.oil += formula(Integer.parseInt(c.imp_oilwell),12,3);
		    			rg.monet -= 600*Integer.parseInt(c.imp_oilwell);
		    		}
		    		if(Integer.parseInt(c.imp_bauxitemine)!=0)  {
		    			rg.baux += formula(Integer.parseInt(c.imp_bauxitemine),6,3); //not sure
		    			rg.monet -= 1600*Integer.parseInt(c.imp_bauxitemine);
		    		}
		    		if(Integer.parseInt(c.imp_ironmine)!=0)  {
		    			rg.iron += formula(Integer.parseInt(c.imp_ironmine),6,3);
		    			rg.monet -= 1600*Integer.parseInt(c.imp_ironmine);
		    		}
		    		if(Integer.parseInt(c.imp_leadmine)!=0)  {
		    			rg.lead += formula(Integer.parseInt(c.imp_leadmine),10,3); //not sure
		    			rg.monet -= 1500*Integer.parseInt(c.imp_leadmine);
		    		}
		    		if(Integer.parseInt(c.imp_farm)!=0)  {
		    			rg.food += formula(Integer.parseInt(c.imp_farm),20,(int)(land/25))*((hasMass)?1.2:1);
		    			rg.monet -= 300*Integer.parseInt(c.imp_farm);
		    		}
		    		if(Integer.parseInt(c.imp_uramine)!=0)  {
		    			rg.uran += formula(Integer.parseInt(c.imp_uramine),3,3)*((hasUran)?2:1);
		    			rg.monet -= 5000*Integer.parseInt(c.imp_uramine);
		    		}
		    		if(powered) {
			    		if(Integer.parseInt(c.imp_gasrefinery)!=0)  {
			    			rg.gaso += formula(Integer.parseInt(c.imp_gasrefinery),5,6)*((hasGasRes)?2:1);
			    			rg.monet -= 4000*Integer.parseInt(c.imp_gasrefinery);
			    			rg.oil -= formula(Integer.parseInt(c.imp_gasrefinery),5,3)*((hasGasRes)?2:1);
			    		}
			    		if(Integer.parseInt(c.imp_steelmill)!=0)  {
			    			rg.steel += formula(Integer.parseInt(c.imp_steelmill),5,9)*((hasIronworks)?1.36:1);
			    			rg.monet -= 4000*Integer.parseInt(c.imp_steelmill);
			    			rg.coal -= formula(Integer.parseInt(c.imp_steelmill),5,3)*((hasIronworks)?1.36:1);
			    			rg.iron -= formula(Integer.parseInt(c.imp_steelmill),5,3)*((hasIronworks)?1.36:1);
			    		}
			    		if(Integer.parseInt(c.imp_aluminumrefinery)!=0)  {
			    			rg.alum += formula(Integer.parseInt(c.imp_aluminumrefinery),5,9)*(1+3/50)*((hasBauxworks)?1.36:1);
			    			rg.monet -= 2500*Integer.parseInt(c.imp_aluminumrefinery);
			    			rg.baux -= formula(Integer.parseInt(c.imp_aluminumrefinery),5,3)*((hasBauxworks)?1.36:1);
			    			
			    		}
			    		if(Integer.parseInt(c.imp_munitionsfactory)!=0)  {
			    			rg.munit += formula(Integer.parseInt(c.imp_munitionsfactory),5,18)*((hasArmsStock)?1.34:1);
			    			rg.monet -= 3500*Integer.parseInt(c.imp_munitionsfactory);
			    			rg.lead -= formula(Integer.parseInt(c.imp_munitionsfactory),5,6)*((hasArmsStock)?1.34:1);
			    		}
			    		if(Integer.parseInt(c.imp_policestation)!=0)
			    			rg.monet -= 750*Integer.parseInt(c.imp_policestation);
			    		if(Integer.parseInt(c.imp_hospital)!=0)
			    			rg.monet -= 1000*Integer.parseInt(c.imp_hospital);
			    		if(Integer.parseInt(c.imp_recyclingcenter)!=0)
			    			rg.monet -= 2500*Integer.parseInt(c.imp_recyclingcenter);
			    		if(Integer.parseInt(c.imp_subway)!=0)
			    			rg.monet -= 3250*Integer.parseInt(c.imp_subway);
			    		if(Integer.parseInt(c.imp_supermarket)!=0)
			    			rg.monet -= 600*Integer.parseInt(c.imp_supermarket);
			    		if(Integer.parseInt(c.imp_bank)!=0)
			    			rg.monet -= 1800*Integer.parseInt(c.imp_bank);
			    		if(Integer.parseInt(c.imp_mall)!=0)
			    			rg.monet -= 5400*Integer.parseInt(c.imp_mall);
			    		if(Integer.parseInt(c.imp_stadium)!=0)
			    			rg.monet -= 12150*Integer.parseInt(c.imp_stadium);
			    		if(Integer.parseInt(c.imp_barracks)!=0) 
			    			rg.barr+= Integer.parseInt(c.imp_barracks);
			    		if(Integer.parseInt(c.imp_factory)!=0) 
			    			rg.fact += Integer.parseInt(c.imp_factory);
			    		if(Integer.parseInt(c.imp_hangar)!=0) 
			    			rg.hang += Integer.parseInt(c.imp_hangar);
			    		if(Integer.parseInt(c.imp_drydock)!=0) 
			    			rg.dry += Integer.parseInt(c.imp_drydock);
		    		}
	    		}
	    		if(offensivewars != 0 || defensivewars != 0) {
	    			rg.food -= (pop/1000+Integer.parseInt(soldiers)*0.002);
	        		rg.monet -= Integer.parseInt(soldiers)*1.88;
	        		rg.monet -= Integer.parseInt(tanks)*75;
	        		rg.monet -= Integer.parseInt(aircraft)*750;
	        		rg.monet -= Integer.parseInt(ships)*5625;
	        		rg.monet -= Integer.parseInt(missiles)*31500;
	        		rg.monet -= Integer.parseInt(nukes)*52500;
	    		} else {
	    			rg.food -= (pop/1000+Integer.parseInt(soldiers)*0.00133333333);
	        		rg.monet -= Integer.parseInt(soldiers)*1.25;
	        		rg.monet -= Integer.parseInt(tanks)*50;
	        		rg.monet -= Integer.parseInt(aircraft)*500;
	        		rg.monet -= Integer.parseInt(ships)*3750;
	        		rg.monet -= Integer.parseInt(missiles)*21000;
	        		rg.monet -= Integer.parseInt(nukes)*35000;
	    		}
	    		return rg;
	    	} catch(Exception e1) {e1.printStackTrace();}
			return null;
	    }
		public static double formula(int amount, int max, int per) {
			return amount*per+((amount-1.0)/(max-1.0)*.5*per*amount);
		}
}
