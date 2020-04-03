package com.wiser.mq.service;

import com.aliyun.openservices.ons.api.Message;

import java.util.List;

public interface ConsumeMqService {

    /**
     * 消费消息
     * @return
     * @throws Exception
     */

    void consumeMq() throws Exception;
}
