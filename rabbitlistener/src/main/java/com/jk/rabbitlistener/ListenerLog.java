package com.jk.rabbitlistener;

import com.jk.model.LogBean;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "logqueue")

public class ListenerLog {
    @Autowired
    private MongoTemplate mongoTemplate;
    @RabbitHandler
    public void getLogMessage(LogBean logBean){
       /* mongoTemplate.save(logBean);
        System.out.println("我处理了一条消息");*/
    }
}
