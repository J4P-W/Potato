package main;

import DevPaw.api.classes.Wars.LWar;
import DevPaw.api.classes.Wars.Wars;
import DevPaw.api.exceptions.UnsuccessfullAPIException;
import DevPaw.api.listeners.DevExecutor;
import DevPaw.api.listeners.ObjectSource;

public class Init {
	public static DevExecutor<LWar> warExecutor;
	public static void Initialize() throws UnsuccessfullAPIException {
		warExecutor = new DevExecutor<LWar>(new ObjectSource<LWar>() {
			@Override
			public LWar[] getSource() throws UnsuccessfullAPIException {
				Wars ws = App.bigapis.getWars();
				LWar[] warray = ws.wars;
				return warray;
			}
			@Override
			public boolean equals(LWar obj1, LWar obj2) {
				return obj1.warID.contentEquals(obj2.warID);
			}
		}, 1000);
		
	}
}
