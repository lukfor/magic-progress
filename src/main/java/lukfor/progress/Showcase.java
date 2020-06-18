package lukfor.progress;

import lukfor.progress.styles.ModernProgressBarStyle;
import lukfor.progress.styles.DefaultProgressBarStyle;
import lukfor.progress.styles.MinimalProgressBarStyle;

public class Showcase {

	public static void main(String[] args) {

		// AnsiColors.disable();

		ITaskWithProgress task = new ITaskWithProgress() {

			@Override
			public void run(IProgressMonitor monitor) {
				monitor.beginTask("Downloading Data......", 800);
				for (int i = 0; i < 800; i++) {
					monitor.worked(1);
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				monitor.done();

			}
		};

		ProgressMonitor.run(task);
		System.out.println("\n");
		ProgressMonitor.run(task, new DefaultProgressBarStyle());
		System.out.println("\n");
		ProgressMonitor.run(task, new ModernProgressBarStyle());
		System.out.println("\n");
		ProgressMonitor.run(task, new MinimalProgressBarStyle());

	}

}
