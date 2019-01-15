package cn.strongme.bootssl.rocketmq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.*;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

/**
 * description:
 * email: <a href="strongwalter2014@gmail.com">阿水</a>
 *
 * @author 阿水
 * @date 2019-01-05 17:05.
 */

@Slf4j
public class MqTest {

    /**
     * 测试同步消息发送
     *
     * @throws Exception
     */
    @Test
    public void testSynchronous() throws Exception {

        String group = "strong";
        String topic = "strong-me";
        DefaultMQProducer producer = new DefaultMQProducer(group);
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.start();

        IntStream.range(0, 1000).forEach(count -> {
            Message message = null;
            try {
                message = new Message(topic, "为什么使用消息队列？消息队列有什么优点和缺点？Kafka、ActiveMQ、RabbitMQ、RocketMQ 都有什么优点和缺点？".getBytes(RemotingHelper.DEFAULT_CHARSET));
            } catch (UnsupportedEncodingException e) {
                log.error(e.getMessage(), e);
            }
            try {
                SendResult sendResult = producer.send(message);
                log.info("send result : {}", sendResult);
            } catch (MQClientException e) {
                log.error(e.getErrorMessage(), e);
            } catch (RemotingException | MQBrokerException | InterruptedException e) {
                log.error(e.getMessage(), e);
            }
        });

        producer.shutdown();

    }

    /**
     * 测试异步消息发送
     *
     * @throws MQClientException
     * @throws InterruptedException
     */
    @Test
    public void testAsynchronous() throws MQClientException, InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(100);

        String group = "weak";
        String topic = "weak-me";
        DefaultMQProducer producer = new DefaultMQProducer(group);
        producer.setNamesrvAddr("127.0.0.1:9876");

        producer.start();
        producer.setRetryTimesWhenSendAsyncFailed(0);

        IntStream.range(0, 100).forEach(count -> {
            Message message = null;
            try {
                message = new Message(topic, "2019", "0106", "Hello Rocket Mq".getBytes(RemotingHelper.DEFAULT_CHARSET));
            } catch (UnsupportedEncodingException e) {
                log.error(e.getMessage());
            }
            try {
                producer.send(message, new SendCallback() {
                    @Override
                    public void onSuccess(SendResult sendResult) {
                        log.info("msg ok index: {}, msgId: {}", count, sendResult.getMsgId());
                        countDownLatch.countDown();
                    }

                    @Override
                    public void onException(Throwable e) {
                        log.error("msg exception index: {}, error: {}", count, e.getMessage());
                        countDownLatch.countDown();
                    }
                });
            } catch (MQClientException | RemotingException | InterruptedException e) {
                log.error(e.getMessage());
            }
        });

        countDownLatch.await();

        log.info("count down finished.");

        producer.shutdown();
    }

    /**
     * 测试消费
     *
     * @throws MQClientException
     */
    @Test
    public void testConsume() throws MQClientException, InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(100);

        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("strong");

        consumer.setNamesrvAddr("127.0.0.1:9876");

        consumer.subscribe("strong-me", "*");

        consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
            for (Message message : msgs) {
                try {
                    log.info("received msg: {}  ,under thread: {}", new String(message.getBody(), RemotingHelper.DEFAULT_CHARSET), Thread.currentThread().getName());
                } catch (UnsupportedEncodingException e) {
                    log.error(e.getMessage(), e);
                }
            }
            countDownLatch.countDown();
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });

        consumer.start();

        log.info("consumer started");

        countDownLatch.await();
    }

    /**
     * 测试单向消息发送
     *
     * @throws MQClientException
     */
    @Test
    public void testOneWayMode() throws MQClientException {

        String group = "oneWay";
        String topic = "oneWay-me";

        DefaultMQProducer producer = new DefaultMQProducer(group);
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.start();

        IntStream.range(0, 100).forEach(count -> {
            Message message = null;
            try {
                message = new Message(topic, "2019", "0106", "Hello Rocket Mq In One Way Mode".getBytes(RemotingHelper.DEFAULT_CHARSET));
            } catch (UnsupportedEncodingException e) {
                log.error(e.getMessage());
            }

            try {
                producer.sendOneway(message);

            } catch (MQClientException | RemotingException | InterruptedException e) {
                log.error(e.getMessage(), e);
            }

        });

        producer.shutdown();

    }

    /**
     * 测试有序的消息生产者
     */
    @Test
    public void testOrderedProducer() throws MQClientException {

        MQProducer producer = new DefaultMQProducer("strong");

        producer.start();

        String[] tags = new String[]{"TagA", "TagB", "TagC", "TagD", "TagE", "TagF"};

        IntStream.range(0, 100).forEach(count -> {
            Integer orderId = count % 10;
            Message message = null;
            try {
                message = new Message("TopicOrder", tags[count % tags.length], "Key" + count, "Hello With Order".getBytes(RemotingHelper.DEFAULT_CHARSET));
            } catch (UnsupportedEncodingException e) {
                log.error(e.getMessage(), e);
            }

            try {
                SendResult sendResult = producer.send(message, new MessageQueueSelector() {
                    @Override
                    public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                        Integer id = (Integer) arg;
                        int index = id % mqs.size();
                        return mqs.get(index);
                    }
                }, orderId);
                log.info("send result : {}", sendResult);
            } catch (MQClientException | RemotingException | MQBrokerException | InterruptedException e) {
                log.error(e.getMessage(), e);
            }
        });

        producer.shutdown();

    }

}
