package lukfor.progress;

import static lukfor.progress.Components.DEFAULT;
import static lukfor.progress.Components.LINE_BREAK;
import static lukfor.progress.Components.SPINNER;
import static lukfor.progress.Components.TASK_NAME;

import java.io.IOException;

import lukfor.progress.tasks.ITaskRunnable;
import lukfor.progress.tasks.monitors.ITaskMonitor;

public class Showcase {

	public static void main(String[] args) {

		// example: "--no-ansi" flag
		TaskService.setAnimated(true);
		TaskService.setAnsiColors(true);

		ITaskRunnable task1 = createTask(500);
		ITaskRunnable task2 = createTask(600);
		ITaskRunnable task3 = createTask(400);

		// TaskService.run(task1, task2, task3);

		TaskService.monitor(SPINNER, TASK_NAME, LINE_BREAK, DEFAULT).run(task1, task2, task3);

		// TaskService.run(tasks, ProgressBarBuilder.DEFAULT.animated(false));

		return;

		/*
		 * System.out.println("\n");
		 * 
		 * ITaskRunnable task = createTask(500);
		 * 
		 * ProgressBarBuilder builder = new ProgressBarBuilder(); builder.components(new
		 * DefaultSpinner(), new TaskNameLabel()); TaskService.run(task, builder);
		 * 
		 * 
		 * System.out.println("\n");
		 * 
		 * ITaskRunnable task0 = createTask(500); TaskService.run(task0);
		 * 
		 * System.out.println("\n");
		 * 
		 * ITaskRunnable task1 = createTaskUnknown(500); TaskService.run(task1,
		 * ProgressBarBuilder.DEFAULT);
		 * 
		 * System.out.println("\n");
		 * 
		 * ITaskRunnable task2 = createTask(500); TaskService.run(task2,
		 * ProgressBarBuilder.MODERN);
		 * 
		 * System.out.println("\n");
		 * 
		 * ITaskRunnable task3 = createTask(500); TaskService.run(task3,
		 * ProgressBarBuilder.MINIMAL);
		 * 
		 * System.out.println("\n");
		 * 
		 * TaskService.waitForAll();
		 */

	}

	public static ITaskRunnable createTask(int max) {

		return new ITaskRunnable() {

			@Override
			public void run(ITaskMonitor monitor) throws IOException {
				monitor.beginTask("Downloading Data......", max);
				for (int i = 0; i < max; i++) {
					monitor.worked(1);
					try {
						if (i == 450) {
							throw new IOException("Input file not found.");
						}
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				monitor.done();

			}
		};

	}

	public static ITaskRunnable createTaskUnknown(int max) {

		return new ITaskRunnable() {

			@Override
			public void run(ITaskMonitor monitor) {
				monitor.beginTask("Downloading Data......", -1);
				for (int i = 0; i < max; i++) {
					monitor.worked(1);
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				monitor.done();

			}
		};

	}

}
