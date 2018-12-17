package DevPaw.utilities;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import DevPaw.api.classes.Alliances.Alliances;
import DevPaw.api.classes.Alliances.LAlliance;

public class SpeedUtils { // Extremely fast and optimized code dumps, pure performance, best u don't try to read any of it after u put it here.

	public static String speedURL(String url) { // Faster in general
		try {
			StringBuilder sb = new StringBuilder();
			BufferedReader in = new BufferedReader(new InputStreamReader(new URL(url).openStream()));
			String inputLine = in.readLine();
			System.out.println(url+" returned " + inputLine);
			while(inputLine != null) {
				sb.append(inputLine);
				inputLine = in.readLine();
			}
			in.close();
			return sb.toString();
		} catch(Exception e) {e.printStackTrace();}
        return null;
	}

	private SpeedUtils() {}
	
	public static <K, V> List<V> getAllKeysForValue(Map<K, V> mapOfWords, K key) {
		List<V> listOfKeys = null;
		if(mapOfWords.containsKey(key)) {
			listOfKeys = new ArrayList<>();
			for (Entry<K, V> entry : mapOfWords.entrySet()) 
				if (entry.getKey().equals(key))
					listOfKeys.add(entry.getValue());
				
		}
		return listOfKeys;	
	}
	
	public static String listToString(List<?> s) {
		StringBuilder sb = new StringBuilder();
		sb.append("[ " + s.get(0));
		for(int x = 1; x < s.size()-1; x++)
			sb.append(s.get(x)+" ,");
		sb.append(s.get(s.size()-1)+" ]");
		return sb.toString();
	}
	
	public static String allianceNameToID(String name,Alliances a) {
		for(LAlliance al:a.alliances)
			if(al.name.contentEquals(name))
				return al.id;
		return "failed";
	}

}
