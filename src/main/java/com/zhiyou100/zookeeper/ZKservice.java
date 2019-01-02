package com.zhiyou100.zookeeper;

import org.apache.zookeeper.*;

import java.io.IOException;

/*客户端可以实时监控服务器上下线的变化
* 采用zookeeper集群，必须注册为临时节点只有这样挂掉zookeeper可以删除
* 分布式系统的服务，在客户端和服务端都需要加代码*/
public class ZKservice {
    private ZooKeeper zkClient=null;
    private static final String connectString = "10.33.180.127,10.33.180.128,10.33.180.129";
    private static final int sessionTimeout = 2000;

    private static final String parentNode="/servers";
//创建zk客户端连接
    public  void getConnetion() throws IOException {
        zkClient = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
            public void process(WatchedEvent event) {
                System.out.println(event.getType() + "---" + event.getPath());
                try {
                    zkClient.getChildren("/", true);
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public void registerServer(String hostname) throws  Exception{
        zkClient.create(parentNode+"server",hostname.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
        System.out.println();

    }

    //业务功能
    public void handleBUssing(String hostname) throws InterruptedException {
        System.out.println(hostname+"start working...");
        Thread.sleep(Long.MAX_VALUE);
    }

    public static void main(String[] args) throws IOException {
        //建立获取zk连接
        ZKservice zKservice = new ZKservice();
        zKservice.getConnetion();



    }


}
