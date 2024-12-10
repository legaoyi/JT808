package com.legaoyi.iov.message.processor.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.legaoyi.common.util.JsonUtil;
import com.legaoyi.iov.message.processor.service.MessageService;

@RestController("messageController")
@RequestMapping(produces = {"application/json"})
public class MessageController extends BaseController {

    @Autowired
    @Qualifier("messageService")
    private MessageService messageService;

    @PostMapping(value = "/command/{deviceSn}/{messageId}")
    public Result sendMessage(@PathVariable String deviceSn, @PathVariable String messageId, @RequestBody Map<String, Object> messageBody) throws Exception {
        logger.info("******command->{},deviceSn={},messageBody={}", messageId, deviceSn, JsonUtil.covertObjectToString(messageBody));
        return new Result(this.messageService.send(deviceSn, messageId, messageBody));
    }

    /**
     * 开启实时视频
     *
     * @param thingId
     * @param type
     * @param messageBody
     * @return Result
     * @throws Exception
     */
    @PostMapping(value = "/command/{deviceSn}/9101")
    public Result startLive(@PathVariable String deviceSn, @RequestBody Map<String, Object> messageBody) throws Exception {
        logger.info("******command->9101,deviceSn={},messageBody={}", deviceSn, JsonUtil.covertObjectToString(messageBody));
        return new Result(this.messageService.startLive(deviceSn, messageBody));
    }

    /**
     * 实时视频控制指令
     *
     * @param deviceSn
     * @param messageBody
     * @return Result
     * @throws Exception
     */
    @PostMapping(value = "/command/{deviceSn}/9102")
    public Result stopLive(@PathVariable String deviceSn, @RequestBody Map<String, Object> messageBody) throws Exception {
        logger.info("******command->9102,deviceSn={},messageBody={}", deviceSn, JsonUtil.covertObjectToString(messageBody));
        return new Result(this.messageService.stopLive(deviceSn, messageBody));

    }
}
