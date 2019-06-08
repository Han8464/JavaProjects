package com.springboot.chapter4.proxy;

import com.springboot.chapter4.intercept.Interceptor;
import com.springboot.chapter4.invoke.Invocation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyBean implements InvocationHandler {
    private Object target = null;

    private Interceptor interceptor = null;

    public static Object getProxyBean (Object target, Interceptor interceptor) {
        ProxyBean proxyBean = new ProxyBean();

        proxyBean.target = target;
        proxyBean.interceptor = interceptor;

        Object proxy = Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), proxyBean);

        return proxy;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        boolean exceptionFlag = false;
        Invocation invocation = new Invocation(target, method, args);
        Object retobj = null;
        try {
            if(this.interceptor.useAround()) {
                retobj = this.interceptor.around(invocation);
            }else {
                retobj = method.invoke(target, args);
            }
        }catch (Exception ex) {
            exceptionFlag = true;
        }
        this.interceptor.after();
        if(exceptionFlag) {
            this.interceptor.afterThrowing();
        }else {
            this.interceptor.afterReturning();
            return retobj;
        }
        return null;
    }

}