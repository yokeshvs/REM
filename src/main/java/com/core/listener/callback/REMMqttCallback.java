package com.core.listener.callback;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.core.task.TaskRejectedException;
import org.springframework.core.task.TaskTimeoutException;
import org.springframework.scheduling.backportconcurrent.ThreadPoolTaskExecutor;

import com.core.constants.REMConstants;

public class REMMqttCallback implements MqttCallback {
	private static final Logger LOGGER = LogManager.getLogger("REMMqttCallback");
	private MqttClient client;
	private ThreadPoolTaskExecutor taskExecutor;

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

	public REMMqttCallback() {
		// TODO Auto-generated constructor stub
	}

	public REMMqttCallback(MqttClient mqttClient, ThreadPoolTaskExecutor executor) {
		this.client = mqttClient;
		this.taskExecutor = executor;

	}

	@Override
	public void connectionLost(Throwable cause) {
		LOGGER.error("REMMqttCallback::connectionLost");
	}

	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		try {
			taskExecutor.execute(new Runnable() {
				public void run() {
					if (topic.equalsIgnoreCase(REMConstants.DEVICE_TOPIC)) {
						LOGGER.debug("\n**************\nREMMqttCallback::messageArrived::message: {}\n**************",
								message.toString());
					}
				}
			});
		} catch (TaskTimeoutException e) {
			LOGGER.error("REMMqttCallback::messageArrived::TaskTimeoutException - {}", e.getMessage());
		} catch (TaskRejectedException e) {
			LOGGER.error("REMMqttCallback::messageArrived::TaskRejectedException - {}", e.getMessage());
		} catch (Exception e) {
			LOGGER.error("REMMqttCallback::messageArrived::Exception - {}", e.getMessage());
		}
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {
		LOGGER.error("REMMqttCallback::deliveryComplete::token: {}", token.toString());
	}

}
