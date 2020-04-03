package com.wiser.mq.service.impl;

import cn.hutool.core.lang.Console;
import com.aliyun.openservices.ons.api.*;
import com.wiser.mq.config.WiserMqConfig;
import com.wiser.mq.service.SendMqService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class SendMqServiceImpl implements SendMqService {

    private Logger LOG = LoggerFactory.getLogger(SendMqServiceImpl.class);

    @Autowired
    private WiserMqConfig wiserMqConfig;


    @Override
    public SendResult sendMq(String aTag, String aJson) throws Exception {

        Properties properties  = null;

        Producer producer = null;

        Message msg = null;

        SendResult sendResult = null;

        try {

            properties = new Properties();
            properties.setProperty(PropertyKeyConst.GROUP_ID,wiserMqConfig.getGroupId());
            // AccessKey 阿里云身份验证，在阿里云服务器管理控制台创建
            properties.put(PropertyKeyConst.AccessKey,wiserMqConfig.getAccessKey());
            // SecretKey 阿里云身份验证，在阿里云服务器管理控制台创建
            properties.put(PropertyKeyConst.SecretKey, wiserMqConfig.getSecretKey());
            //设置发送超时时间，单位毫秒
            properties.setProperty(PropertyKeyConst.SendMsgTimeoutMillis, "60000");
            // 设置 TCP 接入域名，到控制台的实例基本信息中查看
            properties.put(PropertyKeyConst.NAMESRV_ADDR,
                    wiserMqConfig.getNamesrvAddr());

            producer = ONSFactory.createProducer(properties);
            // 在发送消息前，必须调用 start 方法来启动 Producer，只需调用一次即可
            producer.start();


            Console.log("Start send mq message.");

            LOG.info("Send mq message: tag is {},message content is {}",aTag,aJson);

            //只发送一条消息。
            msg = new Message(
                    // Message 所属的 Topic
                    wiserMqConfig.getTopicId(),
                    // Message Tag 可理解为 Gmail 中的标签，对消息进行再归类，方便 Consumer 指定过滤条件在 MQ 服务器过滤
                    aTag,
                    // Message Body 可以是任何二进制形式的数据， MQ 不做任何干预，
                    // 需要 Producer 与 Consumer 协商好一致的序列化和反序列化方式
                    aJson.getBytes());


            // 设置代表消息的业务关键属性，请尽可能全局唯一。
            // 以方便您在无法正常收到消息情况下，可通过阿里云服务器管理控制台查询消息并补发
            // 注意：不设置也不会影响消息正常收发
            msg.setKey("MessageTimeKey_" + System.currentTimeMillis());

            sendResult = producer.send(msg);
            // 同步发送消息，只要不抛异常就是成功
            if (sendResult != null) {

                Console.log(" Send mq message success. Topic is:{},msgId is {}",msg.getTopic(),sendResult.getMessageId());
                LOG.info(" Send mq message success. Topic is:{},msgId is {}",msg.getTopic(),sendResult.getMessageId());
            }else {

                Console.log("Send mq message return null .");
            }


        }catch (Exception ex)
        {
            ex.printStackTrace();
            LOG.error("Send mq message failed. err is:{}",ex.getMessage());
            throw new Exception(ex.getMessage());
        }

        return sendResult;
    }
}
