
package DevPaw.api.classes.TradePrice;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TradePrice {

    @SerializedName("resource")
    @Expose
    public String resource;
    @SerializedName("avgprice")
    @Expose
    public String avgprice;
    @SerializedName("marketindex")
    @Expose
    public String marketindex;
    @SerializedName("highestbuy")
    @Expose
    public Highestbuy highestbuy;
    @SerializedName("lowestbuy")
    @Expose
    public Lowestbuy lowestbuy;

}
