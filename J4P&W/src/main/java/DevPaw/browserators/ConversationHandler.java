package DevPaw.browserators;

import java.io.IOException;
import java.net.MalformedURLException;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.javascript.host.Element;

public class ConversationHandler {
	public ConversationHandler(MessageHandler mh,int messageid) throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		HtmlPage messagepage = mh.wc.getPage("https://politicsandwar.com/inbox/message/id="+messageid);
		HtmlDivision messageline = (HtmlDivision) messagepage.getByXPath("//*[@id=\"rightcolumn\"]/div[4]").get(0);
		System.out.println(messageline.asXml());
	}
}
