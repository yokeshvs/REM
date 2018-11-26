package com.core.scheduler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component("ledgerSyncService")
public class LedgerSyncService {

	private static final Logger LOGGER = LogManager.getLogger("LedgerSyncService");
	
	
	public void syncLedger() {
		LOGGER.debug("inside syncLedger");
		
	}
}
