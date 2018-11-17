package DevPaw.webscrapers;

import DevPaw.utilities.SpeedUtils;


public class WebGeneral {
	public String url;
	public String content;
	
	public WebGeneral(String id) {
		url = id;
		content = SpeedUtils.speedURL(url);
	}
	
	public String getbetween(String a, String b) {
		int index = a.indexOf(a);
		return content.substring(index, a.indexOf(b, index));
	}
}
