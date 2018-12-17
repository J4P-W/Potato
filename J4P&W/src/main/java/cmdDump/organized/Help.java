package cmdDump.organized;

import java.awt.Color;

import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.embed.EmbedBuilder;

public class Help {

	public static void help(Message m) {
		EmbedBuilder embed = new EmbedBuilder()
				.setFooter("[aid] = alliance id, [nid] = nation id, [wid] = warid")
				.addField("```j!ping```", "checks bot's ping to discord server")
				.addField("```j!audit [aid]```", "audits an alliance, warning, takes awhile")
				.addField("```j!nation [nid]```", "gives information on the nation")
				.addField("```j!alliance [aid]```", "gives information on the alliance")
				.addField("```j!market```", "gives the current market status")
				.addField("```j!war [wid]```", "provides information about the given war")
				.addField("```j!vm [aid]```", "gives a list of all the members of an alliance in vacation mode")
				.addField("```j!defend [aid]```", "will send message in alliance with war details when a war is declared on the alliance")
				.addField("```j!undefend [aid]```", "removes the feed from ^")
				.addField("```j!ghostme```", ":)")
				.addField("```j!tier [aid]```", "lists nations and their city count")
				.setDescription("j! is the prefix, do not include the []'s and edits don't count, resend the message.")
				.setColor(new Color((int)(Math.random()*156)+100,(int)(Math.random()*156)+100,(int)(Math.random()*156)+100));
		m.getChannel().sendMessage(embed);
	}

}
