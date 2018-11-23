package main;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Scanner;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.listener.message.MessageCreateListener;
import org.javacord.api.listener.server.member.ServerMemberJoinListener;

import DevPaw.api.PaWAPI;
import DevPaw.utilities.DevReader;
import discordListeners.MessageSent;
import discordListeners.UserJoin;


public class App {
	//coal,oil,uranium,lead,iron,bauxite,gasoline,munitions,steel,aluminum,food
	public static DiscordApi api;
	public static MessageSent ms;
	public static String[] administrators = {"358794817595113476", "245470898293833729"};
	public static PaWAPI mainapi;
	public static PaWAPI tradeapi;
	public static PaWAPI bigapis;
	public static void main(String[] args) {
		PrintStream p = new PrintStream(System.out) {
		    @Override
		    public void println(String x) {
		        super.printf("[%s]:\t%s\n", new SimpleDateFormat("hh:mm:ss a").format(new Date()),x);
		    }
		};
		System.setOut(p);
		mainapi = new PaWAPI(100);
		tradeapi = new PaWAPI(new DevReader(),15);
		bigapis = new PaWAPI(new DevReader(),7);
		bigapis.setTime(2, ChronoUnit.MINUTES);
		mainapi.setTime(5, ChronoUnit.MINUTES);
		Scanner s = new Scanner(System.in);
		System.out.print("Enter bot token: ");
		String token = s.nextLine();
		api = new DiscordApiBuilder().setToken(token).login().join();
		ms = new MessageSent();
		api.addMessageCreateListener((MessageCreateListener) ms);
		api.addServerMemberJoinListener((ServerMemberJoinListener)new UserJoin());
		api.updateActivity("j!help");
		System.out.println(api.createBotInvite());
		Init.Initialize();
		try {
			new Thread(new Runnable() {public void run() {while(!tradeapi.inBuffer("coal")) { try {Thread.sleep(60000);} catch (Exception e) {} tradeapi.getTradePrice("coal");}}}).start();
			new Thread(new Runnable() {public void run() {while(!tradeapi.inBuffer("oil")) { try {Thread.sleep(60000);} catch (Exception e) {} tradeapi.getTradePrice("oil");}}}).start();
			new Thread(new Runnable() {public void run() {while(!tradeapi.inBuffer("uranium")) { try {Thread.sleep(60000);} catch (Exception e) {} tradeapi.getTradePrice("uranium");}}}).start();
			new Thread(new Runnable() {public void run() {while(!tradeapi.inBuffer("lead")) { try {Thread.sleep(60000);} catch (Exception e) {} tradeapi.getTradePrice("lead");}}}).start();
			new Thread(new Runnable() {public void run() {while(!tradeapi.inBuffer("iron")) { try {Thread.sleep(60000);} catch (Exception e) {} tradeapi.getTradePrice("iron");}}}).start();
			new Thread(new Runnable() {public void run() {while(!tradeapi.inBuffer("bauxite")) { try {Thread.sleep(60000);} catch (Exception e) {} tradeapi.getTradePrice("bauxite");}}}).start();
			new Thread(new Runnable() {public void run() {while(!tradeapi.inBuffer("gasoline")) { try {Thread.sleep(60000);} catch (Exception e) {} tradeapi.getTradePrice("gasoline");}}}).start();
			new Thread(new Runnable() {public void run() {while(!tradeapi.inBuffer("munitions")) { try {Thread.sleep(60000);} catch (Exception e) {} tradeapi.getTradePrice("munitions");}}}).start();
			new Thread(new Runnable() {public void run() {while(!tradeapi.inBuffer("steel")) { try {Thread.sleep(60000);} catch (Exception e) {} tradeapi.getTradePrice("steel");}}}).start();
			new Thread(new Runnable() {public void run() {while(!tradeapi.inBuffer("aluminum")) { try {Thread.sleep(60000);} catch (Exception e) {} tradeapi.getTradePrice("aluminum");}}}).start();
			new Thread(new Runnable() {public void run() {while(!tradeapi.inBuffer("food")) { try {Thread.sleep(60000);} catch (Exception e) {} tradeapi.getTradePrice("food");}}}).start();
		} catch(Exception e) {}
		System.out.println("Bot initialized");
		s.close();
	}
}
