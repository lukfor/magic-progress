package lukfor.progress.executors;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import lukfor.progress.tasks.Task;
import lukfor.progress.tasks.TaskStatus;

public class DefaultTaskExecutor implements ITaskExecutor {

	public static final int DEFAULT_THREADS = 1;

	private int threads = DEFAULT_THREADS;

	private ExecutorService executor;

	public DefaultTaskExecutor() {
		this(DEFAULT_THREADS);
	}

	public DefaultTaskExecutor(int threads) {
		this.threads = threads;
	}

	public void setThreads(int threads) {
		this.threads = threads;
	}

	public int getThreads() {
		return threads;
	}

	public void waitForAll() {

	}

	public void run(Task... tasks) {
		List<Task> taskList = new Vector<Task>();
		for (Task task : tasks) {
			taskList.add(task);
		}
		run(taskList);
	}

	@Override
	public void run(List<Task> tasks) {
		executor = Executors.newFixedThreadPool(threads);
		List<Future<TaskStatus>> results  = null;
		try {
			results = executor.invokeAll(tasks);
		} catch (InterruptedException e) {
			System.out.println("OKOK");
			e.printStackTrace();
		}
		
		executor.shutdown();
	}

}
