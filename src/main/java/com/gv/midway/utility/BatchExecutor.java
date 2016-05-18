package com.gv.midway.utility;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.gv.midway.processor.restoreDevice.VerizonRestoreDevicePreProcessor;

public class BatchExecutor {
	
	Logger log = Logger.getLogger(BatchExecutor.class
			.getName());

	private volatile static BatchExecutor batchExecutor;

	public ExecutorService executorService;

	private BatchExecutor() {
		log.info("singelton created");
		setExecutor();
	};

	public static BatchExecutor getBatchExecutor() {
		if (batchExecutor == null) {
			synchronized (BatchExecutor.class) {
				if (batchExecutor == null) {
					batchExecutor = new BatchExecutor();

				}
			}
		}
		return batchExecutor;
	}

	private void setExecutor() {

		executorService = Executors.newFixedThreadPool(10);
	}
	
	
	public ExecutorService getExecutorService(){
		
		return executorService;
	}
	
	public void shutDown(){
		
	   executorService.shutdown();
		try {
            if (!executorService.awaitTermination(10, TimeUnit.SECONDS)) {
            	log.info("Executor not able to finish task in specified time.");
                List<Runnable> droppedTasks = executorService.shutdownNow();
                log.info("Executor was abruptly shut down. " + droppedTasks.size() + " tasks will not be executed.");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}

}
