package discordListeners;

import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageAuthor;
import org.javacord.api.entity.server.Server;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import cmdDump.Cmds1;
import cmdDump.Cmds2;
import cmdDump.Cmds3;
import cmdDump.CmdsPersistant;
import main.App;

public class MessageSent implements MessageCreateListener {

	public void onMessageCreate(MessageCreateEvent event) {
		Message m = event.getMessage();
		String content = m.getContent();
		TextChannel c = event.getChannel();
		MessageAuthor a = m.getAuthor();
		//String[] args = content.split(" ");
		Server s = event.getServer().get();
		try {
			if(!a.asUser().get().isBot()) {
				if(content.equalsIgnoreCase("j!ping")) {
					System.out.println("["+s.getIdAsString()+"]: Ping");
					Cmds1.ping(c);
				}
				else if(content.startsWith("j!audit")) {
					System.out.println("["+s.getIdAsString()+"]: Audit");
					Cmds1.audit(event);
				}
				else if(content.startsWith("j!nation")) {
					System.out.println("["+s.getIdAsString()+"]: Nation");
					Cmds2.nationInfo(m);
				}
				else if(content.startsWith("j!alliance")) {
					System.out.println("["+s.getIdAsString()+"]: Alliance");
					Cmds2.allianceInfo(m);
				}
				else if(content.startsWith("j!tier")) {
					System.out.println("["+s.getIdAsString()+"]: Tier");
					Cmds2.tier(m);
				}
				else if(content.startsWith("j!market")) {
					System.out.println("["+s.getIdAsString()+"]: Market");
					Cmds3.market(m);
				}
				else if(content.startsWith("j!war")) {
					System.out.println("["+s.getIdAsString()+"]: War");
					Cmds3.War(m);
				}
				else if(content.startsWith("j!vm")) {
					System.out.println("["+s.getIdAsString()+"]: Vaca");
					Cmds3.vms(m);
				}
				else if(content.startsWith("j!defend")) {
					System.out.println("["+s.getIdAsString()+"]: Defend");
					CmdsPersistant.defensiveWarTracker(m);
				}
				else if(isAdmin(a.getIdAsString())) {
					if(content.equalsIgnoreCase("j!kill")) {
						System.out.println("["+s.getIdAsString()+"]: Kill");
						Cmds1.kill(c);	
					}
				}
			}
		} catch(Exception e) {
			c.sendMessage("```An Error Occurred!``` https://discord.gg/R2DxPmD Tech Support");
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
