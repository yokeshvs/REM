package com.core.listener.callback;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.core.task.TaskRejectedException;
import org.springframework.core.task.TaskTimeoutException;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.core.constants.REMConstants;
import com.core.services.FirebaseService;

public class REMMqttCallback implements MqttCallback {
	private static final Logger LOGGER = LogManager.getLogger("REMMqttCallback");
	private MqttClient client;
	private List<String> mqttTopics;
	private ThreadPoolTaskExecutor taskExecutor;

	private FirebaseService firebaseService;

	public MqttClient getClient() {
		return client;
	}

	public void setClient(MqttClient client) {
		this.client = client;
	}

	public ThreadPoolTaskExecutor getTaskExecutor() {
		return taskExecutor;
	}

	public void setTaskExecutor(ThreadPoolTaskExecutor taskExecutor) {
		this.taskExecutor = taskExecutor;
	}

	public FirebaseService getFirebaseService() {
		return firebaseService;
	}

	public void setFirebaseService(FirebaseService firebaseService) {
		this.firebaseService = firebaseService;
	}

	public REMMqttCallback() {
		// TODO Auto-generated constructor stub
	}

	public REMMqttCallback(MqttClient mqttClient, ThreadPoolTaskExecutor executor, FirebaseService firebaseService) {
		this.client = mqttClient;
		this.taskExecutor = executor;
		this.firebaseService = firebaseService;
	}

	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		try {
			taskExecutor.execute(new Runnable() {
				public void run() {
					if (topic.equalsIgnoreCase(REMConstants.DEVICE_TOPIC)) {
//						LOGGER.debug("\n**************\nREMMqttCallback::messageArrived::message: {}\n**************",
//								message.toString());

					} else if (topic.equalsIgnoreCase(REMConstants.DEVICE_TOPIC_1)) {
//						LOGGER.debug("\n**************\nREMMqttCallback::messageArrived::message: {}\n**************",
//								message.toString());
//						firebaseService.updateDeviceInfo(message.toString());
//						LOGGER.debug("\n**************\nREMMqttCallback::messageupdated::successful\n**************");
					}
				}
			});
		} catch (TaskTimeoutException e) {
			LOGGER.error("REMMqttCallback::messageArrived::TaskTimeoutException - {}", e.getMessage());
		} catch (TaskRejectedException e) {
			LOGGER.error("REMMqttCallback::messageArrived::TaskRejectedException - {}", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("REMMqttCallback::messageArrived::Exception - {}", e.getMessage());
		}
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {
		LOGGER.error("REMMqttCallback::deliveryComplete::token: {}", token.toString());
	}

	@Override
	public void connectionLost(Throwable cause) {
		LOGGER.error("REMMqttCallback::connectionLost");
		while (!client.isConnected()) {
			try {
				// Setup a callback
				client.setCallback(this);
				MqttConnectOptions options = new MqttConnectOptions();
				options.setKeepAliveInterval(20);
				options.setConnectionTimeout(100);
				// connect
				client.connect(options);

				// re-subscribe to all topics including any new ones
				mqttTopics = getListOfMqttTopics();
				for (int i = 0; i < mqttTopics.size(); i++) {
					LOGGER.debug("REMMqttCallback::connectionLost::Subscribed to " + mqttTopics.get(i));
					client.subscribe(mqttTopics.get(i), REMConstants.QOS);
				}

				LOGGER.info("REMMqttCallback::connectionLost::Client connected successfully to the mosquitto server: "
						+ client.getServerURI());

			} catch (Exception e) {
				LOGGER.error("REMMqttCallback::connectionLost::Client failed to connect to mosquitto server: "
						+ client.getServerURI() + "Error detail: " + e.getMessage(), e);
			}
		}
	}

	private List<String> getListOfMqttTopics() {
		List<String> topics = new ArrayList<String>();
		topics.add(REMConstants.DEVICE_TOPIC);
		topics.add(REMConstants.DEVICE_TOPIC_1);
		LOGGER.debug("REMListener::getListOfMqttTopics::Topics: {}", topics.toString());
		return topics;
	}

}
