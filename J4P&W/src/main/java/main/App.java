package main;


import java.awt.EventQueue;
import javax.swing.JFrame;

import org.javacord.api.DiscordApi;

import DevPaw.api.PaWAPI;
import discordListeners.MessageSent;
import graphics.FirstPanel;


public class App {
	//coal,oil,uranium,lead,iron,bauxite,gasoline,munitions,steel,aluminum,food
	public static DiscordApi api;
	public static MessageSent ms;
	public static String[] administrators = {"358794817595113476", "245470898293833729"};
	public static PaWAPI mainapi;
	public static PaWAPI tradeapi;
	public static PaWAPI bigapis;
	private static JFrame frame2;
	public static void main(String[] args) {
		frame2 = new JFrame ("Intialize bot");
        frame2.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame2.getContentPane().add (new FirstPanel());
        frame2.pack();
        frame2.setVisible (true);
	}
	public static void consolPanel() throws InterruptedException {
		frame2.dispose();
		EventQueue.invokeLater(new consolThread());
	}
	
	
}
