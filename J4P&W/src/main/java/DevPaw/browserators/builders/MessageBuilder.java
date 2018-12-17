package DevPaw.browserators.builders;

import java.util.ArrayList;
import java.util.List;

import DevPaw.api.exceptions.UnsuccessfullAPIException;
import DevPaw.browserators.exceptions.CCLimitException;
import main.App;

public class MessageBuilder {
	private String to;
	private ArrayList<String> CC;
	private StringBuilder message;
	private String subject;
	public MessageBuilder(int nationid) throws UnsuccessfullAPIException {
		message = new StringBuilder();
		CC = new ArrayList<>();
		this.to = App.mainapi.getNation(nationid+"").leadername;
	}
	public MessageBuilder(String leadername) {
		message = new StringBuilder();
		this.to = leadername;
		CC = new ArrayList<>();
	}
	public void addLeader(String leadername) throws CCLimitException {
		if(CC.size() < 20)
			CC.add(leadername);
		else
			throw new CCLimitException("You can only CC up to 20 other nations!");
	}
	public void append(String s) {
		message.append(s);
	}
	public String getTo() {
		return to;
	}
	public List<String> getCC() {
		return CC;
	}
	public String getMessage() {
		return message.toString();
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public void setTo(String main) {
		this.to = main;
	}
	
	
}
