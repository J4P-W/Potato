package DevPaw.browserators.classes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.gargoylesoftware.htmlunit.html.HtmlTableRow;


public class Message {
	public boolean read;
	public String subject;
	public int id;
	public Date time;
	public int senderid;
	
	public Message(HtmlTableRow htmlelement) throws IndexOutOfBoundsException, ParseException {
		read = false;
		SimpleDateFormat dateformat = new SimpleDateFormat("DD/MM/YYYY EEEE HH:MM a");
		time = dateformat.parse(htmlelement.getCell(1).asText().replaceAll("\n", " ").replaceAll("\r", "")); 
		id = Integer.parseInt(StringUtils.substringBetween(htmlelement.getCell(2).asXml(),"<td>\r\n  <a href=\"https://politicsandwar.com/inbox/message/id=", "\">\r\n"));
		if(htmlelement.getAttribute("class").contentEquals("bold"))
			read = true;
		senderid = Integer.parseInt(StringUtils.substringBetween(htmlelement.getCell(3).asXml(),"<a href=\"https://politicsandwar.com/nation/id=","\" data-toggle=\"tooltip\" title=\"\" data-original-title=\""));
	}
}
