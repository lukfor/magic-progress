package lukfor.progress;

import java.io.PrintStream;

import lukfor.progress.util.AnsiColors;

public class ProgressMonitor {

	private static boolean ansiSupport = true;

	private static PrintStream target = System.out;

	public static void run(ITaskWithProgress task) {
		IProgressBarRenderer renderer = new SimpleProgressBarRenderer();
		run(task, renderer);
	}

	public static void run(ITaskWithProgress task, IProgressBarStyle style) {
		IProgressBarRenderer renderer = new InteractiveProgressBarRenderer(style);
		run(task, renderer);
	}

	public static void run(ITaskWithProgress task, IProgressBarRenderer renderer) {
		renderer.setTarget(target);
		ProgressBar bar = new ProgressBar();
		renderer.setProgressBar(bar);
		task.run(bar);
		renderer.finish();
	}

	public static void setAnsiSupport(boolean ansiSupport) {
		ProgressMonitor.ansiSupport = ansiSupport;
		if (ansiSupport) {
			AnsiColors.enable();
		} else {
			AnsiColors.disable();
		}
	}

	public static boolean isAnsiSupport() {
		return ansiSupport;
	}

	public static void setTarget(PrintStream target) {
		ProgressMonitor.target = target;
	}

	public static PrintStream getTarget() {
		return target;
	}

}
