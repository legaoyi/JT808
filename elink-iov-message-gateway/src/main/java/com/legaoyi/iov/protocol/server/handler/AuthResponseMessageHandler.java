package com.legaoyi.iov.protocol.server.handler;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.MessageDeliveryException;
import org.springframework.stereotype.Component;

import com.legaoyi.iov.protocol.downstream.messagebody.Jt808_8001_MessageBody;
import com.legaoyi.iov.protocol.exception.IllegalMessageException;
import com.legaoyi.iov.protocol.message.ExchangeMessage;
import com.legaoyi.iov.protocol.message.Message;
import com.legaoyi.iov.protocol.message.MessageHeader;
import com.legaoyi.iov.protocol.server.Session;
import com.legaoyi.iov.protocol.server.SessionManager;
import com.legaoyi.iov.protocol.server.SessionState;
import com.legaoyi.iov.protocol.upstream.messagebody.Jt808_0102_MessageBody;
import com.legaoyi.iov.protocol.util.DefaultMessageBuilder;

/*
 * 
 * @author <a href="mailto:shengbo.gao@gmail.com;78772895@qq.com">www.legaoyi.com</a>
 * 
 * @version 1.0.0
 * 
 * @since 2020-06-30
 */
@Component("authResponseMessageHandler")
public class AuthResponseMessageHandler extends MessageHandler {

    @Value("${tcp.closeSession.immediately}")
    private boolean closeSession = false;

    @Autowired
    @Qualifier("downstreamMessageDeliverer")
    private DownstreamMessageDeliverer messageDeliverer;

    @Autowired
    @Qualifier("messageProducer")
    private MessageProducer messageProducer;

    @Override
    public void handle(ExchangeMessage exchangeMessage) throws Exception {
        if (ExchangeMessage.MESSAGEID_PLATFORM_AUTH_RESP_MESSAGE.equals(exchangeMessage.getMessageId())) {
            Map<?, ?> map = (Map<?, ?>) exchangeMessage.getMessage();
            String simCode = (String) map.get("simCode");
            int result = (Integer) map.get("result");
            int messageSeq = (Integer) map.get("messageSeq");

            Session session = SessionManager.getInstance().getSession(simCode);
            if (session == null) {
                throw new MessageDeliveryException("device offline,simCode=".concat(simCode));
            }

            Message message = new Message();
            MessageHeader messageHeader = new MessageHeader();
            messageHeader.setSimCode(simCode);
            messageHeader.setMessageId(Jt808_8001_MessageBody.MESSAGE_ID);
            message.setMessageHeader(messageHeader);
            Jt808_8001_MessageBody jtt8001 = new Jt808_8001_MessageBody();
            jtt8001.setMessageId(Jt808_0102_MessageBody.MESSAGE_ID);
            jtt8001.setMessageSeq(messageSeq);
            jtt8001.setResult(result);
            message.setMessageBody(jtt8001);
            messageDeliverer.deliver(session, message);

            // 鉴权成功
            if (result == 0) {
                SessionManager.getInstance().setSessionState(simCode, SessionState.AUTHENTICATED);
                // 通知平台上线
                messageProducer.send(DefaultMessageBuilder.buildOnlineMessage(message.getMessageHeader().getSimCode()).toString());
            } else {
                // 鉴权失败是否需要关闭tcp连接
                if (closeSession) {
                    SessionManager.getInstance().closeSession(session.getSimCode());
                }
            }
        } else if (getSuccessor() != null) {
            getSuccessor().handle(exchangeMessage);
        } else {
            throw new IllegalMessageException();
        }
    }
}
