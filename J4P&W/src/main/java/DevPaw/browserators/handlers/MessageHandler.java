package DevPaw.browserators.handlers;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;

import DevPaw.DevClient;
import DevPaw.browserators.classes.Message;

public class MessageHandler {
	private ArrayList<Message> messages;

	public MessageHandler(DevClient dc, int messagecount) throws IOException, ParseException {
		messages = new ArrayList<>();
		HtmlPage inbox = dc.wc.getPage(String.format(
				"https://politicsandwar.com/index.php?id=16&maximum=%d&minimum=0&od=DESC&searchTerm=&search=Go",
				messagecount));
		HtmlTable messagetable = (HtmlTable) inbox.getByXPath("//*[@id=\"rightcolumn\"]/form[2]/table").get(0);
		for (int x = 1; x < messagetable.getRowCount(); x++)
			messages.add(new Message(messagetable.getRow(x)));
	}

	public List<Message> getUnread() {
		List<Message> returner = new ArrayList<>();
		for (Message m : messages)
			if (m.read)
				returner.add(m);
		return returner;
	}

	public List<Message> getAll() {
		return messages;
	}

	public List<Message> getRead() {
		List<Message> returner = new ArrayList<>();
		for (Message m : messages)
			if (!m.read)
				returner.add(m);
		return returner;
	}

}
