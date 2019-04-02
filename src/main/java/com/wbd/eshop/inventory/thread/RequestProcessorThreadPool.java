package com.wbd.eshop.inventory.thread;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 请求线程池，单列
 * @author jwh
 *
 */
public class RequestProcessorThreadPool {
	
	
	//线程池，线程池大小为10
	private ExecutorService threadPool = Executors.newFixedThreadPool(10);

	
	public RequestProcessorThreadPool() {
		
		//请求对象， 请求对象中包含，内存队列（列表）， concurrenthashmap,
		RequestQueue reQueue =  RequestQueue.getInstance();
		//创建十个内存队列元素  ArrayBlockingQueue
		for(int i=0;i<10;i++) {
			//每个内存队列中放入100个元素
			ArrayBlockingQueue<Request> queue = new ArrayBlockingQueue<Request>(100);
			reQueue.addQueue(queue);
			threadPool.submit(new RequestProcessThread(queue));
			
		}
	}
	
	private static class Singleton{
		
		private static RequestProcessorThreadPool instance;
		
		
		static {
			
			instance = new RequestProcessorThreadPool();
		}
		
		
		public static RequestProcessorThreadPool getInstance() {
			return instance;
		}
		
	}
	
	
	public static RequestProcessorThreadPool getInstance() {
		return Singleton.instance;
	}
	
	//初始化方法
	
	public static void init() {
		getInstance();
	}
	
}
