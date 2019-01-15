package cn.strongme.bootssl.zk;

import cn.strongme.bootssl.BootsslApplication;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;


/**
 * description:
 * email: <a href="strongwalter2014@gmail.com">阿水</a>
 *
 * @author 阿水
 * @date 2019-01-04 16:03.
 */

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootsslApplication.class)
public class TestZooKeeper {


    @Test
    public void test() throws IOException, KeeperException, InterruptedException {

        ZooKeeper zk = new ZooKeeper("localhost:2181,localhost:2182,localhost:2183", 30000, watchedEvent -> {
            log.info("triggered event type: {}", watchedEvent.getType());
        });

        // 创建一个目录节点
        zk.create("/parent", "parent".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        // 创建一个子目录节点
        zk.create("/parent/child1", "child1".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        // 取出节点数据
        log.info("节点数据：{}", new String(zk.getData("/parent", false, null)));

        // 取出子目录节点列表
        log.info("子目录列表：{}", zk.getChildren("/parent", true));

        // 修改子目录节点数据
        zk.setData("/parent/child1", "child1".getBytes(), -1);
        log.info("目录状态：{}", zk.exists("/parent", true));

        // 创建另外一个子目录节点
        zk.create("/parent/child2", "child2".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        log.info("另外一个子目录节点：{}", new String(zk.getData("/parent/child2", true, null)));

        // 删除子目录节点
        zk.delete("/parent/child1", -1);
        zk.delete("/parent/child2", -1);

        // 删除父目录节点
        zk.delete("/parent", -1);

        // 关闭连接
        zk.close();


    }


    String server = "/server";
    String hosts = "localhost:2181,localhost:2182,localhost:2183";
    ZooKeeper zk;

    @Test
    public void testPub() throws IOException, KeeperException, InterruptedException {

        ZooKeeper zk = new ZooKeeper(hosts, 30000, null);
        // 如果"/servers"不存在,则创建
        if (zk.exists(server, false) == null) {
            zk.create(server, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
        // 注册服务
        while (true) {
            if (zk.getChildren(server, true).size() == 3) {
                zk.close();
            }
            zk.create(server + "/" + "server-"+UUID.randomUUID(), "192.168.1.1:8080".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            Thread.sleep(3000);
        }

    }

    @Test
    public void testSubClient() throws IOException, KeeperException, InterruptedException {

        zk = new ZooKeeper(hosts, 30000, watchedEvent -> {
            // 监听"/servers"节点下的子节点变化事件, 更新server列表, 并重新注册监听
            if (watchedEvent.getType() == Watcher.Event.EventType.NodeChildrenChanged && (server).equals(watchedEvent.getPath())) {
                try {
                    updateServerList();
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
            }
        });

        updateServerList();
        Thread.sleep(Integer.MAX_VALUE);

    }

    private void updateServerList() throws KeeperException, InterruptedException, UnsupportedEncodingException {
        if (zk.exists(server, false) == null) {
            //server不存在，则创建
            zk.create(server, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }

        List<String> newServerList = Lists.newArrayList();

        // 获取并监听"/servers"的子节点变化
        // watch参数为true, 表示监听子节点变化事件.
        // 每次都需要重新注册监听, 因为一次注册, 只能监听一次事件, 如果还想继续保持监听, 必须重新注册
        List<String> subList = zk.getChildren(server, true);
        for (String sub : subList) {
            // 获取每个子节点下关联的server地址
            byte[] data = zk.getData(server + "/" + sub, false, null);
            newServerList.add(new String(data, StandardCharsets.UTF_8));
        }

        log.info("new server list: {}", newServerList);

    }

}
