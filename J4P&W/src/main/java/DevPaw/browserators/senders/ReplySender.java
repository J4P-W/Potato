package DevPaw.browserators.senders;

import java.io.IOException;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTextArea;

import DevPaw.DevClient;
import DevPaw.browserators.builders.ReplyBuilder;

public class ReplySender {
	public String messageid;
	public DevClient dc;

	public ReplySender(String messageid, DevClient dc) {
		this.messageid = messageid;
		this.dc = dc;
	}

	public void send(ReplyBuilder reply) throws IOException {
		HtmlPage hp = dc.wc.getPage("https://politicsandwar.com/inbox/message/id=" + messageid);
		((HtmlTextArea) hp.getElementByName("body")).setText(reply.getContent());
		hp.getElementByName("sndmsg").click();
	}
}
