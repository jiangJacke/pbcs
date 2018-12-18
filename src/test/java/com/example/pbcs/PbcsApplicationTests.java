package com.example.pbcs;

import com.example.controller.ShujukuController;
import com.example.service.DataSource;
import com.example.service.Freemarker;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PbcsApplicationTests {
	@Autowired
	private Freemarker freemarker;

	@Autowired
	private ShujukuController shujukuController;

    @Autowired
    private MongoTemplate mongoTemplate;

    //mongo集群测试
	@Test
    public void shard(){
        System.out.println("----MongoShard Test---");
        List<ServerAddress> addresses = new ArrayList<ServerAddress>();
        ServerAddress address1 = new ServerAddress("192.168.204.39",23000);
        ServerAddress address2 = new ServerAddress("192.168.204.40",23000);
        ServerAddress address3 = new ServerAddress("192.168.204.41",23000);
        addresses.add(address1);
        addresses.add(address2);
        addresses.add(address3);
        MongoClient client = new MongoClient(addresses);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS");
        Date date = new Date();
        MongoDatabase db = client.getDatabase("testdb");
        System.out.println(db.getName());
        System.out.println("查询起始时间为 "+simpleDateFormat.format(date));
        MongoCollection<Document> dbCollection = db.getCollection("Student");
        FindIterable<Document> documents = dbCollection.find().limit(100);
        System.out.println(documents);
//        MongoCollection<Document> collection = db.getCollection("Student");
        Date date1 = new Date();
        System.out.println("查询结束时间为 "+simpleDateFormat.format(date1));
        System.out.println("----------");
    }

    //Oracle读取时间获取
    @Test
    public void OracleTime(){
        DataSource dataSource = new DataSource();
        dataSource.getOracleTime();
    }

    @Test
    public void getOracleCollection(){
	    freemarker.getOracleCollection();
    }

    /**
     * 测试知识库是否能连通
     */
    @Test
    public void shujuku(){
        shujukuController.zhishiku();
    }

}
