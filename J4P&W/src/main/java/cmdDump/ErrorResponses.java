package cmdDump;

import java.awt.Color;

import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.embed.EmbedBuilder;

public class ErrorResponses {
	public static void APIException(TextChannel c, Exception e) {
		EmbedBuilder embed = new EmbedBuilder();
		embed.setTitle("API Error!");
		embed.setColor(Color.red);
		embed.addField(e.getMessage(), "this is either the api's error, or a user error, try re-running the command");
		embed.addField("If the error persists: ", "https://discord.gg/R2DxPmD");
		embed.setFooter("You probably provided an invalid ID", "https://cdn.discordapp.com/icons/465555388490579969/b29bb8e4d298d674a88b81e85edbcfc1.png");
		c.sendMessage(embed);
	}
	public static void ParseException(TextChannel c, Exception e) {
		EmbedBuilder embed = new EmbedBuilder();
		embed.setTitle("Parsing Error!");
		embed.setColor(Color.red);
		embed.addField(e.getMessage(), "try rerunning the command, a report was already submitted incase");
		embed.setFooter("I honestly don't know why this would happen", "https://cdn.discordapp.com/icons/465555388490579969/b29bb8e4d298d674a88b81e85edbcfc1.png");
		c.sendMessage(embed);
	}
	public static void invalidFormatException(TextChannel c, Exception e) {
		EmbedBuilder embed = new EmbedBuilder();
		embed.setTitle("Command Error!");
		embed.setColor(Color.red);
		embed.addField(e.getMessage(), "try rerunning the command, try j!help for format");
		embed.setFooter("You might have forgotten an parameter", "https://cdn.discordapp.com/icons/465555388490579969/b29bb8e4d298d674a88b81e85edbcfc1.png");
		c.sendMessage(embed);
	}
	
}
