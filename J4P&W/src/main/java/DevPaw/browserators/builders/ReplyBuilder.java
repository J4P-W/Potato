package DevPaw.browserators.builders;

import DevPaw.browserators.exceptions.MessageSizeException;

public class ReplyBuilder {
	private String content;
	public ReplyBuilder(String content) throws MessageSizeException {
		if(content.length() > 8000)
			throw new MessageSizeException("\""+content+"\" is more than 8000 characters");
		this.content = content;
	}
	public String getContent() {
		return content;
	}
}
