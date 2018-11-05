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

	@Override
	public void onMessageCreate(MessageCreateEvent event) {
		Message m = event.getMessage();
		String content = m.getContent();
		TextChannel c = event.getChannel();
		MessageAuthor a = m.getAuthor();
		//String[] args = content.split(" ");
		Server s = event.getServer().get();
		if(content.equalsIgnoreCase("j!ping"))
			Cmds1.ping(c);
		else if(content.startsWith("j!audit")) {
			Cmds1.audit(event);
		}
		else if(isAdmin(a.getIdAsString())) {
			if(content.equalsIgnoreCase("j!kill"))
				Cmds1.kill(c);
			
		}
		else if(a.canCreateChannelsOnServer()) {
			if(content.startsWith("j!setaudit"))
				Cmds1.setAudit(s, c, m);
		}
	}
	public boolean isAdmin(String s) {
		for(int x = 0; x < App.administrators.length; x++)
			if(s.contentEquals(App.administrators[x]))
				return true;
		return false;
	}
}
