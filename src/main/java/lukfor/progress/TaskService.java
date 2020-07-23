package lukfor.progress;

import java.io.PrintStream;
import java.util.List;

import lukfor.progress.renderer.IProgressIndicator;
import lukfor.progress.tasks.ITaskRunnable;
import lukfor.progress.tasks.Task;
import lukfor.progress.util.AnsiColors;

public class TaskService {

	private static boolean defaultAnimated = true;

	private static PrintStream defaultTarget = System.out;

	private static int defaultThreads = 1;

	private TaskService() {

	}

	protected static TaskServiceBuilder getDefaultTaskServiceBuilder() {
		return new TaskServiceBuilder().animated(defaultAnimated).threads(defaultThreads).target(defaultTarget);
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
		defaultThreads = threads;
	}

}
