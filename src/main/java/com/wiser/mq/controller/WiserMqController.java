package com.wiser.mq.controller;

import cn.hutool.core.util.StrUtil;
import com.wiser.mq.constant.WiserConstant;
import com.wiser.mq.dto.BaseRetDTO;
import com.wiser.mq.service.ConsumeMqService;
import com.wiser.mq.service.SendMqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("WiserMQ")
@RestController
public class WiserMqController {


    @Autowired
    private SendMqService sendMqService;




    public WiserMqController() {

    }


    /**
     * 发送数据到MQ.
     *
     * @param session
     * @param request
     * @param response
     * @param aTags
     * @param aJson
     * @return
     */
    @RequestMapping(value = "ProduceTag",method = RequestMethod.POST)
    public BaseRetDTO<String> produceTagMQ(HttpSession session,
                                            HttpServletRequest request,
                                            HttpServletResponse response,
                                            @RequestParam String aTags,
                                            @RequestParam String aJson)
    {
        BaseRetDTO<String> retDTO = new BaseRetDTO<>();

        try{

            if(StrUtil.isBlank(aTags))
            {
                return retDTO.err(WiserConstant.ERROR_CODE_4001,"标签不允许为空!");
            }

            if(StrUtil.isBlank(aJson))
            {
                return retDTO.err(WiserConstant.ERROR_CODE_4002,"内容不允许为空!");
            }

            sendMqService.sendMq(aTags,aJson);

        }catch (Exception ex)
        {
            return retDTO.err(WiserConstant.ERROR_CODE,ex.getMessage());
        }

        return retDTO.ok(WiserConstant.SUCCESS_CODE,WiserConstant.SUCCESS);
    }

}
