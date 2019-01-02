package com.zhiyou100.zookeeper;


import org.apache.zookeeper.*;
import org.junit.Before;
import org.junit.Test;


import java.util.Date;
import java.util.List;

public class ClientZookeeper {
    private static final String connectString = "10.33.180.127,10.33.180.128,10.33.180.129";
    private static final int sessionTimeout = 2000;
    ZooKeeper zkClient = null;
@Before
    public void init() throws Exception {
        zkClient = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
            public void process(WatchedEvent event) {
                System.out.println(event.getType() + "---" + event.getPath());
            }
        });
    }

    public void test() throws KeeperException, InterruptedException {
        //参数1：要创建的节点的路径；参数2：节点的数据
        //参数3：节点的权限；参数4：节点的类型
        String bodCreated = zkClient.create("/idea", "hellozk".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

@Test
    public void getChildren() throws Exception{
    List<String> children = zkClient.getChildren("/", true);
    for (String child : children) {
            System.out.println(child);
        }
    }
    @Test
    public void getData() throws KeeperException, InterruptedException {
        byte[] data = zkClient.getData("/idea", false, null);
        System.out.println(new String(data));

    }
}
