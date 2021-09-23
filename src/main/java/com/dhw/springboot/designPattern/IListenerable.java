package com.dhw.springboot.designPattern;


/**
 * 事件源接口
 */
public interface IListenerable {

    //为事件源注册监听器
    void setListener(IListener listener);

    //触发监听器
    void triggerListener(IUserEvent event);
}
