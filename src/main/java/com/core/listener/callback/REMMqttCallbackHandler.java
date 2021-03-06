package com.core.listener.callback;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.core.constants.REMConstants;
import com.core.services.FirebaseService;

public class REMMqttCallbackHandler implements Runnable {
	private static final Logger LOGGER = LogManager.getLogger("REMMqttCallbackHandler");
	private List<String> mqttTopics;
	private ThreadPoolTaskExecutor taskExecutor;
	private Boolean mosquittoEnabled;
	private FirebaseService firebaseService;

	public List<String> getMqttTopics() {
		return mqttTopics;
	}

	public void setMqttTopics(List<String> mqttTopics) {
		this.mqttTopics = mqttTopics;
	}

	public ThreadPoolTaskExecutor getTaskExecutor() {
		return taskExecutor;
	}

	public void setTaskExecutor(ThreadPoolTaskExecutor taskExecutor) {
		this.taskExecutor = taskExecutor;
	}

	public Boolean getMosquittoEnabled() {
		return mosquittoEnabled;
	}

	public void setMosquittoEnabled(Boolean mosquittoEnabled) {
		this.mosquittoEnabled = mosquittoEnabled;
	}

	public REMMqttCallbackHandler() {
		// TODO Auto-generated constructor stub
	}

	public REMMqttCallbackHandler(List<String> topics, ThreadPoolTaskExecutor taskExecutor, FirebaseService firebaseService) {
		this.mqttTopics = topics;
		this.taskExecutor = taskExecutor;
		this.mosquittoEnabled = true;
		this.firebaseService = firebaseService;
	}

	@Override
	public void run() {
		String mqttServer = mosquittoEnabled ? "test.mosquitto.org" : "broker.hivemq.com";
		int mqttPort = 1883;
		try {
			String serverURI = mosquittoEnabled ? "tcp://" + mqttServer : "tcp://" + mqttServer + mqttPort;
			LOGGER.debug("REMMqttCallbackHandler::run::serverURI - {}", serverURI);

			String clientId = "REMListener-" + ((int) (Math.random() * 9000) + 1000);
			MqttClient client = new MqttClient(serverURI, clientId);
			LOGGER.info("REMMqttCallbackHandler::run::clientId: {}", clientId);

			MqttConnectOptions options = new MqttConnectOptions();
			options.setKeepAliveInterval(20);
			options.setConnectionTimeout(100);

			// Setup a callback
			client.setCallback(new REMMqttCallback(client, taskExecutor, firebaseService));

			// Connect to the broker
			client.connect(options);

			// subscribe to topics
			for (int i = 0; i < mqttTopics.size(); i++) {
				LOGGER.info("REMMqttCallbackHandler::run::Subscribed to: {}", mqttTopics.get(i));
				client.subscribe(mqttTopics.get(i), REMConstants.QOS);
			}
			LOGGER.info("REMMqttCallbackHandler::run::Handler registered Successfully");
		} catch (MqttException e) {
			LOGGER.error("REMMqttCallbackHandler::run::MqttException - {}", e.getMessage());
		} catch (Exception e) {
			LOGGER.error("REMMqttCallbackHandler::run::Exception - {}", e.getMessage());
		}
	}

}
