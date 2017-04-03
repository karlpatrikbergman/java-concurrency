package concurrency.executor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class Main {

	public static void main(String args[]) {

		invokeAllExample();
//		submitCallableExample();
//		submitRunnableExample();
//		executeExample();

	}

	private static void invokeAllExample() {
		int nrOfCores =  Runtime.getRuntime().availableProcessors();
		System.out.printf("nr of cores: %s\n", nrOfCores);

		ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

		Set<Callable<String>> callables = new HashSet<>();

		callables.add(() -> {
			Thread.sleep(4000);
			return "Task 1";
		});
		callables.add(() -> {
			Thread.sleep(2000);
			return "Task 2";
		});
		callables.add(() -> {
			Thread.sleep(2000);
			return "Task 3";
		});

		List<Future<String>> futures = null;
		try {
			futures = executorService.invokeAll(callables);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		for(Future<String> future : futures != null ? futures : null){
			try {
				System.out.println("future.get = " + future.get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}

		executorService.shutdown();
	}


	private static void submitCallableExample() {
		System.out.printf("%-11s %-11s %-11s\n", "Caller:", "Thread Id:", "Nr of Threads:");
		log("main");

		ExecutorService executorService = Executors.newSingleThreadExecutor();
		Future future = executorService.submit((Callable) () -> "submitCallableExample");
		executorService.shutdown();

		try {
			System.out.println(future.get());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private static void submitRunnableExample() {
		System.out.printf("%-11s %-11s %-11s\n", "Caller:", "Thread Id:", "Nr of Threads:");
		log("main");

		ExecutorService executorService = Executors.newSingleThreadExecutor();
		Future future = executorService.submit(() -> {
            long threadId = Thread.currentThread().getId();
            log("new");
        });
		executorService.shutdown();

		try {
			future.get();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void executeExample() {
		System.out.printf("%-11s %-11s %-11s\n", "Caller:", "Thread Id:", "Nr of Threads:");
		log("main");

		ExecutorService executorService = Executors.newSingleThreadExecutor();
		executorService.execute(() -> {
            long threadId = Thread.currentThread().getId();
            log("new");
        });
		executorService.shutdown();
	}

	private static synchronized void log(String thread) {
		long threadId = Thread.currentThread().getId();
		int nrOfThreads = Thread.activeCount();

		System.out.printf("%-11s %-11s %-11s\n", thread, threadId, nrOfThreads);
	}
}
