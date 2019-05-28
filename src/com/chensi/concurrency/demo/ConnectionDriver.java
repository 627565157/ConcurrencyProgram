package com.chensi.concurrency.demo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.concurrent.TimeUnit;

/**
 * @Author: chensi
 * @Date: 2019/5/28 22:39
 * @Version 1.0
 */
public class ConnectionDriver {

    /**
     * Connection是一个接口，最终的实现由数据库驱动提供方来实现的。
     * 我们通过一个动态代理构造一个Connection，仅仅只是在调用commit方法时休眠100ms
     */
    static class ConnectionHandler implements InvocationHandler {
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if(method.getName().equals("commit")) {
                TimeUnit.MILLISECONDS.sleep(100);

            }
            return null;
        }
    }

    public static final Connection createConnection() {
        return (Connection) Proxy.newProxyInstance(ConnectionDriver.class.getClassLoader(),
                new Class<?>[]{Connection.class}, new ConnectionHandler());
    }

}
