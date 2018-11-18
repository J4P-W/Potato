package DevPaw.api.listeners;

import java.util.ArrayList;

public interface DevListener<O> {
	public void execute(ArrayList<O> data);
}
