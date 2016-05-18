package com.gv.midway.processor.callbacks;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gv.midway.pojo.callback.common.response.CallbackCommonResponse;

public class CallbackPostProcessor implements Processor {
	Logger log = Logger.getLogger(CallbackPostProcessor.class);

	public void process(Exchange exchange) throws Exception {
		log.info("Inside CallbackPostProcessor process " + exchange.getIn().getBody());
		log.info("Exchange inside");
		/**
		 * converting target response to byte because we need to send it to
		 * kafka
		 */
		log.info("Callback Post Processor >>> " + exchange.getIn().getBody());
		CallbackCommonResponse req = (CallbackCommonResponse) exchange.getIn().getBody(CallbackCommonResponse.class);
		ObjectMapper objectMapper = new ObjectMapper();
		byte[] bytes = null;
		try {
			bytes = objectMapper.writeValueAsString(req).getBytes();
			log.info(" converted to bytes");
		} catch (JsonProcessingException e) {
		}

		exchange.getIn().setBody(bytes);
	}

}
