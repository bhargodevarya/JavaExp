package com.bhargo.concurrency;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadDemo {

	public static void AtomicIntDemo() {
		AtomicInteger in = new AtomicInteger();
		final int value = in.get();
		System.out.println("The value is " + value);
		new Thread(() -> {
			System.out.println("The value has been set to 78 ?" + in.compareAndSet(77,78));
		}).start();
		new Thread(() -> {
			System.out.println("The value has been set to 77 ?" + in.compareAndSet(value,77));
		}).start();

		System.out.println(value);

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
