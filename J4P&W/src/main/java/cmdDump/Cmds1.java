package cmdDump;

import java.awt.Color;
import java.util.concurrent.ExecutionException;

import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import DevPaw.api.classes.Alliance;
import DevPaw.api.classes.City;
import DevPaw.api.classes.Nation;
import DevPaw.api.exceptions.UnsuccessfullAPIException;
import main.App;
import utils.miscellaneous.CityLayout;


public class Cmds1 {
	
	private Cmds1() {}
	
	public static void ping(TextChannel c) {
		long start = System.currentTimeMillis();
		Message a = c.sendMessage("pinging...").join();
		long end = System.currentTimeMillis();
		c.deleteMessages(a).join();
		c.sendMessage("```Pong! " + (end - start) + "ms```");
	}
	public static void kill(TextChannel c) {
		c.sendMessage("```diff\n- Good bye cruel world ;(```");
		System.exit(0);
	}
	
	public static void audit(MessageCreateEvent e) {
		TextChannel c = e.getChannel();
		try {
			Message m = e.getMessage();
			String[] args = m.getContent().split(" ");
			CityLayout cl = null;
			cl = new Gson().fromJson(new String(m.getAttachments().get(0).downloadAsByteArray().get()), CityLayout.class);
			Alliance a = App.mainapi.getAlliance(args[1]);
			EmbedBuilder embed = new EmbedBuilder();
			boolean failed = false;
			if(a == null) {
				c.sendMessage("```diff\n- Null Alliance ID: "+ args[1] +"```");
				return;
			}
			int counter = 0;
			for(int nid: a.member_id_list) {
				Nation n = App.mainapi.getNation(nid+"");
				boolean audit = true;
				for(String cid: n.cityids) {
					City city = App.mainapi.getCity(cid);
					audit = cl.audit(city);
					if(!audit) {
						if(counter++ > 10) {
							c.sendMessage(embed);
							counter = 0;
							embed = new EmbedBuilder();
						}
						embed.addField("https://politicsandwar.com/api/nation/id="+nid, ":x: failed city inspection: https://politicsandwar.com/city/id="+cid);
						failed = true;
						break;
					}
				}
				if(audit)
					embed.addField("https://politicsandwar.com/nation/id="+nid, ":white_check_mark:");
			}
			if(failed)
				embed.setColor(Color.red);
			else
				embed.setColor(Color.GREEN);
			embed.setTitle("City Inspection.");
			c.sendMessage(embed);
		} catch(UnsuccessfullAPIException error) {
			ErrorResponses.APIException(c, error);
		} catch (JsonSyntaxException | InterruptedException | ExecutionException e1) {
			ErrorResponses.ParseException(c, e1);
		}
	}
}
