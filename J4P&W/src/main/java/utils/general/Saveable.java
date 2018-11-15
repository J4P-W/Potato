package utils.general;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Saveable implements Serializable {
	private static final long serialVersionUID = 4538501056214130196L;
	public String filename;
	public void save() {
		ObjectOutputStream out;
		try {
			out = new ObjectOutputStream(new FileOutputStream(filename));
			out.writeObject(this);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
