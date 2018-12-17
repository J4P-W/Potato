package cmdDump.organized;

import org.beryx.awt.color.ColorFactory;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.embed.EmbedBuilder;

import cmdDump.ErrorResponses;
import DevPaw.api.classes.Alliance;
import DevPaw.api.exceptions.UnsuccessfullAPIException;
import DevPaw.api.util.ResourceGen;
import main.App;

public class AllianceStuff {
	
	private AllianceStuff() {}

	public static void execute(Message m) {
		try {
			String[] args = m.getContent().split(" ");
			Alliance a = App.mainapi.getAlliance(args[2]);
			ResourceGen rg = new ResourceGen();
			for(Integer nid : a.member_id_list)
				rg.add(App.mainapi.getNation(nid+"").getRevenue());
			EmbedBuilder embed = new EmbedBuilder();
			embed.setTitle(a.name+"'s approximate revenue");
			embed.setColor(ColorFactory.valueOf(a.color));
			embed.setFooter("This is an approximation, not perfect, nor does it include taxes");
			embed.addField("Money:", String.format("$%10.2d", rg.monet));
			embed.addField("Coal:", String.format("- %10.2d", rg.coal));
			embed.addField("Oil:", String.format("- %10.2d", rg.oil));
			embed.addField("Iron:", String.format("- %10.2d ", rg.iron));
			embed.addField("Bauxite:", String.format("- %10.2d ", rg.baux));
			embed.addField("Steel:", String.format("- %10.2d ", rg.steel));
			embed.addField("Aluminum:", String.format("- %10.2d ", rg.alum));
			embed.addField("Gasoline:", String.format("- %10.2d ", rg.gaso));
			embed.addField("Uranium:", String.format("- %10.2d ", rg.uran));
			embed.addField("Lead:", String.format("- %10.2d ", rg.lead));
			embed.addField("Munitions:", String.format("- %10.2d ", rg.munit));
			embed.addField("Food:", String.format("- %10.2d ", rg.food));
			embed.setDescription("Teehee");
			m.getChannel().sendMessage(embed);
		} catch (UnsuccessfullAPIException e) {
			ErrorResponses.APIException(m.getChannel(), e);
		}
	}

}
