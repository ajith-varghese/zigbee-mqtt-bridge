package com.dfm.zigbeemqttbridge;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WSClient extends WebSocketClient {
	
	@Autowired
	MQTTSender sender;
	
	@Autowired
	public WSClient(@Value("${zigbee.server.ip}") String serverIP, @Value("${zigbee.server.port}") String serverPort) throws URISyntaxException {
		super(new URI(WSClient.getWebsocketUrl(serverIP, serverPort)));
		super.connect();
	}
	
	public WSClient( URI serverUri , Draft draft ) {
		super( serverUri, draft );
	}

	public WSClient( URI serverURI ) {
		super( serverURI );
	}

	public WSClient( URI serverUri, Map<String, String> httpHeaders ) {
		super(serverUri, httpHeaders);
	}

	@Override
	public void onOpen( ServerHandshake handshakedata ) {
		System.out.println( "opened connection" );
	}

	@Override
	public void onMessage( String message ) {
		System.out.println( "received: " + message );
		sender.sendMessage(message);
	}

	@Override
	public void onClose( int code, String reason, boolean remote ) {
		System.out.println( "Connection closed by " + ( remote ? "remote peer" : "us" ) + " Code: " + code + " Reason: " + reason );
	}

	@Override
	public void onError( Exception ex ) {
		ex.printStackTrace();
	}
	
	public static String getWebsocketUrl(String serverIP, String serverPort) {
		String ip = serverIP;
		String port = serverPort;
		
		if(ip==null || ip.trim().length()==0) {
			ip="127.0.0.1";
		}
		if(port==null || port.trim().length()==0) {
			port="443";
		}
		return(String.format("ws://%s:%s", ip, port));
	}
}