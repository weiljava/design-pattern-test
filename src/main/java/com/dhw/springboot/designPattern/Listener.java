package com.dhw.springboot.designPattern;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class Listener implements IListener {
    @Override
    public void handle(IUserEvent event) {
        String eventType = event.getEventType();
        if (IUserEvent.Create_EVENT.equals(eventType)) {
            log.info("添加操作");
        } else if (IUserEvent.Delete_EVENT.equals(eventType)) {
            log.info("删除操作");
        } else if (IUserEvent.Update_EVENT.equals(eventType)) {
            log.info("修改操作");
        } else if (IUserEvent.Retrieve_EVENT.equals(eventType)) {
            log.info("查找操作");
        }
    }

}
