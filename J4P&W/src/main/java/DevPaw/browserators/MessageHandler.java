package DevPaw.browserators;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTable;

import DevPaw.browserators.classes.Message;
import DevPaw.browserators.exceptions.InvalidLoginException;

public class MessageHandler {
	protected WebClient wc;
	private ArrayList<Message> messages;
	public MessageHandler(String email, String password, int messagecount) throws FailingHttpStatusCodeException, MalformedURLException, IOException, InvalidLoginException, IndexOutOfBoundsException, ParseException {
		wc = new WebClient(BrowserVersion.CHROME);
		messages = new ArrayList<Message>();
		{HtmlPage login = (HtmlPage) wc.getPage("https://politicsandwar.com/login/");
		HtmlForm loginForm = login.getForms().get(0);
		loginForm.getInputByName("email").setValueAttribute(email);
		loginForm.getInputByName("password").setValueAttribute(password);
		HtmlSubmitInput submitLogin = loginForm.getInputByValue("Login");
		login = submitLogin.click();
		if(login.getUrl().toString().contentEquals("https://politicsandwar.com/login/"))
			throw new InvalidLoginException("Invalid login!");}
		HtmlPage inbox = wc.getPage(String.format("https://politicsandwar.com/index.php?id=16&maximum=%d&minimum=0&od=DESC&searchTerm=&search=Go", messagecount));
		HtmlTable messagetable = (HtmlTable) inbox.getByXPath("//*[@id=\"rightcolumn\"]/form[2]/table").get(0);
		for(int x = 1; x < messagetable.getRowCount(); x++)
			messages.add(new Message(messagetable.getRow(x)));
	}
	public List<Message> getUnread() {
		List<Message> returner = new ArrayList<Message>();
		for(Message m:messages)
			if(m.read)
				returner.add(m);
		return returner;
	}
	public List<Message> getAll() {return messages;}
	public List<Message> getRead() {
		List<Message> returner = new ArrayList<Message>();
		for(Message m:messages)
			if(!m.read)
				returner.add(m);
		return returner;
	}
	
	
}
