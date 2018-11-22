package DevPaw.browserators;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.lang3.StringUtils;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import DevPaw.browserators.classes.Reply;

public class ConversationHandler {
	private ArrayList<Reply> conversation;
	public ConversationHandler(MessageHandler mh,int messageid) throws FailingHttpStatusCodeException, MalformedURLException, IOException, ParseException {
		conversation = new ArrayList<Reply>();
		HtmlPage messagepage = mh.wc.getPage("https://politicsandwar.com/inbox/message/id="+messageid);
		HtmlDivision messageline = (HtmlDivision) messagepage.getByXPath("//*[@id=\"rightcolumn\"]/div[4]").get(0);
		Iterator<DomElement> i = messageline.getChildElements().iterator();
		SimpleDateFormat sdf = new SimpleDateFormat("DD/MM/YYYY EEE HH:MM a");
		while(i.hasNext()) {
			DomElement d = i.next();
			Iterator<DomElement> i2 = d.getChildElements().iterator();
			System.out.println(i2.next().asXml());
			conversation.add(new Reply(i2.next(), sdf.parse(StringUtils.substringBetween("<span class=\"bold\">\r\n    Date:\r\n  </span>", "<a href=\"https://politicsandwar.com/account/block="))));
		}
	}
	
	public ArrayList<Reply> getConversation() {
		return conversation;
	}
}
