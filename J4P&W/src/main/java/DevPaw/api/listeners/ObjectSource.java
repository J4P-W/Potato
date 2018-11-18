package DevPaw.api.listeners;

import DevPaw.api.exceptions.UnsuccessfullAPIException;

public interface ObjectSource<O> {
	public O[] getSource() throws UnsuccessfullAPIException;
	public boolean equals(O obj1, O obj2);
}
