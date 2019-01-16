package discordListeners;

import java.util.ArrayList;
import java.util.List;

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
import cmdDump.organized.AllianceStuff;
import cmdDump.organized.Funny;
import cmdDump.organized.Help;
import cmdDump.organized.NationStuff;
import cmdDump.organized.WarStuff;
import main.GenBot1;

public class MessageSent implements MessageCreateListener {
	public List<String> commands;
	
	
	public MessageSent() {
		commands = new ArrayList<>();
		commands.add("j!ping");
		commands.add("j!mmonitor");
		commands.add("j!offmmonitor");
		commands.add("j!audit");
		commands.add("j!nation");
		commands.add("j!alliance");
		commands.add("j!market");
		commands.add("j!war");
		commands.add("j!vm");
		commands.add("j!defend");
		commands.add("j!undefend");
		commands.add("j!kill");
		commands.add("j!ghostme");
		commands.add("j!tier");
		commands.add("j!help");
		commands.add("j!analyze");
		commands.add("j!tier");
		commands.add("j!beiger");
		commands.add("j!unbeiger");
	}

	public void onMessageCreate(MessageCreateEvent event) {
		Message m = event.getMessage();
		String content = m.getContent();
		TextChannel c = event.getChannel();
		MessageAuthor a = m.getAuthor();
		String[] args = content.split(" ");
		Server s = event.getServer().get();
		try {
			if (commands.contains(args[0])) {
				Message processing = c.sendMessage("Processing...").get();
				if (!a.asUser().get().isBot()) {
					if (content.equalsIgnoreCase("j!ping")) {
						System.out.println("[" + s.getIdAsString() + "]: Ping");
						Cmds1.ping(c);
					}
					if (content.startsWith("j!analyze")) {
						System.out.println("[" + s.getIdAsString() + "]: analyze");
						if (args[1].equals("nation"))
							NationStuff.execute(m);
						else if (args[1].equals("alliance"))
							AllianceStuff.execute(m);
					} else if (content.startsWith("j!help")) {
						Help.help(m);
					} else if (content.startsWith("j!beiger")) {
						WarStuff.beiger(m);
					} else if (content.startsWith("j!unbeiger")) {
						WarStuff.removeBeiger(m);
					} else if (content.startsWith("j!mmonitor")) {
						System.out.println("[" + s.getIdAsString() + "]: MMonitor");
						CmdsPersistant.marketTracker(m);
					} else if (content.startsWith("j!offmmonitor")) {
						System.out.println("[" + s.getIdAsString() + "]: MMonitorOff");
						CmdsPersistant.stopTracker(m);
					} else if (content.startsWith("j!audit")) {
						System.out.println("[" + s.getIdAsString() + "]: Audit");
						Cmds1.audit(event);
					} else if (content.startsWith("j!nation")) {
						System.out.println("[" + s.getIdAsString() + "]: Nation");
						Cmds2.nationInfo(m);
					} else if (content.startsWith("j!alliance")) {
						System.out.println("[" + s.getIdAsString() + "]: Alliance");
						Cmds2.allianceInfo(m);
					} else if (content.startsWith("j!tier")) {
						System.out.println("[" + s.getIdAsString() + "]: Tier");
						Cmds2.tier(m);
					} else if (content.startsWith("j!market")) {
						System.out.println("[" + s.getIdAsString() + "]: Market");
						Cmds3.market(m);
					} else if (content.startsWith("j!war")) {
						System.out.println("[" + s.getIdAsString() + "]: War");
						Cmds3.war(m);
					} else if (content.startsWith("j!vm")) {
						System.out.println("[" + s.getIdAsString() + "]: Vaca");
						Cmds3.vms(m);
					} else if (content.startsWith("j!defend") && a.isServerAdmin()) {
						System.out.println("[" + s.getIdAsString() + "]: Defend");
						CmdsPersistant.defensiveWarTracker(m);
					} else if (content.startsWith("j!undefend") && a.isServerAdmin()) {
						System.out.println("[" + s.getIdAsString() + "]: Undefend");
						CmdsPersistant.removeDefensive(m);
					} else if (content.startsWith("j!ghostme")) {
						Funny.ghostPingeth(m);
					} else if (GenBot1.isAdmin(a.getIdAsString()) && content.equalsIgnoreCase("j!kill")) {
						System.out.println("[" + s.getIdAsString() + "]: Kill");
						Cmds1.kill(c);
					}
				}
				processing.delete();
			}
		} catch (Exception e) {
			c.sendMessage("```An Error Occurred!``` https://discord.gg/R2DxPmD Tech Support");
			e.printStackTrace();
		}
	}
}
