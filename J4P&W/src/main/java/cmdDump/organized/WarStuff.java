package cmdDump.organized;

import java.io.IOException;
import java.util.List;

import org.beryx.awt.color.ColorFactory;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.embed.EmbedBuilder;

import cmdDump.CmdsUtil;
import DevPaw.api.classes.Alliance;
import DevPaw.api.classes.Nations.LNation;
import DevPaw.api.listeners.DevListener;
import main.Init;

public class WarStuff {

	public static void beiger(Message m) throws IOException {
		TextChannel c = m.getChannel();
		String[] args = m.getContent().split(" ");
		Init.beigeExecutor.listeners.put(c.getIdAsString(), new DevListener<LNation>() {
			private static final long serialVersionUID = 4009770055504984598L;
			@Override
			public void execute(List<LNation> data) {
				for(LNation n : data)
					if(n.allianceid == Integer.parseInt(args[1])) {
						EmbedBuilder embed = new EmbedBuilder();
						embed.setTitle("New Beige!");
						embed.setColor(ColorFactory.valueOf("beige"));
						embed.addField(CmdsUtil.hyperUrl("Nation", n.getUrl()), CmdsUtil.hyperUrl("Alliance", Alliance.getLink(n.allianceid+"")));
					}
			}
		});
		Init.beigeExecutor.listendb.save(Init.beigeExecutor.listeners);
		c.sendMessage("Tracking will start now!");
	}
	
	public static void removeBeiger(Message m) throws IOException {
		if (Init.beigeExecutor.listeners.containsKey(m.getChannel().getIdAsString())) {
			Init.beigeExecutor.listeners.remove(m.getChannel().getIdAsString());
			Init.beigeExecutor.listendb.save(Init.beigeExecutor.listeners);
			m.getChannel().sendMessage("Removed!");
		} else {
			m.getChannel().sendMessage("No Beige Tracker found for this channel for that id");
		}

	}

	private WarStuff() {}
}
