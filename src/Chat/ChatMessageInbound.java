package Chat;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;
import org.apache.catalina.websocket.*;

public class ChatMessageInbound extends MessageInbound{

	private static List<WsOutbound> connections = new ArrayList<WsOutbound>();
	
	private final String nickname;

	public ChatMessageInbound(int id) {
		this.nickname = "GUEST_" + id;
	}
	
	@Override
	protected void onBinaryMessage(ByteBuffer arg0) throws IOException {
		throw new UnsupportedOperationException("Binary not supported");
	}

	@Override
	protected void onTextMessage(CharBuffer message) throws IOException {
		System.out.println("onTextMessage");
		String msg = "(" + nickname + ")" + message.toString();
		broadcast(msg);
	}

	private void broadcast(String msg) {
		System.out.println("broadcast");
		for (WsOutbound connection : connections) {
			try {
				CharBuffer buffer = CharBuffer. wrap(msg);
				connection.writeTextMessage(buffer);
			} catch (IOException ignore) {
				//Ignore
			}
		}
	}
	
	@Override
	protected void onOpen(WsOutbound outbound) {
		System.out.println("onOpen");
		connections.add(outbound);
		System.out.println(connections);
		String message = String.format("** %s %s",nickname, "has joined **");
		broadcast(message);
	}
	
	@Override
	protected void onClose(int status) {
		System.out.println("onClose");
		String message = String.format("** %s %s",nickname, "has disconnected **");
		broadcast(message);
	}
}
