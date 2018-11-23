package DevPaw.api.listeners;

import java.io.Serializable;
import java.util.ArrayList;

public interface DevListener<O> extends Serializable {
	public void execute(ArrayList<O> data);
}
