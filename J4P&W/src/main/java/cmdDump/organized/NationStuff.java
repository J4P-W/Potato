package cmdDump.organized;

import org.beryx.awt.color.ColorFactory;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.embed.EmbedBuilder;

import cmdDump.ErrorResponses;
import DevPaw.api.classes.Nation;
import DevPaw.api.exceptions.UnsuccessfullAPIException;
import DevPaw.api.util.ResourceGen;
import main.GenBot1;

public class NationStuff {

	public static void execute(Message m) {
		try {
			String[] args = m.getContent().split(" ");
			Nation n = GenBot1.mainapi.getNation(args[2]);
			ResourceGen rg = n.getRevenue(GenBot1.mainapi);
			EmbedBuilder embed = new EmbedBuilder();
			embed.setTitle(n.leadername+"'s approximate revenue");
			embed.setColor(ColorFactory.valueOf(n.color));
			embed.setFooter("This is an approximation, not perfect, nor does it include taxes");
			embed.addField("Money:", String.format("$%10.2f", rg.monet));
			embed.addField("Coal:", String.format("| %10.2f", rg.coal));
			embed.addField("Oil:", String.format("| %10.2f", rg.oil));
			embed.addField("Iron:", String.format("| %10.2f ", rg.iron));
			embed.addField("Bauxite:", String.format("| %10.2f ", rg.baux));
			embed.addField("Steel:", String.format("| %10.2f ", rg.steel));
			embed.addField("Aluminum:", String.format("| %10.2f ", rg.alum));
			embed.addField("Gasoline:", String.format("| %10.2f ", rg.gaso));
			embed.addField("Uranium:", String.format("| %10.2f ", rg.uran));
			embed.addField("Lead:", String.format("| %10.2f ", rg.lead));
			embed.addField("Munitions:", String.format("| %10.2f ", rg.munit));
			embed.addField("Food:", String.format("| %10.2f ", rg.food));
			embed.setDescription("Teehee");
			m.getChannel().sendMessage(embed);
		} catch (UnsuccessfullAPIException e) {
			ErrorResponses.APIException(m.getChannel(), e);
		}
	}
	
	private NationStuff() {}

}
