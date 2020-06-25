package lukfor.progress.tasks;

import java.io.PrintStream;

import lukfor.progress.ProgressBarBuilder;
import lukfor.progress.renderer.IProgressRenderer;
import lukfor.progress.tasks.monitors.TaskMonitor;

public class Task {

	private IProgressRenderer renderer = new ProgressBarBuilder().build();

	private ITaskRunnable task;

	private PrintStream target = System.out;

	private Task(ITaskRunnable task) {
		this.task = task;
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
		return this;
	}

	public Task target(PrintStream target) {
		this.target = target;
		return this;
	}

	public TaskStatus run() {
		renderer.setTarget(target);
		TaskMonitor monitor = new TaskMonitor();
		renderer.setTaskMonitor(monitor);
		task.run(monitor);
		renderer.finish();
		return new TaskStatus(task);
	}

}
