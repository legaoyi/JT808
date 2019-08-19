package com.legaoyi.storer.handler;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.legaoyi.storer.message.ExchangeMessage;
import com.legaoyi.storer.util.Constants;

/**
 * @author <a href="mailto:shengbo.gao@gmail.com;gaoshengbo@legaoyi.com">gaoshengbo</a>
 * @version 1.0.0
 * @since 2019-08-18
 */
@Component(Constants.ELINK_MESSAGE_STORER_BEAN_PREFIX + "0100"
		+ Constants.ELINK_MESSAGE_STORER_MESSAGE_HANDLER_BEAN_SUFFIX)
public class Jt808_0100_MessageHandler extends MessageHandler {

	private static final Logger logger = LoggerFactory.getLogger(Jt808_0100_MessageHandler.class);

	@Autowired
	public Jt808_0100_MessageHandler(@Qualifier("downstreamMessageSendHandler") MessageHandler handler) {
		setSuccessor(handler);
	}

	@Override
	public void handle(ExchangeMessage message) throws Exception {
		Map<?, ?> map = (Map<?, ?>) message.getMessage();
		Map<?, ?> messageHeader = (Map<?, ?>) map.get(Constants.MAP_KEY_MESSAGE_HEADER);
		// Map<?, ?> messageBody = (Map<?, ?>)
		// map.get(Constants.MAP_KEY_MESSAGE_MESSAGE_BODY);

		String simCode = (String) messageHeader.get(Constants.MAP_KEY_SIM_CODE);
		// String terminalId = (String) messageBody.get("terminalId");

		Map<String, Object> respMessageBody = new HashMap<String, Object>();
		respMessageBody.put(Constants.MAP_KEY_MESSAGE_SEQ, messageHeader.get(Constants.MAP_KEY_MESSAGE_SEQ));

		// 这里默认应答成功，平台可根据终端信息做验证后，应答结果，todo
		int result = 0;

		// 这里默认鉴权码，todo
		String authCode = "e23456";//;getRandomString(6);

		respMessageBody.put("authCode", authCode);
		respMessageBody.put("result", result);

		// 响应终端
		Map<String, Object> respMessageHeader = new HashMap<String, Object>();
		respMessageHeader.put(Constants.MAP_KEY_SIM_CODE, simCode);
		respMessageHeader.put(Constants.MAP_KEY_MESSAGE_ID, "8100");
		Map<String, Object> downMessage = new HashMap<String, Object>();
		downMessage.put(Constants.MAP_KEY_MESSAGE_HEADER, respMessageHeader);
		downMessage.put(Constants.MAP_KEY_MESSAGE_MESSAGE_BODY, respMessageBody);
		// 注入下发消息处理链
		ExchangeMessage exchangeMessage = new ExchangeMessage(ExchangeMessage.MESSAGEID_PLATFORM_DOWN_MESSAGE,
				downMessage, null, message.getGatewayId());
		exchangeMessage.setExtAttribute(message.getExtAttribute());
		getSuccessor().handle(exchangeMessage);
	}

	public static String getRandomString(int length) {
		// 产生随机数
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		// 循环length次
		for (int i = 0; i < length; i++) {
			// 产生0-2个随机数，既与a-z，A-Z，0-9三种可能
			int number = random.nextInt(3);
			long result = 0;
			switch (number) {
			// 如果number产生的是数字0；
			case 0:
				// 产生A-Z的ASCII码
				result = Math.round(Math.random() * 25 + 65);
				// 将ASCII码转换成字符
				sb.append(String.valueOf((char) result));
				break;
			case 1:
				// 产生a-z的ASCII码
				result = Math.round(Math.random() * 25 + 97);
				sb.append(String.valueOf((char) result));
				break;
			case 2:
				// 产生0-9的数字
				sb.append(String.valueOf(new Random().nextInt(10)));
				break;
			}
		}
		return sb.toString();
	}
}
