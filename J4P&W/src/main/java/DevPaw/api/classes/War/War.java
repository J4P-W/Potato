
package DevPaw.api.classes.War;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class War implements Serializable {
	private static final long serialVersionUID = 4824463550348651394L;

    @SerializedName("success")
    @Expose
    public boolean success;
    @SerializedName("war")
    @Expose
    public List<WarData> war = null;

}
