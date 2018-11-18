package DevPaw.utilities;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URL;
import java.net.URLConnection;

public class DevReader { 
	private boolean print;
	public PrintStream out;
	public DevReader() {
		this.print = false;
	}
	public DevReader(PrintStream out, boolean print) {
		this.print = print;
		this.out = out;
	}
	public DevReader(PrintStream out) {
		this.out = out;
		this.print = true;
	}
	public void setPrint(boolean print) {
		this.print = print;
	}
	public String speedURL(String url) { // Faster in general
		try {
			URL theURL = new URL(url);
			URLConnection urlConn = theURL.openConnection();
			urlConn.setDoOutput(true);
			urlConn.setReadTimeout(20000);
			BufferedReader in = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String inputLine = in.readLine();
			while(inputLine != null) {
				sb.append(inputLine);
				inputLine = in.readLine();
			}
			if(print)
				out.println(url+" returned " + sb.toString());
			in.close();
			return sb.toString();
		} catch(Exception e) {e.printStackTrace();}
        return null;
	}
} 