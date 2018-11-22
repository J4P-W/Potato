package DevPaw.browserators.classes;


import java.util.Date;

import com.gargoylesoftware.htmlunit.html.DomElement;

public class Reply {
	public String content;
	public Date date;
	public Reply(DomElement replyelement, Date date) {
		content = replyelement.asText();
		this.date = date;
	}
}
