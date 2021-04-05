package com.legaoyi.iov.protocol.downstream.messagebody;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.legaoyi.iov.protocol.message.DownstreamMessageBody;
import com.legaoyi.iov.protocol.message.MessageBody;

/*
 * 终端控制
 * 
 * @author <a href="mailto:shengbo.gao@gmail.com;78772895@qq.com">www.legaoyi.com</a>
 * 
 * @version 1.0.0
 * 
 * @since 2020-06-30
 */
@Scope("prototype")
@Component(MessageBody.MESSAGE_BODY_BEAN_PREFIX + "8105" + MessageBody.MESSAGE_BODY_BEAN_SUFFIX)
public class Jt808_8105_MessageBody extends DownstreamMessageBody {

    private static final long serialVersionUID = -1094563207168357813L;

    public static final String MESSAGE_ID = "8105";

    /** 命令字 **/
    @JsonProperty("commandWord")
    private int commandWord;

    /** 命令参数，值参考协议定义，如无线升级：";CMNET;;;192.168.1.103;6000;;;1.03;1.00;3600" **/
    @JsonProperty("commandParam")
    private String commandParam;

    public final int getCommandWord() {
        return commandWord;
    }

    public final void setCommandWord(int commandWord) {
        this.commandWord = commandWord;
    }

    public final String getCommandParam() {
        return commandParam;
    }

    public final void setCommandParam(String commandParam) {
        this.commandParam = commandParam;
    }

}
