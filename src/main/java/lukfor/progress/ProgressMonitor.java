package lukfor.progress;

import lukfor.progress.styles.DefaultProgressBarStyle;

public class ProgressMonitor {

	public static void run(ITaskWithProgress task) {
		run(task, new DefaultProgressBarStyle());
	}

	public static void run(ITaskWithProgress task, IProgressBarStyle style) {
		ProgressBar bar = new ProgressBar();
		ProgressBarRenderer renderer = new ProgressBarRenderer(bar, style);
		task.run(bar);
		renderer.finish();
	}

}
