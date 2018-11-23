package main;

import DevPaw.api.classes.Wars.LWar;
import DevPaw.api.classes.Wars.Wars;
import DevPaw.api.exceptions.UnsuccessfullAPIException;
import DevPaw.api.listeners.DevExecutor;
import DevPaw.api.listeners.ObjectSource;

public class Init {
	public static DevExecutor<LWar> warExecutor;
	public static void Initialize() {
		warExecutor = new DevExecutor<LWar>(new ObjectSource<LWar>() {
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
