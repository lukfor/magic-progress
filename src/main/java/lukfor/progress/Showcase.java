package lukfor.progress;

public class Showcase {

	public static void main(String[] args) {

		// example: "--no-ansi" flag
		ProgressMonitor.setAnsiSupport(true);
		ProgressMonitor.setTarget(System.err);

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
		ProgressMonitor.run(task, IProgressBarStyle.DEFAULT);
		System.out.println("\n");
		ProgressMonitor.run(task, IProgressBarStyle.MODERN);
		System.out.println("\n");
		ProgressMonitor.run(task, IProgressBarStyle.MINIMAL);

	}

}
