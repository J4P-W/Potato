package DevPaw.api.listeners;



public interface ObjectSource<O> {
	public O[] getSource();
	public boolean equals(O a, O b);
}
