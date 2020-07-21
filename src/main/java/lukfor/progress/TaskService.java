package lukfor.progress;

import java.io.PrintStream;
import java.util.List;
import java.util.Vector;

import lukfor.progress.executors.DefaultTaskExecutor;
import lukfor.progress.executors.ITaskExecutor;
import lukfor.progress.renderer.AnimatedProgressRenderer;
import lukfor.progress.renderer.IProgressRenderer;
import lukfor.progress.renderer.RendererThread;
import lukfor.progress.tasks.ITaskRunnable;
import lukfor.progress.tasks.Task;
import lukfor.progress.util.AnsiColors;

public class TaskService {

	private static boolean ansiSupport = true;

	private static PrintStream target = System.out;

	private static ITaskExecutor executor = new DefaultTaskExecutor();

	public static Task run(ITaskRunnable task) {
		return run(task, new ProgressBarBuilder());
	}
	
	public static List<Task> run(List<ITaskRunnable> tasks) {
		return run(tasks, new ProgressBarBuilder());
	}	
	
	public static Task run(ITaskRunnable task, ProgressBarBuilder builder) {
		return run(task, builder.build());
	}
	
	public static List<Task> run(List<? extends ITaskRunnable> tasks, ProgressBarBuilder builder) {
		return run(tasks, builder.build());
	}

	public static Task run(ITaskRunnable runnable, IProgressRenderer renderer) {
		Task task = Task.create(runnable).render(renderer).target(target);
		
		// start renderer thread only when animated
		if (renderer instanceof AnimatedProgressRenderer) {
			new Thread(new RendererThread(renderer)).start();
		}
		
		executor.run(task);
		return task;
	}

	public static List<Task> run(List<? extends ITaskRunnable> runnables, IProgressRenderer renderer) {
		List<Task> tasks = new Vector<Task>();
		for (ITaskRunnable runnable: runnables) {
			Task task = Task.create(runnable).render(renderer).target(target);
			tasks.add(task);
		}
		
		// start renderer thread only when animated
		if (renderer instanceof AnimatedProgressRenderer) {
			new Thread(new RendererThread(renderer)).start();
		}
				
		executor.run(tasks);
		return tasks;
	}
	
	public static void setAnsiSupport(boolean ansiSupport) {
		if (ansiSupport) {
			AnsiColors.enable();
		} else {
			AnsiColors.disable();
		}
		TaskService.ansiSupport = ansiSupport;
	}

	public static boolean isAnsiSupport() {
		return ansiSupport;
	}

	public static void setTarget(PrintStream target) {
		TaskService.target = target;
	}

	public static PrintStream getTarget() {
		return target;
	}

	public static ITaskExecutor getExecutor() {
		return executor;
	}

	public static void setExecutor(ITaskExecutor executor) {
		TaskService.executor = executor;
	}
	
}
