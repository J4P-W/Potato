package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.HashMap;

import pawstuff.CityLayout;
import utils.general.Saveable;

public class AuditData extends Saveable implements Serializable {
	private static final long serialVersionUID = 3544862744397316322L;
	public static HashMap<String, CityLayout> data; // Server id, Data
	@SuppressWarnings("static-access")
	public AuditData() {
		filename = "ServerData.data";
		if(!new File("ServerData.data").exists()) {
			data = new HashMap<String, CityLayout>();
			try {
				new File("ServerData.data").createNewFile();
				save();
			} catch (IOException e) {e.printStackTrace();}
		}
		else {
			try {
				ObjectInputStream in = new ObjectInputStream(new FileInputStream("ServerData.data"));
				AuditData s = (AuditData) in.readObject();
				data = s.data;
				in.close();
			} catch (Exception e) {e.printStackTrace();}
		}
	}
}
