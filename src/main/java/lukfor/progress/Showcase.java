package lukfor.progress;

import static lukfor.progress.Components.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import lukfor.progress.tasks.ITaskRunnable;
import lukfor.progress.tasks.TaskFailureStrategy;
import lukfor.progress.tasks.monitors.ITaskMonitor;

public class Showcase {

	public static void main(String[] args) {

		// example: "--no-ansi" flag
		// TaskService.setAnimated(false);
		TaskService.setAnsiColors(true);
		TaskService.setThreads(3);
		TaskService.setFailureStrategy(TaskFailureStrategy.CANCEL_TASKS);

		ITaskRunnable task1 = createTask("task1", 500, 100);
		ITaskRunnable task2 = createTask("task2", 600, 200);
		ITaskRunnable task3 = createTask("task3", 800, -1);

		TaskService.monitor(SPINNER, TASK_NAME,  DEFAULT).run(task1, task2, task3);

	}

	public static ITaskRunnable createTask(String name, int max, int stop) {

		return new ITaskRunnable() {

			@Override
			public void run(ITaskMonitor monitor) throws IOException {
				monitor.begin(name, max);
				for (int i = 0; i < max; i++) {

					if (monitor.isCanceled()) {
						break;
					}

					monitor.worked(1);
					try {
						if (i == stop) {
							throw new IOException("Input file not found.");
						}
						TimeUnit.MILLISECONDS.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}
		};

	}

}
