package com.legaoyi.storer.handler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.legaoyi.storer.message.ExchangeMessage;
import com.legaoyi.storer.util.Constants;
import com.legaoyi.activemq.ActiveMqProducer;

/**
 * @author <a href="mailto:shengbo.gao@gmail.com;gaoshengbo@legaoyi.com">gaoshengbo</a>
 * @version 1.0.0
 * @since 2019-08-18
 */
@Component("downstreamMessageSendHandler")
public class DownstreamMessageSendHandler extends MessageHandler {

	@Autowired
	@Qualifier("activeMqProducer")
	private ActiveMqProducer activeMqProducer;

	private static Map<String, Integer> seqMap = new ConcurrentHashMap<String, Integer>();

	private static final long MAX_MESSAGE_SEQ = 65535;

	@Override
	@SuppressWarnings("unchecked")
	public void handle(ExchangeMessage message) throws Exception {
		Map<?, ?> map = (Map<?, ?>) message.getMessage();
		Map<String, Object> messageHeader = (Map<String, Object>) map.get(Constants.MAP_KEY_MESSAGE_HEADER);
		if (messageHeader != null) {
			String simCode = (String) messageHeader.get(Constants.MAP_KEY_SIM_CODE);
			messageHeader.put("messageSeq", generateSeq(simCode));
		}
		activeMqProducer.sendQueueMessage(message.toString());
		if (this.getSuccessor() != null) {
			this.getSuccessor().handle(message);
		}
	}

	private int generateSeq(String simCode) {
		Integer seq = seqMap.get(simCode);
		if (seq == null || seq > MAX_MESSAGE_SEQ) {
			seq = 1;
		}
		return seq;
	}

}
