package com.wbd.eshop.kafka;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

/**
 * 消费者
 * @author jwh
 *
 */
public class KafkaConsumer implements Runnable {

	
	private ConsumerConnector cc;
	
	private String topic;
	
	
	
	public KafkaConsumer(String topic) {
	//ConsumerConnector实例化该对象
		this.cc = Consumer.createJavaConsumerConnector(createConsumerConfig());
		this.topic = topic;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void run() {
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put(topic, 1);
		  Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = 
	        		cc.createMessageStreams(map);
	        List<KafkaStream<byte[], byte[]>> streams = consumerMap.get(topic);
	        for(KafkaStream stream :streams) {
	        	System.out.println("消费者启动.....");
	        	new Thread(new KafkaMessageProcessor(stream)).start();
	        }
	        
	}
	
	public  static ConsumerConfig createConsumerConfig() {
		Properties pro = new Properties();
		pro.put("zookeeper.connect", "192.168.1.81:2181,192.168.1.82:2181,192.168.1.83:2181,192.168.1.84:2181,192.168.1.85:2181");
		pro.put("group.id", "eshop-cache");
		pro.put("zookeeper.session.timeout.ms", "40000");
	    pro.put("zookeeper.sync.time.ms", "200");
	    pro.put("auto.commit.interval.ms", "1000");		
		return new ConsumerConfig(pro);
		
	}

}
