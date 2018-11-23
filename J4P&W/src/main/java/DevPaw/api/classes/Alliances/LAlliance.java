package DevPaw.api.classes.Alliances;

import java.io.Serializable;
import java.util.List;

public class LAlliance implements Serializable {
	private static final long serialVersionUID = 4824463550348651394L;
    public String id;
    public String founddate;
    public String name;
    public String acronym;
    public String color;
    public String continent;
    public int rank;
    public int members;
    public double score;
    public List<String> heirids;
    public List<String> officerids;
    public List<String> leaderids;
    public double avgscore;
    public String flagurl;
    public String forumurl;
    public String ircchan;
}
