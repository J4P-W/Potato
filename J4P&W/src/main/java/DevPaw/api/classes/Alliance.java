package DevPaw.api.classes;

import java.util.List;


import DevPaw.api.exceptions.UnsuccessfullAPIException;
import main.App;


public class Alliance {
	public boolean success;
    public String allianceid;
    public String name;
    public String acronym;
    public String score;
    public String color;
    public int members;
    public List<Integer> member_id_list;
    public int vmodemembers;
    public String accepting_members;
    public int applicants;
    public String flagurl;
    public String forumurl;
    public String irc;
    public double gdp;
    public int cities;
    public int soldiers;
    public int tanks;
    public int aircraft;
    public int ships;
    public int missiles;
    public int nukes;
    public int treasures;
    
    public Nation[] getNations() throws UnsuccessfullAPIException {
    	Nation[] nations = new Nation[member_id_list.size()];
    	for(int x = 0; x < nations.length; x++)
    		nations[x] = App.mainapi.getNation(member_id_list.get(x)+"");
    	return nations;
    }
    
}
