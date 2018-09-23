package com.core.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.backportconcurrent.ThreadPoolTaskExecutor;

public class REMListener {

	private static final Logger LOGGER = LogManager.getLogger("REMListener");
	private ThreadPoolTaskExecutor remTaskExecutor;
}
