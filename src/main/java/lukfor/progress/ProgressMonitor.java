package lukfor.progress;

public class ProgressMonitor {

	public static void run(ITaskWithProgress task) {
		IProgressBarRenderer renderer = new SimpleProgressBarRenderer();
		run(task, renderer);
	}

	public static void run(ITaskWithProgress task, IProgressBarStyle style) {
		IProgressBarRenderer renderer = new InteractiveProgressBarRenderer(style);
		run(task, renderer);
	}

	public static void run(ITaskWithProgress task, IProgressBarRenderer renderer) {
		ProgressBar bar = new ProgressBar();
		renderer.setProgressBar(bar);
		task.run(bar);
		renderer.finish();
	}

}
