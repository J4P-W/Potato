package cmdDump;

import java.awt.Color;
import java.util.Arrays;

import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.embed.EmbedBuilder;

import DevPaw.api.classes.Alliance;
import DevPaw.api.classes.TradePrice.TradePrice;
import DevPaw.api.classes.War.WarData;
import DevPaw.api.exceptions.UnsuccessfullAPIException;
import main.App;

public class Cmds3 {
	public static void vms(Message m) {
		TextChannel c = m.getChannel();
		String[] args = m.getContent().split(" ");
		try {
			Alliance a = App.mainapi.getAlliance(args[1]);
			EmbedBuilder embed = new EmbedBuilder();
			embed.setTitle("Vacation mode members in "+a.name);
			embed.addField("Value: ","```"+a.vmodemembers+"```");
			c.sendMessage(embed);
		} catch(UnsuccessfullAPIException error) {
			ErrorResponses.APIException(c, error);
		}
	}
	
	
	public static void War(Message m) {
		TextChannel c = m.getChannel();
		String[] args = m.getContent().split(" ");
		try {
			WarData w = App.mainapi.getWarData(args[1]);
			EmbedBuilder embed = new EmbedBuilder();
			embed.setTitle("War " + args[1]);
			embed.setColor(Color.red);
			embed.addField("Defender's Nation", "https://politicsandwar.com/nation/id="+w.defenderId);
			embed.addField("Aggressor's Nation", "https://politicsandwar.com/nation/id="+w.aggressorId);
			embed.addInlineField("Defenders losses", w.defenderMoneyLost+"$");
			embed.addInlineField("Aggressors losses", w.aggressorMoneyLost+"$");
			if(w.warEnded)
				embed.addField("War status: ", "Ended");
			else {
				char[] agroarray = new char[Integer.parseInt(w.aggressorResistance)/10];
				char[] defensearray = new char[Integer.parseInt(w.defenderResistance)/10];
				Arrays.fill(agroarray, '=');
				Arrays.fill(defensearray, '=');
				embed.addField("Defender's resistance", "```|"+new String(defensearray)+"> %"+ w.defenderResistance+"```");
				embed.addField("Aggressor's resistance", "```|"+new String(agroarray)+"> %"+ w.aggressorResistance+"```");
			}
			embed.addInlineField("War type", w.warType);
			embed.addInlineField("Turns left", w.turnsLeft+"");
			embed.addField("Likely outcome: ", w.airSuperiority.contentEquals(w.aggressorId)?"Aggressor will probably win":"Defender has a chance");
			c.sendMessage(embed);
		}
		catch(UnsuccessfullAPIException error) {
			ErrorResponses.APIException(c, error);
		}
	}
	public static void market(Message m) {
		TextChannel c = m.getChannel();
		EmbedBuilder embed = new EmbedBuilder();
		TradePrice coal = App.tradeapi.getTradePrice("coal");
		TradePrice oil = App.tradeapi.getTradePrice("oil");
		TradePrice uranium = App.tradeapi.getTradePrice("uranium");
		TradePrice lead = App.tradeapi.getTradePrice("lead");
		TradePrice iron = App.tradeapi.getTradePrice("iron");
		TradePrice bauxite = App.tradeapi.getTradePrice("bauxite");
		TradePrice gasoline = App.tradeapi.getTradePrice("gasoline");
		TradePrice munitions = App.tradeapi.getTradePrice("munitions");
		TradePrice steel = App.tradeapi.getTradePrice("steel");
		TradePrice aluminum = App.tradeapi.getTradePrice("aluminum");
		TradePrice food = App.tradeapi.getTradePrice("food");
		embed.setUrl("https://politicsandwar.com/nation/trade/display=world");
		embed.addInlineField("Coal average", coal.avgprice); embed.addInlineField("Coal best sell", coal.highestbuy.price); embed.addInlineField("Coal best buy", coal.lowestbuy.price);
		embed.addInlineField("Oil average", oil.avgprice); embed.addInlineField("Oil best sell", oil.highestbuy.price); embed.addInlineField("Oil best buy", oil.lowestbuy.price);
		embed.addInlineField("Uranium average", uranium.avgprice); embed.addInlineField("Uranium best sell", uranium.highestbuy.price); embed.addInlineField("Uranium best buy", uranium.lowestbuy.price);
		embed.addInlineField("Lead average", lead.avgprice); embed.addInlineField("Lead best sell", lead.highestbuy.price); embed.addInlineField("Lead best buy", lead.lowestbuy.price);
		embed.addInlineField("Iron average", iron.avgprice); embed.addInlineField("Iron best sell", iron.highestbuy.price); embed.addInlineField("Iron best buy", iron.lowestbuy.price);
		embed.addInlineField("Bauxite average", bauxite.avgprice); embed.addInlineField("Bauxite best sell", bauxite.highestbuy.price); embed.addInlineField("Bauxite best buy", bauxite.lowestbuy.price);
		embed.addInlineField("Gasoline average", gasoline.avgprice); embed.addInlineField("Gasoline best sell", gasoline.highestbuy.price); embed.addInlineField("Gasoline best buy", gasoline.lowestbuy.price);
		embed.addInlineField("Munitions average", munitions.avgprice); embed.addInlineField("Munitions best sell", munitions.highestbuy.price); embed.addInlineField("Munitions best buy", munitions.lowestbuy.price);
		EmbedBuilder embed2 = new EmbedBuilder();
		embed2.addInlineField("Steel average", steel.avgprice); embed2.addInlineField("Steel best sell", steel.highestbuy.price); embed2.addInlineField("Steel best buy", steel.lowestbuy.price);
		embed2.addInlineField("Aluminum average", aluminum.avgprice); embed2.addInlineField("Aluminum best sell", aluminum.highestbuy.price); embed.addInlineField("Aluminum best buy.", aluminum.lowestbuy.price);
		embed2.addInlineField("Food average", food.avgprice); embed2.addInlineField("Food best sell", food.highestbuy.price); embed2.addInlineField("Food best buy", food.lowestbuy.price);
		embed.setColor(Color.white);
		embed2.setColor(Color.white);
		c.sendMessage(embed);
		c.sendMessage(embed2);
	}
}
