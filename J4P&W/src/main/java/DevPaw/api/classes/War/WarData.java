
package DevPaw.api.classes.War;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WarData {

    @SerializedName("war_ended")
    @Expose
    public boolean warEnded;
    @SerializedName("date")
    @Expose
    public String date;
    @SerializedName("aggressor_id")
    @Expose
    public String aggressorId;
    @SerializedName("defender_id")
    @Expose
    public String defenderId;
    @SerializedName("aggressor_alliance_name")
    @Expose
    public String aggressorAllianceName;
    @SerializedName("aggressor_is_applicant")
    @Expose
    public boolean aggressorIsApplicant;
    @SerializedName("defender_alliance_name")
    @Expose
    public String defenderAllianceName;
    @SerializedName("defender_is_applicant")
    @Expose
    public boolean defenderIsApplicant;
    @SerializedName("aggressor_offering_peace")
    @Expose
    public boolean aggressorOfferingPeace;
    @SerializedName("war_reason")
    @Expose
    public String warReason;
    @SerializedName("ground_control")
    @Expose
    public String groundControl;
    @SerializedName("air_superiority")
    @Expose
    public String airSuperiority;
    @SerializedName("blockade")
    @Expose
    public String blockade;
    @SerializedName("aggressor_military_action_points")
    @Expose
    public String aggressorMilitaryActionPoints;
    @SerializedName("defender_military_action_points")
    @Expose
    public String defenderMilitaryActionPoints;
    @SerializedName("aggressor_resistance")
    @Expose
    public String aggressorResistance;
    @SerializedName("defender_resistance")
    @Expose
    public String defenderResistance;
    @SerializedName("aggressor_is_fortified")
    @Expose
    public boolean aggressorIsFortified;
    @SerializedName("defender_is_fortified")
    @Expose
    public boolean defenderIsFortified;
    @SerializedName("turns_left")
    @Expose
    public int turnsLeft;
    @SerializedName("war_type")
    @Expose
    public String warType;
    @SerializedName("aggressor_infra_lost")
    @Expose
    public int aggressorInfraLost;
    @SerializedName("defender_infra_lost")
    @Expose
    public int defenderInfraLost;
    @SerializedName("aggressor_money_lost")
    @Expose
    public int aggressorMoneyLost;
    @SerializedName("defender_money_lost")
    @Expose
    public int defenderMoneyLost;
    @SerializedName("aggressor_soldiers_lost")
    @Expose
    public int aggressorSoldiersLost;
    @SerializedName("defender_soldiers_lost")
    @Expose
    public int defenderSoldiersLost;
    @SerializedName("aggressor_tanks_lost")
    @Expose
    public int aggressorTanksLost;
    @SerializedName("defender_tanks_lost")
    @Expose
    public int defenderTanksLost;
    @SerializedName("aggressor_aircraft_lost")
    @Expose
    public int aggressorAircraftLost;
    @SerializedName("defender_aircraft_lost")
    @Expose
    public int defenderAircraftLost;
    @SerializedName("aggressor_ships_lost")
    @Expose
    public int aggressorShipsLost;
    @SerializedName("defender_ships_lost")
    @Expose
    public int defenderShipsLost;

}
