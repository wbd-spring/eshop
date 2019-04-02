package com.wbd.eshop.inventory.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 请求对象内存队列， 
 * @author jwh
 *
 */
public class RequestQueue {
	
	
	//内存队列
	
	private List<ArrayBlockingQueue<Request>> queue = new ArrayList<ArrayBlockingQueue<Request>>();
	
	//标识位map
	private Map<Integer,Boolean> map = new ConcurrentHashMap<Integer,Boolean>();
	
	
	private static class Singleton{
		
		private static RequestQueue instance;
		
		
		static {
			instance = new RequestQueue();
		}
		
		
		public static RequestQueue getInstance() {
			return instance;
		}
		
		
		
	}
	
	
	
	public static RequestQueue getInstance() {
		return Singleton.getInstance();
	}
	
	/**
	 * 添加一个内存队列
	 * @param queue
	 */
	public void addQueue(ArrayBlockingQueue<Request> queue) {
		
		this.queue.add(queue);
	}
	
	
	/**
	 * 获取队列的大小
	 * @return
	 */
	public int queueSize() {
		return this.queue.size();
	}
	
	/**
	 * 获取内存队列
	 * @param index
	 * @return
	 */
	public ArrayBlockingQueue<Request> getQueue(int index) {
		
		return this.queue.get(index);
	}
	
	public Map<Integer,Boolean> getFlagMap(){
		return map;
	}

}
