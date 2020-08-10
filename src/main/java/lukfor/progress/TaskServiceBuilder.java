package lukfor.progress;

import java.io.PrintStream;
import java.util.List;
import java.util.Vector;

import lukfor.progress.executors.DefaultTaskExecutor;
import lukfor.progress.executors.ITaskExecutor;
import lukfor.progress.renderer.AbstractProgressRenderer;
import lukfor.progress.renderer.AnimatedProgressRenderer;
import lukfor.progress.renderer.IProgressIndicator;
import lukfor.progress.renderer.RendererThread;
import lukfor.progress.renderer.StaticProgressRenderer;
import lukfor.progress.tasks.ITaskRunnable;
import lukfor.progress.tasks.Task;
import lukfor.progress.tasks.TaskFailureStrategy;

public class TaskServiceBuilder {

	private boolean animated = true;

	private PrintStream target = System.out;

	private IProgressIndicator[] components = null;

	private ITaskExecutor executor = new DefaultTaskExecutor();

	private TaskFailureStrategy taskFailureStrategy;

	public TaskServiceBuilder() {

	}

	public TaskServiceBuilder style(IProgressIndicator... components) {
		this.components = components;
		return this;
	}

	public TaskServiceBuilder animated(boolean animated) {
		this.animated = animated;
		return this;
	}

	public TaskServiceBuilder target(PrintStream target) {
		this.target = target;
		return this;
	}

	public TaskServiceBuilder executor(ITaskExecutor executor) {
		this.executor = executor;
		return this;
	}

	public TaskServiceBuilder threads(int threads) {
		this.executor.setThreads(threads);
		return this;
	}

	public TaskServiceBuilder onFailure(TaskFailureStrategy taskFailureStrategy) {
		this.taskFailureStrategy = taskFailureStrategy;
		return this;
	}

	public List<Task> run(List<? extends ITaskRunnable> tasks) {
		ITaskRunnable[] runnables = new ITaskRunnable[tasks.size()];
		for (int i = 0; i < tasks.size(); i++) {
			runnables[i] = tasks.get(i);
		}
		return run(runnables);
	}

	public List<Task> run(ITaskRunnable... runnables) {

		AbstractProgressRenderer renderer = null;

		if (animated) {
			renderer = new AnimatedProgressRenderer();
		} else {
			renderer = new StaticProgressRenderer();
		}

		if (components != null) {
			renderer.setComponents(components);
		}

		renderer.setTarget(target);
		renderer.setTaskFailureStrategy(taskFailureStrategy);

		List<Task> tasks = new Vector<Task>();
		for (ITaskRunnable runnable : runnables) {
			Task task = new Task(runnable, renderer);
			tasks.add(task);
		}

		// start renderer thread only when animated
		if (renderer instanceof AnimatedProgressRenderer) {
			new Thread(new RendererThread(renderer)).start();
		}

		executor.run(tasks);

		return tasks;
	}

}
