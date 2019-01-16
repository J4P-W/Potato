package cmdDump;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

import org.javacord.api.entity.channel.Channel;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.embed.EmbedBuilder;

import DevPaw.api.classes.Nation;
import DevPaw.api.classes.War.WarData;
import DevPaw.api.exceptions.UnsuccessfullAPIException;
import main.GenBot1;
import main.Init;

public class CmdsPersistant {
	protected static Map<String, Timer> timers = new HashMap<>();

	private CmdsPersistant() {
		super();
	}

	public static void marketTracker(Message m) {
		m.getChannel().sendMessage("Tracking...");
		if (!timers.containsKey(m.getChannel().getIdAsString())) {
			Timer t = new Timer();
			t.schedule(new TimerTask() {
				public void run() {
					Cmds3.market(m);
				}
			}, 0, 60000);
			timers.put(m.getChannel().getIdAsString(), t);
		} else
			m.getChannel().sendMessage("There is already a market stream for this channel");
	}

	public static void stopTracker(Message m) {
		Timer t = timers.remove(m.getChannel().getIdAsString());
		if (t == null)
			m.getChannel().sendMessage("There was no market stream allocated to this channel!");
		else {
			t.cancel();
			t.purge();
			m.getChannel().sendMessage("Market Stream was disabled.");
		}
	}

	public static void removeDefensive(Message m) {
		if (removeDefensive(m.getChannel().getIdAsString())) {
			m.getChannel().sendMessage("Removed!");
		} else {
			m.getChannel().sendMessage("No defender found for this channel for that id");
		}
	}

	private static boolean removeDefensive(String channelid) {
		if (Init.warExecutor.hasID(channelid)) {
			Init.warExecutor.removeConsumer(channelid);
			return true;
		}
		return false;
	}

	public static void defensiveWarTracker(Message m) {
		TextChannel c = m.getChannel();
		String[] args = m.getContent().split(" ");
		if (!args[1].equalsIgnoreCase("all")) {
			try {
				GenBot1.mainapi.getAlliance(args[1]);
			} catch (UnsuccessfullAPIException error) {
				ErrorResponses.APIException(c, error);
				return;
			} catch (ArrayIndexOutOfBoundsException error) {
				ErrorResponses.invalidFormatException(c, error);
				return;
			}
		}
		final String allianceid = m.getContent().split(" ")[1];
		final String channelid = c.getIdAsString();
		Init.warExecutor.addConsumer(channelid, list -> list.forEach(war -> {
			try {
				if (GenBot1.mainapi.getNation(war.defenderID).allianceid.contentEquals(allianceid)
						|| allianceid.equalsIgnoreCase("all")) {
					System.out.println("Sucess!");
					EmbedBuilder embed = new EmbedBuilder();
					WarData w = GenBot1.mainapi.getWarData(war.warID);
					embed.setTitle("New War Declaration!");
					embed.setUrl("https://politicsandwar.com/nation/war/timeline/war=" + war.warID);
					Nation agro = GenBot1.mainapi.getNation(w.aggressorId);
					Nation defe = GenBot1.mainapi.getNation(w.defenderId);
					embed.addInlineField("Invader's nation", CmdsUtil.hyperUrl(agro.name + "-",
							"https://politicsandwar.com/nation/id=" + agro.nationid));
					embed.addInlineField("Invader's alliance", CmdsUtil.hyperUrl(agro.alliance + "-",
							"https://politicsandwar.com/alliance/id=" + agro.allianceid));
					embed.addInlineField("Invader's score", agro.score + "#");
					embed.addInlineField("Defender's nation", CmdsUtil.hyperUrl(defe.name + "-",
							"https://politicsandwar.com/nation/id=" + defe.nationid));
					embed.addInlineField("Defender's alliance", CmdsUtil.hyperUrl(defe.alliance + "-",
							"https://politicsandwar.com/alliance/id=" + defe.allianceid));
					embed.addInlineField("Defender's score", defe.score + "#");
					embed.addInlineField("Invader's soldiers", agro.soldiers + " -");
					embed.addInlineField("Defender's soldiers", defe.soldiers + " -");
					embed.addInlineField("Advantage",
							(Integer.parseInt(agro.soldiers) <= Integer.parseInt(defe.soldiers)) ? ":white_check_mark: "
									: ":x: ");
					embed.addInlineField("Invader's tanks", agro.tanks + " -");
					embed.addInlineField("Defender's tanks", defe.tanks + " -");
					embed.addInlineField("Advantage",
							(Integer.parseInt(agro.tanks) <= Integer.parseInt(defe.tanks)) ? ":white_check_mark: "
									: ":x: ");
					embed.addInlineField("Invader's planes", agro.aircraft + " -");
					embed.addInlineField("Defender's planes", defe.aircraft + " -");
					embed.addInlineField("Advantage",
							(Integer.parseInt(agro.aircraft) <= Integer.parseInt(defe.aircraft)) ? ":white_check_mark: "
									: ":x: ");
					embed.addInlineField("Invader's ships", agro.ships + " -");
					embed.addInlineField("Defender's ships", defe.ships + " -");
					embed.addInlineField("Advantage",
							(Integer.parseInt(agro.ships) <= Integer.parseInt(defe.ships)) ? ":white_check_mark: "
									: ":x: ");
					embed.addInlineField("Invader's missiles", agro.missiles + " -");
					embed.addInlineField("Defender's missiles", defe.missiles + " -");
					embed.addInlineField("Advantage",
							(Integer.parseInt(agro.missiles) <= Integer.parseInt(defe.missiles)) ? ":white_check_mark: "
									: ":x: ");
					embed.addInlineField("Invader's nukes", agro.nukes + " -");
					embed.addInlineField("Defender's nukes", defe.nukes + " -");
					embed.addInlineField("Advantage",
							(Integer.parseInt(agro.nukes) <= Integer.parseInt(defe.nukes)) ? ":white_check_mark: "
									: ":x: ");
					embed.setFooter("War monitor system made by Devan S.",
							"https://cdn.discordapp.com/avatars/358794817595113476/48192cb27b43069e544052b814c48df1.png?size=256");
					Optional<Channel> channel = GenBot1.api.getChannelById(channelid);
					if (channel.isPresent())
						channel.get().asServerTextChannel().get().sendMessage(embed);
					else
						removeDefensive(channelid);
				}
			} catch (UnsuccessfullAPIException e) {
				ErrorResponses.APIException(c, e);
			}
		}));
		c.sendMessage("Tracking will start now!");
	}

}
