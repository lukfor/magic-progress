package lukfor.progress.tasks;

import java.io.PrintStream;
import java.util.concurrent.Callable;

import lukfor.progress.ProgressBarBuilder;
import lukfor.progress.renderer.IProgressRenderer;
import lukfor.progress.tasks.monitors.TaskMonitor;

public class Task implements Callable<TaskStatus> {

	private IProgressRenderer renderer = new ProgressBarBuilder().build();

	private ITaskRunnable task;

	private PrintStream target = System.out;

	private TaskStatus status;

	private TaskMonitor monitor;

	private Task(ITaskRunnable task) {
		this.task = task;
		this.status = new TaskStatus(task);
		renderer.setTarget(target);
		monitor = new TaskMonitor();
	}

	public static Task create(ITaskRunnable task) {
		return new Task(task);
	}

	public Task render(ProgressBarBuilder builder) {
		this.renderer = builder.build();
		return this;
	}

	public Task render(IProgressRenderer renderer) {
		this.renderer = renderer;
		this.renderer.addTaskMonitor(monitor);
		return this;
	}

	public Task target(PrintStream target) {
		this.target = target;
		return this;
	}

	public TaskStatus call() {
		status = new TaskStatus(task);
		try {
			task.run(monitor);
			status.setDone(true);
			renderer.finish();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	public TaskStatus getStatus() {
		return status;
	}

}
