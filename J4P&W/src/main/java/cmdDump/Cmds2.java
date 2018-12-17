package cmdDump;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.beryx.awt.color.ColorFactory;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.emoji.Emoji;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.listener.message.reaction.ReactionAddListener;

import DevPaw.api.classes.Alliance;
import DevPaw.api.classes.Military;
import DevPaw.api.classes.Nation;
import DevPaw.api.exceptions.UnsuccessfullAPIException;
import DevPaw.utilities.SpeedUtils;
import main.App;

public class Cmds2 {
	public static void tier(Message m) {
		try {
			TextChannel c = m.getChannel();
			String[] args = m.getContent().split(" ");
			Alliance a = App.mainapi.getAlliance(args[1]);
			EmbedBuilder embed = new EmbedBuilder();
			embed.setColor(ColorFactory.valueOf(a.color));
			embed.setTitle(a.name + " (" + a.acronym +")'s City tier count");
			ArrayList<Integer> citycount = new ArrayList<>();
			int counter = 0;
			for(int x = 0; x < a.member_id_list.size(); x++) {
				Nation n = App.mainapi.getNation(a.member_id_list.get(x)+"");
				citycount.add(n.cities);
				if(n.cities > counter)
					counter = n.cities;
			}
			embed.addInlineField("```1 city```", "```"+Collections.frequency(citycount, 1)+"```");
			for(int x = 2; x <= counter; x++)
				embed.addInlineField("```"+x+" cities```", "```"+Collections.frequency(citycount, x)+"```");
			c.sendMessage(embed);
		} catch(UnsuccessfullAPIException error) {
			ErrorResponses.APIException(m.getChannel(), error);
		}
	}
	
	private Cmds2() {}
	
	
	public static void milit(Message m) throws InterruptedException, ExecutionException {
		try {
			TextChannel c = m.getChannel();
			Message prog = c.sendMessage("Wait up to a few minuets...").get();
			String[] args = m.getContent().split(" ");
			Alliance a = App.mainapi.getAlliance(args[1]);
			EmbedBuilder embed = new EmbedBuilder();
			embed.setTitle(a.name + "(" + a.acronym + ")");
			double avg = 0;
			int total = 0;
			for(int x = 0; x < a.member_id_list.size(); x++) {
				Military milit = new Military(a.member_id_list.get(x)+"");
				if(milit.prep==-1) total--;
				avg += milit.prep==-1?0:milit.prep;
				total++;
			}
			if(total != 0)
				avg /= total;
			char[] strn = new char[(int)(avg*10)];
			for(int x = 0; x < strn.length; x++)
				strn[x]= '=';
			System.out.println(avg);
			embed.addInlineField("Soldiers", a.soldiers+"");
			embed.addInlineField("Tanks", a.tanks+"");
			embed.addField("Airplanes", a.aircraft+"");
			embed.addField("Ships", a.ships+"");
			embed.addField("Militarization "+String.format("%3.1f", avg*100)+"%", "```|" + new String(strn)+">```");
			embed.setColor(ColorFactory.valueOf(a.color));
			c.sendMessage(embed);
			prog.delete();
		} catch(UnsuccessfullAPIException error) {
			ErrorResponses.APIException(m.getChannel(), error);
		}
	}
	
	public static void allianceInfo(Message m) throws InterruptedException, ExecutionException {
		try {
			TextChannel c = m.getChannel();
			String[] args = m.getContent().split(" ");
			Alliance a = App.mainapi.getAlliance(args[1]);
			EmbedBuilder embed = new EmbedBuilder();
			embed.setTitle(a.name + " (" + a.acronym + ")");
			embed.addInlineField("Accepting members", a.accepting_members+"#");
			embed.addInlineField("Applicants", a.applicants+"#");
			embed.addInlineField("VM moders", a.vmodemembers+"#");
			embed.addInlineField("Forum", a.forumurl+" -");
			embed.addInlineField("Discord", a.irc+" -");
			embed.addInlineField("Treasures", a.treasures+" -");
			embed.addField("GDP: ", a.gdp+"$");
			embed.addField("Score: ", "["+a.score+"]");
			embed.setColor(ColorFactory.valueOf(a.color));
			embed.setThumbnail(a.flagurl);
			embed.setTimestampToNow();
			embed.setAuthor(m.getAuthor());
			embed.setUrl("https://politicsandwar.com/alliance/id="+args[1]);
			Message m2 = c.sendMessage("React with :boom: to check military (removes in 5 min)",embed).get();
			m2.addReaction("\uD83D\uDCA5");
			ReactionAddListener reactionator = e -> {
				Emoji emoji = e.getReaction().get().getEmoji();
				if(emoji.equalsEmoji("\uD83D\uDCA5") && e.getReaction().get().getCount() > 1) {
					try {
						milit(m);
					} catch (Exception e1) {e1.printStackTrace();}
				}
			};
			m2.addReactionAddListener(reactionator).removeAfter(5, TimeUnit.MINUTES);
		} catch(UnsuccessfullAPIException error) {
			ErrorResponses.APIException(m.getChannel(), error);
		}
	}
	
	
	public static void nationInfo(Message m) {
		try {
			TextChannel c = m.getChannel();
			String[] args = m.getContent().split(" ");
			Nation n = App.mainapi.getNation(args[1]);
			Military milit = new Military(n);
			EmbedBuilder embed = new EmbedBuilder();
			embed.setTitle("Nation: " + n.nationid);
			embed.addInlineField("Leader: ", n.leadername);
			embed.addInlineField("Nation name: ", n.name);
			embed.addField("Ranking: ", n.nationrankstrings);
			embed.addInlineField("Color: ", n.color);
			embed.addInlineField("City Count: ", n.cities+"");
			embed.addField("Cities", SpeedUtils.listToString(n.cityids));
			embed.addField("Score: ", n.score);
			embed.addInlineField("Soldiers: ", n.soldiers+"/"+milit.barr*5000);
			embed.addInlineField("Tanks: ", n.tanks+"/"+milit.fact*250);
			embed.addField("Planes: ", n.aircraft+"/"+milit.hang*18);
			embed.addField("Ships: ", n.ships+"/"+milit.drys*5);
			embed.setAuthor(m.getAuthor());
			embed.setThumbnail(n.flagurl);
			embed.setUrl("https://politicsandwar.com/nation/id="+args[1]);
			embed.setColor(ColorFactory.valueOf(n.color));
			c.sendMessage(embed);
		} catch(UnsuccessfullAPIException error) {
			ErrorResponses.APIException(m.getChannel(), error);
		}
	}
	
}
