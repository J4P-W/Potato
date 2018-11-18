package DevPaw.api.listeners;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;

import DevPaw.api.exceptions.UnsuccessfullAPIException;

public class DevExecutor<O> {
	public ObjectSource<O> objects;
	public ArrayList<O> old;
	
	public HashMap<String,DevListener<O>> listeners;
	public Thread listenerThread;
	public DevExecutor(ObjectSource<O> source, long delay) throws UnsuccessfullAPIException {
		this.objects = source;
		old = new ArrayList<O>(Arrays.asList(source.getSource()));
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
					try {
						data = update();
					} catch (UnsuccessfullAPIException e) {
						e.printStackTrace();
						return;
					}
					for(Entry<String,DevListener<O>> d: listeners.entrySet())
						d.getValue().execute(data);
				}
			}
		}).start();
	}
	public void removeListener(String key) {
		listeners.remove(key);
	}
	public ArrayList<O> update() throws UnsuccessfullAPIException {
		O[] retrieve = objects.getSource();
		ArrayList<O> different = new ArrayList<O>();
		for(int x = 0; x < retrieve.length; x++)
			for(int y = 0; y < old.size(); y++)
				if(!objects.equals(retrieve[x], old.get(x))) {
					different.add(retrieve[x]);
					break;
				}
		old = new ArrayList<O>(Arrays.asList(retrieve));
		return different;
	}
}
