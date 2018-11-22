package DevPaw.utilities;

import DevPaw.api.classes.Alliance;
import DevPaw.api.classes.Military;
import DevPaw.api.exceptions.UnsuccessfullAPIException;
import main.App;

public class MilitLevel {
	private double militarization;
	public MilitLevel(Alliance a) throws UnsuccessfullAPIException {
		double avg = 0;
		for(Integer id: a.member_id_list) {
			Military m = new Military(App.mainapi.getNation(id+""));
			avg += m.prep;
		}
		avg/= a.member_id_list.size();
		militarization = avg;
	}
	public double getMilitarization() {
		return militarization;
	}
}
