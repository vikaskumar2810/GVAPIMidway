package com.gv.midway.audit;

import java.util.EventObject;

import org.apache.camel.Exchange;
//import org.apache.camel.management.event.ExchangeCreatedEvent;
import org.apache.camel.management.event.ExchangeCompletedEvent;
//ExchangeSentEvent;
import org.apache.camel.support.EventNotifierSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.gv.midway.pojo.BaseResponse;
import com.gv.midway.pojo.audit.Audit;

public class AuditLogResponseEventNotifer extends EventNotifierSupport {

	private static final Logger logger = LoggerFactory
			.getLogger(AuditLogResponseEventNotifer.class); // Initializing

	@Autowired
	MongoTemplate mongoTemplate;

	public void notify(EventObject event) throws Exception {
		if (event instanceof ExchangeCompletedEvent) {
			ExchangeCompletedEvent create = (ExchangeCompletedEvent) event;
			Exchange exchange = create.getExchange();
			logger.info("In Audit log Response");

			if (exchange.getIn().getBody().getClass()
					.isInstance(BaseResponse.class)) {

				BaseResponse baseResponse = (BaseResponse) exchange.getIn()
						.getBody();
				String msgBody = (String) exchange.getIn().getBody().toString();
				String TransactionId = (String) exchange
						.getProperty("TransactionId");

				Audit audit = new Audit();
				audit.setCarrier(baseResponse.getHeader().getBsCarrier());
				audit.setSource(baseResponse.getHeader().getSourceName());
				audit.setApiAction(exchange.getFromEndpoint().toString());
				audit.setInboundURL(exchange.getFromEndpoint().toString());
				audit.setTransactionId(TransactionId);
				audit.setPayload(msgBody);
				mongoTemplate.save(audit);

			}
		}

	}

	public boolean isEnabled(EventObject event) {
		// we only want the sent events
		return event instanceof ExchangeCompletedEvent;
	}

	protected void doStart() throws Exception {
		// noop
	}

	protected void doStop() throws Exception {
		// noop
	}
}
