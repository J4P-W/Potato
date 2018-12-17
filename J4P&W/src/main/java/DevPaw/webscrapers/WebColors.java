package DevPaw.webscrapers;


import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableCell;


public class WebColors {
	private static final String URL = "https://politicsandwar.com/leaderboards/display=color";
	public static boolean callme;
	static HashMap<String, String> colormap;
	static {
		update();
	}
	public static void update() {
		colormap = new HashMap<>();
		WebClient wb = new WebClient(BrowserVersion.CHROME);
		try {
			HtmlPage colors = wb.getPage(URL);
			wb.close();
			HtmlTable ht = (HtmlTable) colors.getByXPath("//*[@id=\"rightcolumn\"]/table").get(0);
			for(int x = 1; x < 17; x++) {
				HtmlTableCell color = ht.getCellAt(x, 0);
				HtmlTableCell val = ht.getCellAt(x, 5);
				colormap.put(((HtmlElement)color.getFirstChild()).getAttribute("title").toLowerCase(), val.asText());
			}
		} catch (Exception e) {e.printStackTrace();}
	}
	public static void autoUpdate(long ms) {
		Timer t = new Timer();
		t.schedule(new TimerTask() {
			public void run() {
				update();
			}
		}, 0, ms);
	}
	public static String getBonus(String color) {
		return colormap.get(color);
	}
	public static int getBonusAsInt(String color) {
		return Integer.parseInt(colormap.get(color).replaceAll("[$,]", "").trim());
	}
}
