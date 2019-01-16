package main;


import java.awt.EventQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;

import org.javacord.api.DiscordApi;

import DevPaw.api.PaWAPI;
import DevPaw.webscrapers.WebColors;
import discordListeners.MessageSent;
import graphics.FirstPanel;


public class GenBot1 {
	//coal,oil,uranium,lead,iron,bauxite,gasoline,munitions,steel,aluminum,food
	public static DiscordApi api;
	public static MessageSent ms;
	protected static final String[] ADMINISTRATORS = {"358794817595113476", "245470898293833729"};
	public static PaWAPI mainapi;
	public static PaWAPI tradeapi;
	public static PaWAPI bigapis;
	private static JFrame frame2;
	public static void main(String[] args) {
		Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF); 
		frame2 = new JFrame ("Intialize bot");
        frame2.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame2.getContentPane().add (new FirstPanel());
        frame2.pack();
        frame2.setVisible (true);
        WebColors.callme = true;
	}
	public static boolean isAdmin(String id) {
		for(String s : ADMINISTRATORS)
			if(s.equals(id))
				return true;
		return false;
	}
	
	public static void consolPanel() {
		frame2.dispose();
		EventQueue.invokeLater(new consolThread());
	}
}