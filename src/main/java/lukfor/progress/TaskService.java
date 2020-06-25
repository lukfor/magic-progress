package lukfor.progress;

import java.io.PrintStream;

import lukfor.progress.renderer.IProgressRenderer;
import lukfor.progress.tasks.ITaskRunnable;
import lukfor.progress.tasks.Task;
import lukfor.progress.tasks.TaskStatus;
import lukfor.progress.util.AnsiColors;

public class TaskService {

	private static boolean ansiSupport = true;

	private static PrintStream target = System.out;

	public static TaskStatus run(ITaskRunnable task) {
		return run(task, new ProgressBarBuilder());
	}

	public static TaskStatus run(ITaskRunnable task, ProgressBarBuilder builder) {
		return run(task, builder.build());
	}

	public static TaskStatus run(ITaskRunnable task, IProgressRenderer renderer) {
		return Task.create(task).render(renderer).target(target).run();
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
}
