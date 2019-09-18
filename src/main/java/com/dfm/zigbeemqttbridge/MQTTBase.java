package com.dfm.zigbeemqttbridge;

import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public abstract class MQTTBase {

	@Value("${mqtt.server.ip}")
	private String serverIP;
	
	@Value("${mqtt.server.port}")
	private String serverPort;
	
	@Value("${mqtt.server.user}")
	private String serverUser;
	
	@Value("${mqtt.server.pass}")
	private String serverPass;
	
	@Value("${mqtt.receiver.topic}")
	private String receiverTopic;
	
	@Value("${mqtt.sender.topic}")
	private String senderTopic;
	
	
	public String getClientID() {
		return (MqttAsyncClient.generateClientId());
	}

	public String getServerIP() {
		return serverIP;
	}

	public String getServerPort() {
		return serverPort;
	}

	public String getServerUser() {
		return serverUser;
	}

	public String getServerPass() {
		return serverPass;
	}

	public String getReceiverTopic() {
		return receiverTopic;
	}

	public String getSenderTopic() {
		return senderTopic;
	}
	
	public String getMQTTUrl() {
		return(String.format("tcp://%s:%s", getServerIP(), getServerPort()));
	}
	
}
