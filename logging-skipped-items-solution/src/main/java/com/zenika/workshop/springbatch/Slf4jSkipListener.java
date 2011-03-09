/**
 * 
 */
package com.zenika.workshop.springbatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.listener.SkipListenerSupport;

/**
 * @author acogoluegnes
 *
 */
public class Slf4jSkipListener<T,S> extends SkipListenerSupport<T, S> {
	
	private static final Logger LOG = LoggerFactory.getLogger(Slf4jSkipListener.class);

	@Override
	public void onSkipInRead(Throwable t) {
		LOG.warn("skipped item: {}",t.toString());
	}
	
}
