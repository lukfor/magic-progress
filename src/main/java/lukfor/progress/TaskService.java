package lukfor.progress;

import java.io.PrintStream;
import java.util.List;

import lukfor.progress.renderer.IProgressIndicator;
import lukfor.progress.tasks.ITaskRunnable;
import lukfor.progress.tasks.Task;
import lukfor.progress.tasks.TaskFailureStrategy;
import lukfor.progress.util.AnsiColors;

public class TaskService {

	private static boolean defaultAnimated = true;

	private static PrintStream defaultTarget = System.out;

	private static int defaultThreads = 1;

	private static TaskFailureStrategy defaultFailureStrategy = TaskFailureStrategy.IGNORE_FAILURES;

	private TaskService() {

	}

	protected static TaskServiceBuilder getDefaultTaskServiceBuilder() {
		return new TaskServiceBuilder().animated(defaultAnimated).threads(defaultThreads).target(defaultTarget)
				.onFailure(defaultFailureStrategy);
	}

	public static List<Task> run(ITaskRunnable... runnables) {
		return getDefaultTaskServiceBuilder().run(runnables);
	}

	public static TaskServiceBuilder monitor(IProgressIndicator... components) {
		return getDefaultTaskServiceBuilder().style(components);
	}

	public static void setAnsiColors(boolean ansiColors) {
		if (ansiColors) {
			AnsiColors.enable();
		} else {
			AnsiColors.disable();
		}
	}

	public static void setAnimated(boolean defaultAnimated) {
		TaskService.defaultAnimated = defaultAnimated;
	}

	public static boolean isAnimated() {
		return TaskService.defaultAnimated;
	}

	public static void setTarget(PrintStream defaultTarget) {
		TaskService.defaultTarget = defaultTarget;
	}

	public static PrintStream getTarget() {
		return TaskService.defaultTarget;
	}

	public static void setThreads(int defaultThreads) {
		TaskService.defaultThreads = defaultThreads;
	}

	public static void setFailureStrategy(TaskFailureStrategy defaultFailureStrategy) {
		TaskService.defaultFailureStrategy = defaultFailureStrategy;
	}

	public static TaskFailureStrategy getFailureStrategy() {
		return TaskService.defaultFailureStrategy;
	}

}
