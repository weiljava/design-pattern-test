package com.dhw.springboot.designPattern;


/**
 * 定义事件类型
 */
public interface IUserEvent {


    String Create_EVENT = "create event";
    String Update_EVENT = "update event";
    String Retrieve_EVENT = "retrieve event";
    String Delete_EVENT = "delete event";
    //获取事件源对象
    IListenerable getEventSource();
    //获取事件类型
    String getEventType();
}
