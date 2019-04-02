package com.wbd.eshop.inventory.thread;

import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;

/**
 * 执行请求的工作线程
 * 
 * @author jwh
 *
 */
public class RequestProcessThread implements Callable<Boolean> {

	// 自己监控的内存队列
	private ArrayBlockingQueue<Request> queue;

	public RequestProcessThread(ArrayBlockingQueue<Request> queue) {
		this.queue = queue;
	}

	@Override
	public Boolean call() throws Exception {

		try {
			while (true) {
				Request request = queue.take();
				boolean forceRefresh = request.isForceRefresh();
				// 先做读请求的去重
				if (!forceRefresh) {

					Map<Integer, Boolean> flagMap = RequestQueue.getInstance().getFlagMap();

					// 如果是更新数据库请求,那么就将那个productId对应的标识设置为true

					if (request instanceof ProductInventoryDBUpdateRequest) {

						flagMap.put(request.getProductId(), true);

						// 如果是读取请求,那么就将那个productId对应的标识设置为false
					} else if (request instanceof ProductInventoryCacheRefreshRequest) {

						// 获取product id
						Boolean flag = flagMap.get(request.getProductId());

						// 如果flag是null
						if (flag == null) {
							flagMap.put(request.getProductId(), false);
						}

						// 如果是缓存刷新的请求，那么就判断，如果标识不为空，而且是true，就说明之前有一个这个商品的数据库更新请求
						if (flag != null && flag) {
							flagMap.put(request.getProductId(), false);
						}

						// 如果是缓存刷新的请求，而且发现标识不为空，但是标识是false
						// 说明前面已经有一个数据库更新请求+一个缓存刷新请求了，大家想一想
						if (flag != null && !flag) {
							// 对于这种读请求，直接就过滤掉，不要放到后面的内存队列里面去了
							return true;
						}

					}

				}

				System.out.println("===========日志===========: 工作线程处理请求，商品id=" + request.getProductId());
				// 执行这个request操作
				request.process();

				// 假如说，执行完了一个读请求之后，假设数据已经刷新到redis中了
				// 但是后面可能redis中的数据会因为内存满了，被自动清理掉
				// 如果说数据从redis中被自动清理掉了以后
				// 然后后面又来一个读请求，此时如果进来，发现标志位是false，就不会去执行这个刷新的操作了
				// 所以在执行完这个读请求之后，实际上这个标志位是停留在false的

			}
		} catch (Exception e) {

		}

		return true;
	}

}
