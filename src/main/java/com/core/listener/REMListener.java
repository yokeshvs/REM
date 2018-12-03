package com.core.listener;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import com.core.constants.REMConstants;
import com.core.listener.callback.REMMqttCallbackHandler;
import com.core.services.FirebaseService;

@Component
public class REMListener {

	private static final Logger LOGGER = LogManager.getLogger("REMListener");
	private ThreadPoolTaskExecutor remTaskExecutor;
	private boolean listenerEnabled;
	
	@Autowired(required = true)
	@Qualifier("FirebaseServiceImpl")
	private FirebaseService firebaseService;

	public ThreadPoolTaskExecutor getRemTaskExecutor() {
		return remTaskExecutor;
	}

	public void setRemTaskExecutor(ThreadPoolTaskExecutor remTaskExecutor) {
		this.remTaskExecutor = remTaskExecutor;
	}

	public boolean isListenerEnabled() {
		return listenerEnabled;
	}

	public void setListenerEnabled(boolean listenerEnabled) {
		this.listenerEnabled = listenerEnabled;
	}

	public FirebaseService getFirebaseService() {
		return firebaseService;
	}

	public void setFirebaseService(FirebaseService firebaseService) {
		this.firebaseService = firebaseService;
	}

	public void startTopicListener() {
		LOGGER.debug("REMListener::startTopicListener");
		if (isListenerEnabled()) {
			List<String> topics = getListOfMqttTopics();
			LOGGER.debug("REMListener::startTopicListener::remTaskExecutor: {}", remTaskExecutor);
			REMMqttCallbackHandler callbackHandler = new REMMqttCallbackHandler(topics, remTaskExecutor,
					firebaseService);
			new Thread(callbackHandler).start();
		}
	}

	private List<String> getListOfMqttTopics() {
		List<String> topics = new ArrayList<String>();
//		topics.add(REMConstants.DEVICE_TOPIC);
//		topics.add(REMConstants.DEVICE_TOPIC_1);
		topics.add(REMConstants.DEVICE_TOPIC_2);
		LOGGER.debug("REMListener::getListOfMqttTopics::Topics: {}", topics.toString());
		return topics;
	}
}
