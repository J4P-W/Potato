package DevPaw.browserators.handlers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import DevPaw.DevClient;
import DevPaw.browserators.classes.Reply;

public class ConversationHandler {
	private ArrayList<Reply> conversation;

	public ConversationHandler(DevClient dc, int messageid) throws IOException, ParseException {
		conversation = new ArrayList<>();
		HtmlPage messagepage = dc.wc.getPage("https://politicsandwar.com/inbox/message/id=" + messageid);
		HtmlDivision messageline = (HtmlDivision) messagepage.getByXPath("//*[@id=\"rightcolumn\"]/div[4]").get(0);
		Iterator<DomElement> i = messageline.getChildElements().iterator();
		SimpleDateFormat sdf = new SimpleDateFormat("DD/MM/yyyy EEE HH:MM a");
		while (i.hasNext()) {
			DomElement d = i.next();
			Iterator<DomElement> i2 = d.getChildElements().iterator();
			String yeet = i2.next().asXml();
			conversation.add(new Reply(i2.next(),
					sdf.parse(StringUtils.substringBetween(yeet, "Date:\r\n  </span>\r\n", "\r\n</p>"))));
		}
	}

	public List<Reply> getConversation() {
		return conversation;
	}
}
