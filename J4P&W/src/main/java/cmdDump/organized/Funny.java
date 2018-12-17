package cmdDump.organized;

import org.javacord.api.entity.message.Message;

public class Funny {
	
	public static void ghostPingeth(Message m) throws InterruptedException {
		Thread.sleep(1000);
		m.delete();
	}
}
