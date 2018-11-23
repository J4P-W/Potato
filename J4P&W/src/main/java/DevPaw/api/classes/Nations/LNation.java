package DevPaw.api.classes.Nations;

import java.io.Serializable;

public class LNation implements Serializable {
	private static final long serialVersionUID = 4824463550348651394L;
	public int nationid;
    public String nation;
    public String leader;
    public String continent;
    public String war_policy;
    public String color;
    public String alliance;
    public int allianceid;
    public int allianceposition;
    public int cities;
    public int offensivewars;
    public int defensivewars;
    public int score;
    public int rank;
    public String vacmode;
    public int minutessinceactive;
}
