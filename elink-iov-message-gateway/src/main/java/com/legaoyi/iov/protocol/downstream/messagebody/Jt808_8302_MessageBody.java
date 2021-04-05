package com.legaoyi.iov.protocol.downstream.messagebody;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.legaoyi.iov.protocol.message.DownstreamMessageBody;
import com.legaoyi.iov.protocol.message.MessageBody;

/*
 * 提问下发
 * 
 * @author <a href="mailto:shengbo.gao@gmail.com;78772895@qq.com">www.legaoyi.com</a>
 * 
 * @version 1.0.0
 * 
 * @since 2020-06-30
 */
@Scope("prototype")
@Component(MessageBody.MESSAGE_BODY_BEAN_PREFIX + "8302" + MessageBody.MESSAGE_BODY_BEAN_SUFFIX)
public class Jt808_8302_MessageBody extends DownstreamMessageBody {

    private static final long serialVersionUID = -1826119762827732057L;

    public static final String MESSAGE_ID = "8302";

    /** 标志 **/
    @JsonProperty("flag")
    private int flag;

    /** 问题 **/
    @JsonProperty("question")
    private String question;

    /** 候选答案列表,key/val键值对，包括答案id：answerId，答案内容：answer **/
    private List<Map<String, Object>> answerList;

    public final int getFlag() {
        return flag;
    }

    public final void setFlag(int flag) {
        this.flag = flag;
    }

    public final String getQuestion() {
        return question;
    }

    public final void setQuestion(String question) {
        this.question = question;
    }

    public final List<Map<String, Object>> getAnswerList() {
        return answerList;
    }

    public final void setAnswerList(List<Map<String, Object>> answerList) {
        this.answerList = answerList;
    }

}
