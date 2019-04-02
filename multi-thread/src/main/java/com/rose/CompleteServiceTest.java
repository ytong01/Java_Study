package com.rose;

import java.util.Random;
import java.util.concurrent.*;

public class CompleteServiceTest {
public static void main(String[] args) throws InterruptedException, ExecutionException {
    ExecutorService executorService = Executors.newFixedThreadPool(10);


    CompletionService<String> completionService = new ExecutorCompletionService<String>(executorService);
    
    /**
     * 产生一个随机数，模拟不同的任务的处理时间不同
     */
    for (int i = 0; i < 10; i++) {
        completionService.submit(new Callable<String>() {
            public String call(){
                int rnt = new Random().nextInt(5);
                
                try {
                    Thread.sleep(rnt*1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.out.println("run rnt = "+rnt);
                return String.valueOf(rnt*1000);
            }
        });
    }
    
    /**
     * 获取结果时，总是先拿到队列上已经存在的对象，这样不用依次等待结果
     * 显然效率更高
     */
    for (int i = 0; i < 10; i++) {
        Future<String> future = completionService.take();
        System.out.println(future.get());
    }
    executorService.shutdown();
}
}