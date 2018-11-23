package DevPaw.api.listeners;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import DevPaw.utilities.database.DatabaseManager;

public class DevExecutor<O> {
	public ObjectSource<O> objects;
	public O[] old;
	
	public HashMap<String,DevListener<O>> listeners;
	
	public Thread listenerThread;
	public DatabaseManager<HashMap<String,DevListener<O>>> listendb;
	
	public DevExecutor(String name,ObjectSource<O> source, long delay) {
		this.objects = source;
		old = source.getSource();
		try {
			listendb = new DatabaseManager<HashMap<String,DevListener<O>>>(new File("Executors/"+name+".txt"));
			if(listendb.test()) {
				listeners = listendb.load();
			}
			else {
				listeners = new HashMap<String,DevListener<O>>();
				listendb.save(listeners);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
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
