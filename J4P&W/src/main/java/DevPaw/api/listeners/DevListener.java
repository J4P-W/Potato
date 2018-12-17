package DevPaw.api.listeners;

import java.io.Serializable;
import java.util.List;

public interface DevListener<O> extends Serializable {
	public void execute(List<O> data);
}
