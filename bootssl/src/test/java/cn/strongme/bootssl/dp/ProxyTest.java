package cn.strongme.bootssl.dp;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * description:
 * email: <a href="strongwalter2014@gmail.com">阿水</a>
 *
 * @author 阿水
 * @date 2019-01-04 09:19.
 */

@Slf4j
public class ProxyTest {

    private void consume(OriginWorker worker) {
        log.info("My Name: {}", worker.getMyName("Jelly"));
    }

    @Test
    public void test() {

        log.info("origin");
        consume(new DefaultWorkerImpl());
        log.info("proxied");
        consume(new WorkerProxy(new DefaultWorkerImpl()));

        log.info("------ 动态代理");
        ClassLoader classLoader = OriginWorker.class.getClassLoader();
        Class[] interfaces = new Class[]{OriginWorker.class};
        InvocationHandler invocationHandler = new DynamicProxier(new DefaultWorkerImpl());
        OriginWorker proxy = (OriginWorker) Proxy.newProxyInstance(classLoader, interfaces, invocationHandler);
        consume(proxy);

    }


}
