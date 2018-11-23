package DevPaw.api.classes.Nations;

import java.io.Serializable;
import java.util.List;


public class Nations implements Serializable {
	private static final long serialVersionUID = 4824463550348651394L;
	public boolean success;
    public List<LNation> nations;
    
    public int[] AllianceFinder(int alliance, int members) { //
		int[] nationlist = new int[members+1];
		int counter2 = 0;
		for(int x = 0; x < nations.size(); x++)
			if(nations.get(x).allianceid == alliance)
				nationlist[counter2++] = nations.get(x).nationid;
		return nationlist;
	}
    public String ItName(int id) { //ID to nation name
    	for(int x = 0; x < nations.size(); x++)
    		if(nations.get(x).nationid==id)
    			return nations.get(x).nation;
    	return "Fail!!!";
    }
    public LNation ItNation(int id) { //ID to nation 
    	for(int x = 0; x < nations.size(); x++)
    		if(nations.get(x).nationid==id)
    			return nations.get(x);
    	return null;
    }
}
