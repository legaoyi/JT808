package com.legaoyi.iov.protocol.upstream.messagebody;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.legaoyi.iov.protocol.message.MessageBody;

/*
 * 
 * @author <a href="mailto:shengbo.gao@gmail.com;78772895@qq.com">www.legaoyi.com</a>
 * 
 * @version 1.0.0
 * 
 * @since 2020-06-30
 */
@Scope("prototype")
@Component(MessageBody.MESSAGE_BODY_BEAN_PREFIX + "0500" + MessageBody.MESSAGE_BODY_BEAN_SUFFIX)
public class Jt808_0500_MessageBody extends Jt808_0201_MessageBody {

    private static final long serialVersionUID = -346300311282868829L;

    public static final String MESSAGE_ID = "0500";

}
