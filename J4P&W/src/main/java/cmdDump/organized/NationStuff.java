package cmdDump.organized;

import org.beryx.awt.color.ColorFactory;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.embed.EmbedBuilder;

import cmdDump.ErrorResponses;
import DevPaw.api.classes.Nation;
import DevPaw.api.exceptions.UnsuccessfullAPIException;
import DevPaw.api.util.ResourceGen;
import main.App;

public class NationStuff {

	public static void execute(Message m) {
		try {
			String[] args = m.getContent().split(" ");
			Nation n = App.mainapi.getNation(args[1]);
			ResourceGen rg = n.getRevenue();
			EmbedBuilder embed = new EmbedBuilder();
			embed.setTitle(n.leadername+"'s approximate revenue");
			embed.setColor(ColorFactory.valueOf(n.color));
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
	
	private NationStuff() {}

}
