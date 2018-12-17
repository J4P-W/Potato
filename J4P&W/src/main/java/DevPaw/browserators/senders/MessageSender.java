package DevPaw.browserators.senders;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTextArea;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

import DevPaw.DevClient;
import DevPaw.browserators.builders.MessageBuilder;

public class MessageSender {
	private WebClient wc;
	public MessageSender(DevClient dc) {
		wc = dc.wc;
	}
	public void send(MessageBuilder mb) throws IOException {
		HtmlPage hp = wc.getPage("https://politicsandwar.com/inbox/message/");
		((HtmlTextInput) hp.getElementByName("receiver")).setValueAttribute(mb.getTo());
		((HtmlTextInput) hp.getElementByName("subject")).setText(mb.getSubject());
		try {
			StringBuilder sum = new StringBuilder();
			for(int y = 0; y < mb.getCC().size()-1; y++)
				sum.append(mb.getCC().get(y)+",");
			sum.append(mb.getCC().get(mb.getCC().size()-1));
			((HtmlTextInput)hp.getElementByName("carboncopy")).type(sum.toString());
		} catch(Exception e) {e.printStackTrace();}
		((HtmlTextArea) hp.getElementByName("body")).setText(mb.getMessage());
		hp = hp.getElementByName("sndmsg").click();
		File file = File.createTempFile("HtmlUnit", ".html");
		Files.delete(file.toPath());
        hp.save(file);
        Runtime.getRuntime().exec("C:/Program Files/Internet Explorer/iexplore.exe " + file);
	}

}
