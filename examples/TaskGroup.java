//DEPS lukfor:magic-progress:0.0.1
//DEPS org.fusesource.jansi:jansi:1.18

import java.util.List;
import java.util.Vector;

import lukfor.progress.*;
import lukfor.progress.renderer.labels.TaskNameLabel;
import lukfor.progress.renderer.spinners.DefaultSpinner;
import lukfor.progress.tasks.ITaskRunnable;
import lukfor.progress.tasks.Task;
import lukfor.progress.tasks.monitors.ITaskMonitor;

public class TaskGroup {

	public static void main(String[] args) {

		// example: "--no-ansi" flag
		TaskService.setAnsiSupport(true);
		TaskService.setTarget(System.err);
		TaskService.getExecutor().setThreads(2);

		List<ITaskRunnable> tasks = new Vector<ITaskRunnable>();
		tasks.add(createTask(500));
		tasks.add(createTask(600));
		tasks.add(createTask(400));
		tasks.add(createTask(700));
		TaskService.run(tasks, ProgressBarBuilder.DEFAULT);

		return;

	}

	public static ITaskRunnable createTask(int max) {

		return new ITaskRunnable() {

			@Override
			public void run(ITaskMonitor monitor) {
				monitor.beginTask("Downloading Data......", max);
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
