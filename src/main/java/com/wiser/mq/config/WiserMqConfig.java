package com.wiser.mq.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="ali.mq")
public class WiserMqConfig {

    private String namesrvAddr;

    private String groupId;

    private String accessKey;

    private String secretKey;

    private String topicId;

    private String consumeTags;

    private String callBack;

    private String eyewisdomCallBack;

    private String callBackDev;

    private String eyewisdomCallBackDev;


    public String getCallBackDev() {
        return callBackDev;
    }

    public void setCallBackDev(String callBackDev) {
        this.callBackDev = callBackDev;
    }

    public String getEyewisdomCallBackDev() {
        return eyewisdomCallBackDev;
    }

    public void setEyewisdomCallBackDev(String eyewisdomCallBackDev) {
        this.eyewisdomCallBackDev = eyewisdomCallBackDev;
    }

    public String getEyewisdomCallBack() {
        return eyewisdomCallBack;
    }

    public void setEyewisdomCallBack(String eyewisdomCallBack) {
        this.eyewisdomCallBack = eyewisdomCallBack;
    }

    public String getCallBack() {
        return callBack;
    }

    public void setCallBack(String callBack) {
        this.callBack = callBack;
    }

    public String getConsumeTags() {
        return consumeTags;
    }

    public void setConsumeTags(String consumeTags) {
        this.consumeTags = consumeTags;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public String getNamesrvAddr() {
        return namesrvAddr;
    }

    public void setNamesrvAddr(String namesrvAddr) {
        this.namesrvAddr = namesrvAddr;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }





}
