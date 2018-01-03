package com.yryz.writer.modules.id.controller;


import com.yryz.writer.modules.id.api.IdAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("id/")
public class IdController {

    private static final Logger logger = LoggerFactory.getLogger(IdController.class);
	
	@Autowired
	private IdAPI idAPI;

    @RequestMapping(value="/getCode", method = { RequestMethod.GET })
    @ResponseBody
    private Long getId(String type){
        logger.info("single request userId:{}", type);
        Long result = idAPI.getId(type);
        logger.info("selectUserById result:{}", result);
        return result;
    }


    @RequestMapping(value="/getSnowflakeId", method = { RequestMethod.GET })
    @ResponseBody
    private Object getOrderId(){
        logger.info("getOrderId request");
        Long orderId = idAPI.getSnowflakeId();
        logger.info("getOrderId request result:{}", orderId);
        return orderId;
    }
}
