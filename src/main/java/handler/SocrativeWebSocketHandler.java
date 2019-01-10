package handler;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import main.MainWSSocrative;

@WebSocket
public class SocrativeWebSocketHandler {

	@OnWebSocketConnect
	public void onConnect(Session session) throws Exception {
		String username = "User" + MainWSSocrative.userNumber.incrementAndGet();
		MainWSSocrative.userUsernameMap.put(session, username);
		System.out.println(username + " conexion inicializada");
	}
	
	@OnWebSocketClose
	public void onClose(Session user, int statusCode, String reason) {
		String username = MainWSSocrative.userUsernameMap.get(user);
		MainWSSocrative.userUsernameMap.remove(user);
		System.out.println(username + " conexion terminada");
	}
	
	@OnWebSocketMessage
    public void onMessage(Session user, String message) {
		MainWSSocrative.broadcastMessage(MainWSSocrative.userUsernameMap.get(user), message);
    }
}
