package main;

import java.time.temporal.ChronoUnit;

import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.listener.message.MessageCreateListener;
import org.javacord.api.listener.server.member.ServerMemberJoinListener;

import DevPaw.api.PaWAPI;
import DevPaw.api.classes.Wars.LWar;
import DevPaw.api.classes.Wars.Wars;
import DevPaw.api.exceptions.UnsuccessfullAPIException;
import DevPaw.api.listeners.DevExecutor;
import DevPaw.api.listeners.ObjectSource;
import DevPaw.utilities.DevReader;
import discordListeners.MessageSent;
import discordListeners.UserJoin;

public class Init {
	public static DevExecutor<LWar> warExecutor;
	public static void Initialize(String token) {
		App.mainapi = new PaWAPI(100);
		App.tradeapi = new PaWAPI(new DevReader(System.out),15);
		App.bigapis = new PaWAPI(new DevReader(System.out),7);
		App.bigapis.setTime(2, ChronoUnit.MINUTES);
		App.mainapi.setTime(5, ChronoUnit.MINUTES);
		App.api = new DiscordApiBuilder().setToken(token).login().join();
		App.ms = new MessageSent();
		App.api.addMessageCreateListener((MessageCreateListener) App.ms);
		App.api.addServerMemberJoinListener((ServerMemberJoinListener)new UserJoin());
		App.api.updateActivity("j!help");
		System.out.println(App.api.createBotInvite());
		try {
			new Thread(new Runnable() {public void run() {while(!App.tradeapi.inBuffer("coal")) { try {Thread.sleep(60000);} catch (Exception e) {} App.tradeapi.getTradePrice("coal");}}}).start();
			new Thread(new Runnable() {public void run() {while(!App.tradeapi.inBuffer("oil")) { try {Thread.sleep(60000);} catch (Exception e) {} App.tradeapi.getTradePrice("oil");}}}).start();
			new Thread(new Runnable() {public void run() {while(!App.tradeapi.inBuffer("uranium")) { try {Thread.sleep(60000);} catch (Exception e) {} App.tradeapi.getTradePrice("uranium");}}}).start();
			new Thread(new Runnable() {public void run() {while(!App.tradeapi.inBuffer("lead")) { try {Thread.sleep(60000);} catch (Exception e) {} App.tradeapi.getTradePrice("lead");}}}).start();
			new Thread(new Runnable() {public void run() {while(!App.tradeapi.inBuffer("iron")) { try {Thread.sleep(60000);} catch (Exception e) {} App.tradeapi.getTradePrice("iron");}}}).start();
			new Thread(new Runnable() {public void run() {while(!App.tradeapi.inBuffer("bauxite")) { try {Thread.sleep(60000);} catch (Exception e) {} App.tradeapi.getTradePrice("bauxite");}}}).start();
			new Thread(new Runnable() {public void run() {while(!App.tradeapi.inBuffer("gasoline")) { try {Thread.sleep(60000);} catch (Exception e) {} App.tradeapi.getTradePrice("gasoline");}}}).start();
			new Thread(new Runnable() {public void run() {while(!App.tradeapi.inBuffer("munitions")) { try {Thread.sleep(60000);} catch (Exception e) {} App.tradeapi.getTradePrice("munitions");}}}).start();
			new Thread(new Runnable() {public void run() {while(!App.tradeapi.inBuffer("steel")) { try {Thread.sleep(60000);} catch (Exception e) {} App.tradeapi.getTradePrice("steel");}}}).start();
			new Thread(new Runnable() {public void run() {while(!App.tradeapi.inBuffer("aluminum")) { try {Thread.sleep(60000);} catch (Exception e) {} App.tradeapi.getTradePrice("aluminum");}}}).start();
			new Thread(new Runnable() {public void run() {while(!App.tradeapi.inBuffer("food")) { try {Thread.sleep(60000);} catch (Exception e) {} App.tradeapi.getTradePrice("food");}}}).start();
		} catch(Exception e) {}
		System.out.println("Bot initialized");
		warExecutor = new DevExecutor<LWar>("War",new ObjectSource<LWar>() {
			@Override
			public LWar[] getSource() {
				Wars ws;
				try {
					ws = App.bigapis.getWars(2000);
					LWar[] warray = ws.wars;
					return warray;
				} catch (UnsuccessfullAPIException e) {
					e.printStackTrace();
				}
				return null;
			}
			@Override
			public boolean equals(LWar a, LWar b) {
				return a.warID.contentEquals(b.warID);
			}
		}, 120000);
	}
}
