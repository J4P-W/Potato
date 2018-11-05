package cmdDump;

import java.awt.Color;

import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.server.Server;
import org.javacord.api.event.message.MessageCreateEvent;

import api.classes.Alliance;
import api.classes.City;
import api.classes.Nation;
import pawstuff.CityLayout;
import utils.AuditData;


public class Cmds1 {
	public static void setAudit(Server s, TextChannel c, Message m) {
		try {
			System.out.println("Hello?");
			String json = new String(m.getAttachments().get(0).downloadAsByteArray().get());
			System.out.println(json);
			CityLayout cl = CityLayout.getCityLayout(json);
			AuditData.data.put(s.getIdAsString(), cl);
			c.sendMessage("Audit sucessfully updated!");
		} catch (Exception e) {
			e.printStackTrace();
			c.sendMessage("Audit update failed!!!!!");
		}
	}
	
	public static void ping(TextChannel c) {
		System.out.println("Pinged");
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
		System.out.println("Test");
		Message m = e.getMessage();
		String[] args = m.getContent().split(" ");
		TextChannel c = e.getChannel();
		Server s = e.getServer().get();
		CityLayout cl = AuditData.data.get(s.getIdAsString());
		Alliance a = Alliance.getAlliance(args[1]);
		EmbedBuilder embed = new EmbedBuilder();
		boolean failed = false;
		for(int nid: a.member_id_list) {
			Nation n = Nation.getNation(nid+"");
			boolean audit = true;
			for(String cid: n.cityids) {
				City city = City.getCity(cid);
				audit = cl.audit(city);
				if(!audit) {
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
	}
}
