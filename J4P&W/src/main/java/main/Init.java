package main;

import java.time.temporal.ChronoUnit;

import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.listener.message.MessageCreateListener;
import org.javacord.api.listener.server.member.ServerMemberJoinListener;

import DevPaw.api.PaWAPI;
import DevPaw.api.classes.Nations.LNation;
import DevPaw.api.classes.Wars.LWar;
import DevPaw.api.listeners.APIExecutor;
import DevPaw.api.util.ArrayExtraUtils;
import DevPaw.utilities.DevReader;
import discordListeners.MessageSent;
import discordListeners.UserJoin;

public class Init {
	public static APIExecutor<LWar> warExecutor;
	public static APIExecutor<LNation> beigeExecutor;
	
	public static void initialize(String token) {
		try {
			GenBot1.mainapi = new PaWAPI(100);
			GenBot1.tradeapi = new PaWAPI(new DevReader(System.out),15);
			GenBot1.bigapis = new PaWAPI(new DevReader(System.out),7);
			GenBot1.bigapis.setTime(10, ChronoUnit.MINUTES);
			GenBot1.mainapi.setTime(30, ChronoUnit.MINUTES);
			GenBot1.api = new DiscordApiBuilder().setToken(token).login().join();
			GenBot1.ms = new MessageSent();
			GenBot1.api.addMessageCreateListener((MessageCreateListener) GenBot1.ms);
			GenBot1.api.addServerMemberJoinListener((ServerMemberJoinListener)new UserJoin());
			GenBot1.api.updateActivity("j!help");
			System.out.println(GenBot1.api.createBotInvite());
			
			new Thread(() -> {while(!GenBot1.tradeapi.inBuffer("coal")) { try {Thread.sleep(60000);} catch (Exception e) {/*Timeout usually*/} GenBot1.tradeapi.getTradePrice("coal");}}).start();
			new Thread(() -> {while(!GenBot1.tradeapi.inBuffer("oil")) { try {Thread.sleep(60000);} catch (Exception e) {/*Timeout usually*/} GenBot1.tradeapi.getTradePrice("oil");}}).start();
			new Thread(() -> {while(!GenBot1.tradeapi.inBuffer("uranium")) { try {Thread.sleep(60000);} catch (Exception e) {/*Timeout usually*/} GenBot1.tradeapi.getTradePrice("uranium");}}).start();
			new Thread(() -> {while(!GenBot1.tradeapi.inBuffer("lead")) { try {Thread.sleep(60000);} catch (Exception e) {/*Timeout usually*/} GenBot1.tradeapi.getTradePrice("lead");}}).start();
			new Thread(() -> {while(!GenBot1.tradeapi.inBuffer("iron")) { try {Thread.sleep(60000);} catch (Exception e) {/*Timeout usually*/} GenBot1.tradeapi.getTradePrice("iron");}}).start();
			new Thread(() -> {while(!GenBot1.tradeapi.inBuffer("bauxite")) { try {Thread.sleep(60000);} catch (Exception e) {/*Timeout usually*/} GenBot1.tradeapi.getTradePrice("bauxite");}}).start();
			new Thread(() -> {while(!GenBot1.tradeapi.inBuffer("gasoline")) { try {Thread.sleep(60000);} catch (Exception e) {/*Timeout usually*/} GenBot1.tradeapi.getTradePrice("gasoline");}}).start();
			new Thread(() -> {while(!GenBot1.tradeapi.inBuffer("munitions")) { try {Thread.sleep(60000);} catch (Exception e) {/*Timeout usually*/} GenBot1.tradeapi.getTradePrice("munitions");}}).start();
			new Thread(() -> {while(!GenBot1.tradeapi.inBuffer("steel")) { try {Thread.sleep(60000);} catch (Exception e) {/*Timeout usually*/} GenBot1.tradeapi.getTradePrice("steel");}}).start();
			new Thread(() -> {while(!GenBot1.tradeapi.inBuffer("aluminum")) { try {Thread.sleep(60000);} catch (Exception e) {/*Timeout usually*/} GenBot1.tradeapi.getTradePrice("aluminum");}}).start();
			new Thread(() -> {while(!GenBot1.tradeapi.inBuffer("food")) { try {Thread.sleep(60000);} catch (Exception e) {/*Timeout usually*/} GenBot1.tradeapi.getTradePrice("food");}}).start();
			
			System.out.println("Bot initialized");
			warExecutor = new APIExecutor<>(
					"war",
					() -> GenBot1.bigapis.getWars().wars,
					(a, b) -> a.warID.equals(b.warID),
					false);
			beigeExecutor = new APIExecutor<>(
					"beige",
					() -> ArrayExtraUtils.streamToArray(GenBot1.bigapis.getNations().nations.stream().filter(n -> n.color.equals("beige"))),
					(a, b) -> a.nationid == b.nationid,
					false);
			System.out.println("Init Done!!!");
		} catch(Exception e) {e.printStackTrace();}
	}
	
	private Init() {}
}
