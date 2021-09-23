package com.dhw.springboot.designPattern;


/**
 * 监听器接口（观察者）
 * 作用：处理消息（事件）
 */
public interface IListener {

    void handle(IUserEvent event);
}
