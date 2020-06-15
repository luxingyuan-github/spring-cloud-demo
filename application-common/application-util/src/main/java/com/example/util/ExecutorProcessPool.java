package com.example.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/***
 *  
 * @ClassName
 * @Description:简单线程池操作
 * @author LXY
 * @date 2019/3/12 5:21 PM
 * @param 
 * @return 
 */
public class ExecutorProcessPool {
    private ExecutorService executor;
    private static ExecutorProcessPool pool = new ExecutorProcessPool();

    /**
     *
     * @Title: createFixedThreadPool
     * @Description: 创建一个固定数的线程池
     * @param @param threadMax
     * @param @return
     * @return ExecutorService
     * @throws
     */
    public ExecutorService createFixedThreadPool(int threadMax) {
        executor = Executors.newFixedThreadPool(threadMax);
        return executor;
    }
    /**
     *
     * @Title: newSingleThreadPool
     * @Description: 创建一个单线程的线程池
     * @param @return
     * @return ExecutorService
     * @throws
     */
    public ExecutorService newSingleThreadPool() {
        executor = Executors.newSingleThreadExecutor();
        return executor;
    }

    /**
     *
     * @Title: getInstance
     * @Description: 线程池实例
     * @param @return
     * @return ExecutorProcessPool
     * @throws
     */
    public static ExecutorProcessPool getInstance() {
        if(pool == null)  pool = new ExecutorProcessPool();
        return pool;
    }


    /**
     *
     * @Title: execute
     * @Description: 提交线程任务
     * @param @param task
     * @return void
     * @throws
     */
    public void execute(Runnable task){
        executor.execute(task);
    }

    /**
     *
     * @Title: shutdown
     * @Description: 关闭线程池
     * @param
     * @return void
     * @throws
     */
    public void shutdown(){
        executor.shutdown();
        pool = null;
    }

}
