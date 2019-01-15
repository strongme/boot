package cn.strongme.bootssl.dp;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * description:
 * email: <a href="strongwalter2014@gmail.com">阿水</a>
 *
 * @author 阿水
 * @date 2019-01-04 09:14.
 */

@Slf4j
public class DynamicProxier implements InvocationHandler {

    private Object proxied;

    public DynamicProxier(Object proxied) {
        this.proxied = proxied;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.debug("proxied class: {}", proxy.getClass());
        log.debug("method : {}", method.getName());
        log.debug("args: {}", Arrays.toString(args));
        Object invokeObject = method.invoke(proxied, args);
        if (invokeObject != null) {
            log.debug("invoke object: {}", invokeObject.getClass());
        }else {
            log.debug("invoke object is null");
        }
        return invokeObject;
    }

}
