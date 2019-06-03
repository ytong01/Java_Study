package cn.cloudwalk.learn03;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.RateLimiter;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RatelimiterTest {

    public static void main(String[] args) {

        RateLimiter limiter = RateLimiter.create(0.5);

        List<Runnable> tasks = Lists.newArrayList();
        for (int i = 0; i < 10; i++) {
            tasks.add(new UserRequest(i));
        }
        ExecutorService es = Executors.newFixedThreadPool(1);
        for (Runnable runnable : tasks) {
            System.out.println("等待时间：" + limiter.acquire());
            es.submit(runnable);
        }
    }

    private static class UserRequest implements Runnable {
        private int id;

        public UserRequest(int id) {
            this.id = id;
        }

        public void run() {
            System.out.println(id);
        }
    }
}
