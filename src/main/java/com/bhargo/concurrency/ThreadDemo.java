package com.bhargo.concurrency;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadDemo {

    /**
     * interrupt method only sets the interrupt flag for the thread.
     * Inside the thread, you have to check the status and then clean up accordingly.
     * If the thread is sleeing ot waiting, then InterruptedException is thrown
     */
	public static void threadInterruptDemo() {
		Thread t1 = new Thread() {
			@Override
			public void run() {
				int i =0;
				while (!this.isInterrupted()){
					System.out.println("loop "+ (++i));
				}
				System.out.println("Exiting the thread");
			}
		};

		t1.start();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		t1.interrupt();
		System.out.println("interrupted from outside");
	}

	public static void countdownLatchDemo() {
		CountDownLatch countDownLatch = new CountDownLatch(2);
		new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread counting down is " + Thread.currentThread().getName());
            countDownLatch.countDown();}).start();

        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread counting down is " + Thread.currentThread().getName());
            countDownLatch.countDown();}).start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(countDownLatch.getCount());
        countDownLatch.countDown();

	}

	public static void atomicIntDemo() {
		AtomicInteger in = new AtomicInteger();
		final int value = in.get();
		System.out.println("The value is " + value);
		new Thread(() -> {
			System.out.println("The value has been set to 78 ?" + in.compareAndSet(77,78));
		}).start();
		new Thread(() -> {
			System.out.println("The value has been set to 77 ?" + in.compareAndSet(value,77));
		}).start();

		System.out.println(value + " " + in.get());

	}
	public static void executorServiceDemo(Class clazz) {
		Future<String> result = null;
		ExecutorService executorService = Executors.newFixedThreadPool(3);
		for (int i = 0; i <= 4; i++) {
			if (clazz.equals(Runnable.class))
				executorService.submit(() -> {
					System.out.println(Thread.currentThread().getName());
					try {
						Thread.sleep(1000);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				});
			//Callable if submitted, must return a result of the generic type of future object
			else {
				result = executorService.submit(() -> {
					System.out.println(Thread.currentThread().getName());
					try {
						Thread.sleep(1000);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// System.out.println(Thread.currentThread().getName());
					return Thread.currentThread().getName();
				});
			}
		}
		executorService.shutdown();
		try {
			System.out.println(">" + result.get());
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void executorServiceInvokeAllDemo() throws InterruptedException{
		Callable<String> callable = () ->{
			//System.out.println(Thread.currentThread().getName());
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//System.out.println(Thread.currentThread().getName());
			return Thread.currentThread().getName();
		};
		List<Callable<String>> callables = new ArrayList<>();
		
		for(int i =0;i<=4;i++) {
			callables.add(callable);
		}
		 ExecutorService executorService = Executors.newFixedThreadPool(3);
		 executorService.invokeAll(callables).forEach(n -> {
			 try {
				System.out.println(n.get());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 });
		 executorService.shutdown();
	}
}
