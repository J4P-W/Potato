package DevPaw.api.listeners;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class DevExecutor<O> {
	public ObjectSource<O> objects;
	public O[] old;
	
	public HashMap<String,DevListener<O>> listeners;
	public Thread listenerThread;
	public DevExecutor(ObjectSource<O> source, long delay) {
		this.objects = source;
		old = source.getSource();
		listeners = new HashMap<String,DevListener<O>>();
		new Thread(new Runnable() {
			public void run() {
				while(true) {
					try {
						Thread.sleep(delay);
					} catch (InterruptedException e) {
						e.printStackTrace();
						return;
					}
					ArrayList<O> data = null;
					data = update();
					for(Entry<String,DevListener<O>> d: listeners.entrySet())
						d.getValue().execute(data);
				}
			}
		}).start();
	}
	public void removeListener(String key) {
		listeners.remove(key);
	}
	public ArrayList<O> update() {
		O[] data = objects.getSource();
		ArrayList<O> diff = new ArrayList<O>();
		for(int x = 0; x < data.length; x++)
			if(!inOldData(data[x]))
				diff.add(data[x]);
		old = data;
		return diff;
	}
	public boolean inOldData(O o) {
		for(int x = 0; x < old.length; x++)
			if(objects.equals(old[x], o))
				return true;
		return false;
	}
	
}
