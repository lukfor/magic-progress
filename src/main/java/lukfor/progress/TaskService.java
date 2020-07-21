package lukfor.progress;

import java.io.PrintStream;
import java.util.List;
import java.util.Vector;

import lukfor.progress.executors.DefaultTaskExecutor;
import lukfor.progress.executors.ITaskExecutor;
import lukfor.progress.renderer.AbstractProgressRenderer;
import lukfor.progress.renderer.AnimatedProgressRenderer;
import lukfor.progress.renderer.IProgressContentProvider;
import lukfor.progress.renderer.IProgressRenderer;
import lukfor.progress.renderer.RendererThread;
import lukfor.progress.renderer.StaticProgressRenderer;
import lukfor.progress.renderer.bars.DefaultProgressBar;
import lukfor.progress.renderer.labels.DefaultLabel;
import lukfor.progress.renderer.labels.TimeLabel;
import lukfor.progress.tasks.ITaskRunnable;
import lukfor.progress.tasks.Task;
import lukfor.progress.util.AnsiColors;

public class TaskService {

	private static boolean defaultAnimated = true;

	private static PrintStream defaultTarget = System.out;

	private static ITaskExecutor executor = new DefaultTaskExecutor();

	private boolean animated = defaultAnimated;

	private PrintStream target = defaultTarget;

	private IProgressContentProvider[] components = new IProgressContentProvider[] { new TimeLabel(),
			new DefaultProgressBar(), new DefaultLabel() };

	private ITaskRunnable[] runnables;

	private TaskService(ITaskRunnable... runnables) {
		this.runnables = runnables;
	}

	private TaskService(List<? extends ITaskRunnable> runnables) {
		this.runnables = new ITaskRunnable[runnables.size()];
		for (int i = 0; i < runnables.size(); i++) {
			this.runnables[i] = runnables.get(i);
		}
	}

	public static TaskService runnable(ITaskRunnable... runnables) {
		return new TaskService(runnables);
	}

	public static TaskService runnable(List<? extends ITaskRunnable> runnables) {
		return new TaskService(runnables);
	}

	public TaskService animated(boolean animated) {
		this.animated = animated;
		return this;
	}

	public TaskService components(IProgressContentProvider... components) {
		this.components = components;
		return this;
	}

	public TaskService target(PrintStream target) {
		this.target = target;
		return this;
	}

	public List<Task> build() {

		AbstractProgressRenderer renderer = null;

		List<Task> tasks = new Vector<Task>();

		if (animated) {
			renderer = new AnimatedProgressRenderer();
		} else {
			renderer = new StaticProgressRenderer();
		}
		renderer.setComponents(components);
		renderer.setTarget(target);

		for (ITaskRunnable runnable : runnables) {
			Task task = new Task(runnable, renderer);
			tasks.add(task);
		}

		return tasks;
	}

	public List<Task> run() {

		List<Task> tasks = build();

		TaskService.run(tasks);

		return tasks;
	}

	public static void run(Task task) {

		IProgressRenderer renderer = task.getRenderer();

		// start renderer thread only when animated
		if (renderer instanceof AnimatedProgressRenderer) {
			new Thread(new RendererThread(renderer)).start();
		}

		executor.run(task);

	}

	public static void run(List<? extends Task> tasks) {

		IProgressRenderer renderer = tasks.get(0).getRenderer();

		// start renderer thread only when animated
		if (renderer instanceof AnimatedProgressRenderer) {
			new Thread(new RendererThread(renderer)).start();
		}

		executor.run(tasks);

	}

	public static ITaskExecutor getExecutor() {
		return executor;
	}

	public static void setExecutor(ITaskExecutor executor) {
		TaskService.executor = executor;
	}

	// default values

	public static void setAnsiColors(boolean ansiColors) {
		if (ansiColors) {
			AnsiColors.enable();
		} else {
			AnsiColors.disable();
		}
	}

	public static void setAnimated(boolean animated) {
		TaskService.defaultAnimated = animated;
	}

	public static boolean isAnimated() {
		return TaskService.defaultAnimated;
	}

	public static void setTarget(PrintStream target) {
		defaultTarget = target;
	}

	public static PrintStream getTarget() {
		return defaultTarget;
	}

	public static void setThreads(int threads) {
		getExecutor().setThreads(threads);
	}

}
