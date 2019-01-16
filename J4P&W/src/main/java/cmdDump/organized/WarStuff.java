package cmdDump.organized;

import java.util.Optional;

import org.beryx.awt.color.ColorFactory;
import org.javacord.api.entity.channel.Channel;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.embed.EmbedBuilder;

import cmdDump.CmdsUtil;
import DevPaw.api.classes.Alliance;
import main.GenBot1;
import main.Init;

public class WarStuff {

	public static void beiger(Message m) throws Exception {
		TextChannel c = m.getChannel();
		String[] args = m.getContent().split(" ");
		int integer = -1;
		if(!args[1].equals("all"))
			try {
				integer = Integer.parseInt(args[1]);
				if(GenBot1.mainapi.getAlliance(args[1]).success)
					throw new NumberFormatException("");
			} catch(NumberFormatException exception) {
				c.sendMessage("Invalid ID");
				return;
			}
		final String channel = c.getIdAsString();
		final int inte = integer;
		Init.beigeExecutor.addConsumer(channel, list ->
			list.stream().filter(n -> args[1].equals("all") || n.allianceid==inte).forEach(n -> {
				EmbedBuilder embed = new EmbedBuilder();
				embed.setTitle("New Beige!");
				embed.setColor(ColorFactory.valueOf("beige"));
				embed.addField(CmdsUtil.hyperUrl("Nation", n.getUrl()), CmdsUtil.hyperUrl("Alliance", Alliance.getLink(n.allianceid)));
				embed.setTimestampToNow();
				Optional<Channel> o = GenBot1.api.getChannelById(channel);
				if(o.isPresent() && o.get() instanceof TextChannel)
					((TextChannel)o.get()).sendMessage(embed);
				else
					Init.beigeExecutor.removeConsumer(channel);
			})
		);
		c.sendMessage("Tracking will start now!");
	}
	
	public static void removeBeiger(Message m) {
		if (Init.beigeExecutor.removeConsumer(m.getChannel().getIdAsString()))
			m.getChannel().sendMessage("Removed!");
		else
			m.getChannel().sendMessage("No Beige Tracker found for this channel for that id");
	}

	private WarStuff() {}
}
