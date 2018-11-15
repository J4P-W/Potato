package discordListeners;

import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageAuthor;
import org.javacord.api.entity.server.Server;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import cmdDump.Cmds1;
import main.App;

public class MessageSent implements MessageCreateListener {

	public void onMessageCreate(MessageCreateEvent event) {
		Message m = event.getMessage();
		String content = m.getContent();
		TextChannel c = event.getChannel();
		MessageAuthor a = m.getAuthor();
		//String[] args = content.split(" ");
		Server s = event.getServer().get();
		System.out.println(content.startsWith("j!setaudit") + " " + !a.asUser().get().isBot());
		try {
			if(!a.asUser().get().isBot()) {
				System.out.println("Hello");
				if(content.equalsIgnoreCase("j!ping")) {
					System.out.println("["+s.getIdAsString()+"]: Ping");
					Cmds1.ping(c);
				}
				else if(content.startsWith("j!audit")) {
					System.out.println("["+s.getIdAsString()+"]: Audit");
					Cmds1.audit(event);
				}
				else if(content.startsWith("j!setaudit")) {
					System.out.println("["+s.getIdAsString()+"]: SetAudit");
					Cmds1.setAudit(s, c, m);
				}
				else if(isAdmin(a.getIdAsString())) {
					System.out.println("["+s.getIdAsString()+"]: Admin");
					if(content.equalsIgnoreCase("j!kill")) {
						System.out.println("["+s.getIdAsString()+"]: Kill");
						Cmds1.kill(c);	
					}
				}
				
			}
		} catch(Exception e) {
			c.sendMessage("An Error Occurred! https://discord.gg/R2DxPmD Tech Support");
			e.printStackTrace();
		}
	}
	public boolean isAdmin(String s) {
		for(int x = 0; x < App.administrators.length; x++)
			if(s.contentEquals(App.administrators[x]))
				return true;
		return false;
	}
}
