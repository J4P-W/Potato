
package DevPaw.api.classes.War;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class War {

    @SerializedName("success")
    @Expose
    public boolean success;
    @SerializedName("war")
    @Expose
    public List<WarData> war = null;

}
