package DevPaw.utilities.database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class DatabaseManager<T> {
	private File destination;
	public DatabaseManager(File db) throws IOException {
		if(!db.exists()) {
			db.getParentFile().mkdirs();
			db.createNewFile();
		}
		destination = db;
	}
	public boolean test() {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(destination));
			@SuppressWarnings({ "unchecked", "unused" })
			T obj = (T)ois.readObject();
			ois.close();
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
	public void save(T save) throws FileNotFoundException, IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(destination));
		oos.writeObject(save);
		oos.close();
	}
	@SuppressWarnings("unchecked")
	public T load() throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(destination));
		T obj = (T)ois.readObject();
		ois.close();
		return obj;
	}
}
