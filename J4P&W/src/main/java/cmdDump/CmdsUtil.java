package cmdDump;

public class CmdsUtil {
	public static String hyperUrl(String text, String url) {
		return String.format("[%s](%s)", text, url);
	}
}
