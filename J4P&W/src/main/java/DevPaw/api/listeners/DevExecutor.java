package DevPaw.api.listeners;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import DevPaw.utilities.database.DatabaseManager;

public class DevExecutor<T> {
	public ObjectSource<T> objects;
	public T[] old;
	
	public Map<String,DevListener<T>> listeners;
	
	public Thread listenerThread;
	public DatabaseManager<Map<String,DevListener<T>>> listendb;
	
	public DevExecutor(String name,ObjectSource<T> source, long delay) {
		this.objects = source;
		old = source.getSource();
		try {
			listendb = new DatabaseManager<>(new File("Executors/"+name+".txt"));
			if(listendb.test()) {
				listeners = listendb.load();
			}
			else {
				listeners = new HashMap<>();
				listendb.save(listeners);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		new Thread(() -> {
			while(true) {
				try {
					Thread.sleep(delay);
				} catch (InterruptedException e) {
					e.printStackTrace();
					return;
				}
				List<T> data = null;
				data = update();
				for(Entry<String,DevListener<T>> d: listeners.entrySet())
					d.getValue().execute(data);
			}
		}).start();
	}
	public void removeListener(String key) {
		listeners.remove(key);
	}
	public List<T> update() {
		T[] data = objects.getSource();
		ArrayList<T> diff = new ArrayList<>();
		for(int x = 0; x < data.length; x++)
			if(!inTldData(data[x]))
				diff.add(data[x]);
		old = data;
		return diff;
	}
	public boolean inTldData(T o) {
		for(int x = 0; x < old.length; x++)
			if(objects.equals(old[x], o))
				return true;
		return false;
	}
	
}
