package main;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.listener.message.MessageCreateListener;
import org.javacord.api.listener.server.member.ServerMemberJoinListener;

import DevPaw.api.PaWAPI;
import DevPaw.api.exceptions.UnsuccessfullAPIException;
import DevPaw.utilities.DevReader;
import discordListeners.MessageSent;
import discordListeners.UserJoin;


public class App {
	//coal,oil,uranium,lead,iron,bauxite,gasoline,munitions,steel,aluminum,food
	public static DiscordApi api;
	public static MessageSent ms;
	public static String[] administrators = {"358794817595113476", "245470898293833729"};
	public static PaWAPI mainapi = new PaWAPI(new DevReader(System.out));
	public static PaWAPI tradeapi = new PaWAPI(new DevReader(),15);
	public static PaWAPI bigapis = new PaWAPI(new DevReader(), 4);
	public static void main(String[] args) throws UnsuccessfullAPIException {
		PrintStream p = new PrintStream(System.out) {
		    @Override
		    public void println(String x) {
		        super.printf("[%s]:\t%s\n",new SimpleDateFormat("hh:mm:ss a").format(new Date()),x);
		    }
		};
		System.setOut(p);
		System.out.println("yeet");
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
		new Thread(new Runnable() {public void run() {while(!tradeapi.inBuffer("coal")) { try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();} tradeapi.getTradePrice("coal");}}}).start();
		new Thread(new Runnable() {public void run() {while(!tradeapi.inBuffer("oil")) { try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();} tradeapi.getTradePrice("oil");}}}).start();
		new Thread(new Runnable() {public void run() {while(!tradeapi.inBuffer("uranium")) { try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();} tradeapi.getTradePrice("uranium");}}}).start();
		new Thread(new Runnable() {public void run() {while(!tradeapi.inBuffer("lead")) { try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();} tradeapi.getTradePrice("lead");}}}).start();
		new Thread(new Runnable() {public void run() {while(!tradeapi.inBuffer("iron")) { try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();} tradeapi.getTradePrice("iron");}}}).start();
		new Thread(new Runnable() {public void run() {while(!tradeapi.inBuffer("bauxite")) { try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();} tradeapi.getTradePrice("bauxite");}}}).start();
		new Thread(new Runnable() {public void run() {while(!tradeapi.inBuffer("gasoline")) { try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();} tradeapi.getTradePrice("gasoline");}}}).start();
		new Thread(new Runnable() {public void run() {while(!tradeapi.inBuffer("munitions")) { try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();} tradeapi.getTradePrice("munitions");}}}).start();
		new Thread(new Runnable() {public void run() {while(!tradeapi.inBuffer("steel")) { try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();} tradeapi.getTradePrice("steel");}}}).start();
		new Thread(new Runnable() {public void run() {while(!tradeapi.inBuffer("aluminum")) { try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();} tradeapi.getTradePrice("aluminum");}}}).start();
		new Thread(new Runnable() {public void run() {while(!tradeapi.inBuffer("food")) { try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();} tradeapi.getTradePrice("food");}}}).start();
		System.out.println("Bot initialized");
		s.close();
	}
}
