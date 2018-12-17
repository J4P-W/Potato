package main;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.listener.message.MessageCreateListener;
import org.javacord.api.listener.server.member.ServerMemberJoinListener;

import DevPaw.api.PaWAPI;
import DevPaw.api.classes.Nations.LNation;
import DevPaw.api.classes.Wars.LWar;
import DevPaw.api.exceptions.UnsuccessfullAPIException;
import DevPaw.api.listeners.DevExecutor;
import DevPaw.api.listeners.ObjectSource;
import DevPaw.utilities.DevReader;
import discordListeners.MessageSent;
import discordListeners.UserJoin;

public class Init {
	public static DevExecutor<LWar> warExecutor;
	public static DevExecutor<LNation> beigeExecutor;
	
	public static void initialize(String token) {
		App.mainapi = new PaWAPI(100);
		App.tradeapi = new PaWAPI(new DevReader(System.out),15);
		App.bigapis = new PaWAPI(new DevReader(System.out),7);
		App.bigapis.setTime(10, ChronoUnit.MINUTES);
		App.mainapi.setTime(30, ChronoUnit.MINUTES);
		App.api = new DiscordApiBuilder().setToken(token).login().join();
		App.ms = new MessageSent();
		App.api.addMessageCreateListener((MessageCreateListener) App.ms);
		App.api.addServerMemberJoinListener((ServerMemberJoinListener)new UserJoin());
		App.api.updateActivity("j!help");
		System.out.println(App.api.createBotInvite());
		try {
			new Thread(() -> {while(!App.tradeapi.inBuffer("coal")) { try {Thread.sleep(60000);} catch (Exception e) {/*Timeout usually*/} App.tradeapi.getTradePrice("coal");}}).start();
			new Thread(() -> {while(!App.tradeapi.inBuffer("oil")) { try {Thread.sleep(60000);} catch (Exception e) {/*Timeout usually*/} App.tradeapi.getTradePrice("oil");}}).start();
			new Thread(() -> {while(!App.tradeapi.inBuffer("uranium")) { try {Thread.sleep(60000);} catch (Exception e) {/*Timeout usually*/} App.tradeapi.getTradePrice("uranium");}}).start();
			new Thread(() -> {while(!App.tradeapi.inBuffer("lead")) { try {Thread.sleep(60000);} catch (Exception e) {/*Timeout usually*/} App.tradeapi.getTradePrice("lead");}}).start();
			new Thread(() -> {while(!App.tradeapi.inBuffer("iron")) { try {Thread.sleep(60000);} catch (Exception e) {/*Timeout usually*/} App.tradeapi.getTradePrice("iron");}}).start();
			new Thread(() -> {while(!App.tradeapi.inBuffer("bauxite")) { try {Thread.sleep(60000);} catch (Exception e) {/*Timeout usually*/} App.tradeapi.getTradePrice("bauxite");}}).start();
			new Thread(() -> {while(!App.tradeapi.inBuffer("gasoline")) { try {Thread.sleep(60000);} catch (Exception e) {/*Timeout usually*/} App.tradeapi.getTradePrice("gasoline");}}).start();
			new Thread(() -> {while(!App.tradeapi.inBuffer("munitions")) { try {Thread.sleep(60000);} catch (Exception e) {/*Timeout usually*/} App.tradeapi.getTradePrice("munitions");}}).start();
			new Thread(() -> {while(!App.tradeapi.inBuffer("steel")) { try {Thread.sleep(60000);} catch (Exception e) {/*Timeout usually*/} App.tradeapi.getTradePrice("steel");}}).start();
			new Thread(() -> {while(!App.tradeapi.inBuffer("aluminum")) { try {Thread.sleep(60000);} catch (Exception e) {/*Timeout usually*/} App.tradeapi.getTradePrice("aluminum");}}).start();
			new Thread(() -> {while(!App.tradeapi.inBuffer("food")) { try {Thread.sleep(60000);} catch (Exception e) {/*Timeout usually*/} App.tradeapi.getTradePrice("food");}}).start();
		} catch(Exception e) {e.printStackTrace();}
		System.out.println("Bot initialized");
		warExecutor = new DevExecutor<>("War",new ObjectSource<LWar>() {
			@Override
			public LWar[] getSource() {
				try {
					return App.bigapis.getWars(2000).wars;
				} catch (UnsuccessfullAPIException e) {
					e.printStackTrace();
				}
				return new LWar[0];
			}
			@Override
			public boolean equals(LWar a, LWar b) {
				return a.warID.contentEquals(b.warID);
			}
		}, 120000);
		beigeExecutor = new DevExecutor<>("Beige", new ObjectSource<LNation>() {
			@Override
			public LNation[] getSource() {
				try {
					List<LNation> ns = App.bigapis.getNations().nations;
					List<LNation> beiges = new ArrayList<>();
					for(LNation nation : ns)
						if(nation.color.equals("beige"))
							beiges.add(nation);
					return beiges.toArray(new LNation[beiges.size()]);
				} catch(UnsuccessfullAPIException e) {e.printStackTrace();}
				return new LNation[0];
			}

			@Override
			public boolean equals(LNation a, LNation b) {
				return a.nationid == b.nationid;
			}
			
		}, 120000);
		System.out.println("Init Done!!!");
	}
	
	private Init() {}
}
