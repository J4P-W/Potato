package cmdDump;

import java.util.ArrayList;

import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.embed.EmbedBuilder;

import DevPaw.api.classes.Nation;
import DevPaw.api.classes.Alliances.Alliances;
import DevPaw.api.classes.War.WarData;
import DevPaw.api.classes.Wars.LWar;
import DevPaw.api.exceptions.UnsuccessfullAPIException;
import DevPaw.api.listeners.DevListener;
import DevPaw.utilities.SpeedUtils;
import main.App;
import main.Init;

public class CmdsPersistant {
	public static void defensiveWarTracker(Message m) {
		TextChannel c = m.getChannel();
		String[] args = m.getContent().split(" ");
		try {
			App.mainapi.getAlliance(args[1]);
			c.sendMessage("Tracking will begin now...");
		} catch(UnsuccessfullAPIException error) {
			ErrorResponses.APIException(c, error);
			return;
		} catch(ArrayIndexOutOfBoundsException error) {
			ErrorResponses.invalidFormatException(c, error);
		}
		
		Init.warExecutor.listeners.put(m.getChannel().getIdAsString(), new DevListener<LWar>() {
			public String allianceid = m.getContent().split(" ")[1];
			public String channelid = c.getIdAsString();
			@Override
			public void execute(ArrayList<LWar> data) {
				Alliances as = null;
				try {as = App.bigapis.getAlliances();} catch (UnsuccessfullAPIException e) {e.printStackTrace();}
				for(int x = 0; x < data.size(); x++)
					if(SpeedUtils.allianceNameToID(data.get(x).defenderAA, as).contentEquals(allianceid)) {
						EmbedBuilder embed = new EmbedBuilder();
						try {
							WarData w = App.mainapi.getWarData(data.get(x).warID);
							embed.setTitle("New War Declaration!");
							embed.setUrl(""+data.get(x).warID);
							Nation agro = App.mainapi.getNation(w.aggressorId);
							Nation defe = App.mainapi.getNation(w.defenderId);
							embed.addInlineField("Invader's nation", CmdsUtil.hyperUrl(agro.name, "https://politicsandwar.com/nation/id="+agro.nationid));
							embed.addInlineField("Invader's alliance", CmdsUtil.hyperUrl(agro.alliance, "https://politicsandwar.com/alliance/id="+agro.allianceid));
							embed.addInlineField("Invader's score", agro.score);
							embed.addInlineField("Defender's nation", CmdsUtil.hyperUrl(defe.name, "https://politicsandwar.com/nation/id="+defe.nationid));
							embed.addInlineField("Defender's alliance", CmdsUtil.hyperUrl(defe.alliance, "https://politicsandwar.com/alliance/id="+defe.allianceid));
							embed.addInlineField("Defender's score", defe.score);
							embed.addInlineField("Invader's soldiers", agro.soldiers); embed.addInlineField("Defender's soldiers", defe.soldiers); embed.addInlineField("Advantage", (Integer.parseInt(agro.soldiers) <= Integer.parseInt(defe.soldiers))?":white_check_mark: ":":x: ");
							embed.addInlineField("Invader's tanks", agro.tanks); embed.addInlineField("Defender's tanks", defe.tanks); embed.addInlineField("Advantage", (Integer.parseInt(agro.tanks) <= Integer.parseInt(defe.tanks))?":white_check_mark: ":":x: ");
							embed.addInlineField("Invader's planes", agro.aircraft); embed.addInlineField("Defender's planes", defe.aircraft); embed.addInlineField("Advantage", (Integer.parseInt(agro.aircraft) <= Integer.parseInt(defe.aircraft))?":white_check_mark: ":":x: ");
							embed.addInlineField("Invader's ships", agro.ships); embed.addInlineField("Defender's ships", defe.ships); embed.addInlineField("Advantage", (Integer.parseInt(agro.ships) <= Integer.parseInt(defe.ships))?":white_check_mark: ":":x: ");
							embed.addInlineField("Invader's missiles", agro.missiles); embed.addInlineField("Defender's missiles", defe.missiles); embed.addInlineField("Advantage", (Integer.parseInt(agro.missiles) <= Integer.parseInt(defe.missiles))?":white_check_mark: ":":x: ");
							embed.addInlineField("Invader's nukes", agro.nukes); embed.addInlineField("Defender's nukes", defe.nukes); embed.addInlineField("Advantage", (Integer.parseInt(agro.nukes) <= Integer.parseInt(defe.nukes))?":white_check_mark: ":":x: ");
							embed.setFooter("War monitor system made by Devan S.", "https://cdn.discordapp.com/avatars/358794817595113476/48192cb27b43069e544052b814c48df1.png?size=256");
							c.sendMessage(embed);
						} catch(UnsuccessfullAPIException error) {
							embed.setTitle("Invalid war ID");
							embed.addField("We're sorry for the inconvience", "This war was unable to be processed");
						}
						App.api.getChannelById(channelid).get().asServerTextChannel().get().sendMessage(embed);
					}
			}
		});
	}
}
