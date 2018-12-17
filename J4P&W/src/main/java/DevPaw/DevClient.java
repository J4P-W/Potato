package DevPaw;

import java.io.IOException;
import java.util.logging.Level;

import org.apache.commons.logging.LogFactory;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;

import DevPaw.browserators.exceptions.InvalidLoginException;

public class DevClient {
	public WebClient wc = new WebClient(BrowserVersion.CHROME);
	public void login(String email, String password) throws IOException, InvalidLoginException {
		LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog");
		java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.OFF); 
		java.util.logging.Logger.getLogger("org.apache.commons.httpclient").setLevel(Level.OFF);
		HtmlPage login = wc.getPage("https://politicsandwar.com/login/");
		HtmlForm loginForm = login.getForms().get(0);
		loginForm.getInputByName("email").setValueAttribute(email);
		loginForm.getInputByName("password").setValueAttribute(password);
		HtmlSubmitInput submitLogin = loginForm.getInputByValue("Login");
		login = submitLogin.click();
		if(login.getUrl().toString().contentEquals("https://politicsandwar.com/login/"))
			throw new InvalidLoginException("Invalid login!");
	}
}
