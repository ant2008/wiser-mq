package com.wiser.mq.service;

import com.aliyun.openservices.ons.api.SendResult;

/**
 * MQ 发送
 * @author zhangxuwen
 * @date 2020-1-15
 * @since 1.0
 */
public interface SendMqService {

    SendResult sendMq(String aTag, String aJson) throws Exception;
}
