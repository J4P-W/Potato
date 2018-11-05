package cmdDump;

import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageAuthor;
import org.javacord.api.entity.server.Server;
import org.javacord.api.event.message.MessageCreateEvent;

import pawstuff.CityLayout;
import utils.AuditData;


public class Cmds1 {
	public static void setAudit(Server s, TextChannel c, Message m) {
		try {
			String json = new String(m.getAttachments().get(0).downloadAsByteArray().get());
			System.out.println(json);
			CityLayout cl = CityLayout.getCityLayout(json);
			AuditData.data.put(s.getIdAsString(), cl);
			c.sendMessage("Audit sucessfully updated!");
		} catch (Exception e) {
			e.printStackTrace();
			c.sendMessage("Audit update failed!!!!!");
		}
	}
	
	public static void ping(TextChannel c) {
		long start = System.currentTimeMillis();
		Message a = c.sendMessage("pinging...").join();
		long end = System.currentTimeMillis();
		c.deleteMessages(a).join();
		c.sendMessage("```Pong! " + (end - start) + "ms```");
	}
	
	public static void kill(TextChannel c) {
		c.sendMessage("```diff\n- Good bye cruel world ;(```");
		System.exit(0);
	}
	
	public static void audit(MessageCreateEvent e) {
		Message m = e.getMessage();
		String content = m.getContent();
		TextChannel c = e.getChannel();
		MessageAuthor a = m.getAuthor();
		String[] args = content.split(" ");
		Server s = e.getServer().get();
		CityLayout cl = AuditData.data.get(s.getIdAsString());
	}
}
