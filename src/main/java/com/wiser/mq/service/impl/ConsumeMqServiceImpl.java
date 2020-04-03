package com.wiser.mq.service.impl;

import cn.hutool.core.lang.Console;
import cn.hutool.http.HttpUtil;
import com.aliyun.openservices.ons.api.*;
import com.google.gson.Gson;
import com.wiser.mq.config.WiserMqConfig;
import com.wiser.mq.dto.WiserMqMessage;
import com.wiser.mq.service.ConsumeMqService;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Service
public class ConsumeMqServiceImpl implements ConsumeMqService {


    private Logger LOG = LoggerFactory.getLogger(ConsumeMqServiceImpl.class);

    @Autowired
    private WiserMqConfig wiserMqConfig;

    /**
     * 消费消息
     * @return
     * @throws Exception
     */
    @Override
    public void consumeMq() throws Exception {
        Properties properties = null;
        Consumer consumer = null;

        try
        {

            properties = new Properties();
            // 您在控制台创建的 Group ID
            properties.put(PropertyKeyConst.GROUP_ID, wiserMqConfig.getGroupId());
            // AccessKey 阿里云身份验证，在阿里云服务器管理控制台创建
            properties.put(PropertyKeyConst.AccessKey, wiserMqConfig.getAccessKey());
            // SecretKey 阿里云身份验证，在阿里云服务器管理控制台创建
            properties.put(PropertyKeyConst.SecretKey, wiserMqConfig.getSecretKey());
            // 设置 TCP 接入域名，到控制台的实例基本信息中查看
            properties.put(PropertyKeyConst.NAMESRV_ADDR,
                    wiserMqConfig.getNamesrvAddr());
            // 集群订阅方式 (默认)
            // properties.put(PropertyKeyConst.MessageModel, PropertyValueConst.CLUSTERING);
            // 广播订阅方式
            // properties.put(PropertyKeyConst.MessageModel, PropertyValueConst.BROADCASTING);
            consumer = ONSFactory.createConsumer(properties);
            consumer.subscribe(wiserMqConfig.getTopicId(),
                    wiserMqConfig.getConsumeTags(),
                    new MessageListener() {
                //订阅多个 Tag
                @Override
                public Action consume(Message message, ConsumeContext context) {


                    if(message != null)
                    {
                        LOG.info("Receive message. message tag is: {},message body is: {},message id:{}",
                                message.getTag(),message.getBody(),message.getMsgID());

                        LOG.debug("Send message to callback: url is {}",wiserMqConfig.getCallBack());

                        Map<String,Object> paramMap = new HashMap<>();

                        WiserMqMessage wiserMqMessage = new WiserMqMessage();

                        wiserMqMessage.setMessageId(message.getMsgID());
                        wiserMqMessage.setMessageBody(new String(message.getBody()));
                        wiserMqMessage.setMessageKey(message.getKey());
                        wiserMqMessage.setMessgeTag(message.getTag());

                        Gson gson = new Gson();

                        String tmpJson = gson.toJson(wiserMqMessage);

                        paramMap.put("aTag",message.getTag());
                        paramMap.put("aJson",tmpJson);

                        String retStr =  HttpUtil.post(wiserMqConfig.getCallBack(),paramMap);

                        if(retStr != null)
                        {
                             LOG.debug("Send callback message return : {}",retStr);
                        }else
                        {
                            LOG.debug("Send call return null ");
                        }


                    }else
                    {
                        LOG.info("Receive message is null");
                    }

                    return Action.CommitMessage;
                }
            });
            consumer.start();

            Console.log("Consume mq start.");
            LOG.info("Consume mq start.");

        }catch (Exception ex)
        {
            throw new Exception(ex.getMessage());
        }
    }
}
