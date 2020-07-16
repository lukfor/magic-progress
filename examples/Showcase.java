
//REPOS bintry-lukfor-maven=https://dl.bintray.com/lukfor/maven
//DEPS lukfor:magic-progress:0.0.1

import lukfor.progress.*;
import lukfor.progress.renderer.labels.TaskNameLabel;
import lukfor.progress.renderer.spinners.DefaultSpinner;
import lukfor.progress.tasks.ITaskRunnable;
import lukfor.progress.tasks.Task;
import lukfor.progress.tasks.monitors.ITaskMonitor;

public class Showcase {

	public static void main(String[] args) {

		// example: "--no-ansi" flag
		TaskService.setAnsiSupport(true);
		TaskService.setTarget(System.err);

		System.out.println();

		ITaskRunnable task = createTask(500);
		ProgressBarBuilder builder = new ProgressBarBuilder();
		builder.components(new DefaultSpinner(), new TaskNameLabel());
		TaskService.run(task, builder);

		ITaskRunnable task0 = createTask(500);
		TaskService.run(task0);

		ITaskRunnable task1 = createTaskUnknown(500);
		TaskService.run(task1, ProgressBarBuilder.DEFAULT);

		ITaskRunnable task2 = createTask(500);
		TaskService.run(task2, ProgressBarBuilder.MODERN);

		ITaskRunnable task3 = createTask(500);
		TaskService.run(task3, ProgressBarBuilder.MINIMAL);
		
		System.out.println();
	}

	public static ITaskRunnable createTask(int max) {

		return new ITaskRunnable() {

			@Override
			public void run(ITaskMonitor monitor) {
				monitor.beginTask("Perform heavy task", max);
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

	public static ITaskRunnable createTaskUnknown(int max) {

		return new ITaskRunnable() {

			@Override
			public void run(ITaskMonitor monitor) {
				monitor.beginTask("Perform heavy task", -1);
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
