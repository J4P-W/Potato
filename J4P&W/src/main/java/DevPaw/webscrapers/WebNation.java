package DevPaw.webscrapers;

import DevPaw.utilities.SpeedUtils;


public class WebNation {
	public Thread parsethread;
	
	public String url;
	public String content;
	
	private String bounties;
	private String warsWon;
	private String warsLost;
	private String Description;
	
	public WebNation(String id) {
		url = "https://politicsandwar.com/nation/id="+id;
		content = SpeedUtils.speedURL(url);
		parsethread = new Thread(() -> {
			bounties = getbetween("<tr><td>Total Value of All Bounties:</td><td>", "</td></tr>");
			warsWon = getbetween("<tr><td>Wars Won:</td><td>","</td></tr>");
			warsLost = getbetween("<tr><td>Wars Lost:</td><td>","</td></tr>");
			Description = getbetween("<p style=\"text-align:justify;\">","</p>");
		});
		parsethread.start();
	}
	public WebNation(int id) {
		url = "https://politicsandwar.com/nation/id="+id;
		content = SpeedUtils.speedURL(url);
	}
	
	public String getbetween(String a, String b) {
		int index = content.indexOf(a)+a.length();
		return content.substring(index, content.indexOf(b, index));
	}
	
	public String totalBounties() {
		try {
			parsethread.join();
		} catch(Exception e) {e.printStackTrace();}
		return bounties;
	}
	
	public String getWarsWon() {
		try {
			parsethread.join();
		} catch(Exception e) {e.printStackTrace();}
		return warsWon;
	}
	
	public String getWarsLost() {
		try {
			parsethread.join();
		} catch(Exception e) {e.printStackTrace();}
		return warsLost;
	}
	
	public String getDesc() {
		try {
			parsethread.join();
		} catch(Exception e) {e.printStackTrace();}
		return Description;
	}
	
	
}
