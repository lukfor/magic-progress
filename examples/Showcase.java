
//REPOS bintry-lukfor-maven=https://dl.bintray.com/lukfor/maven
//DEPS lukfor:magic-progress:0.0.1

import lukfor.progress.*;

public class Showcase {

	public static void main(String[] args) {

		// example: "--no-ansi" flag
		ProgressMonitor.setAnsiSupport(true);
		ProgressMonitor.setTarget(System.err);

		System.out.println("\n");
		
		ITaskWithProgress task1 = createTask(500);
		ProgressMonitor.run(task1);
		
		System.out.println("\n");
		
		ITaskWithProgress task2 = createTask(500);
		ProgressMonitor.run(task2, IProgressBarStyle.MODERN);
		System.out.println("\n");
		
		ITaskWithProgress task3 = createTask(500);
		ProgressMonitor.run(task3, IProgressBarStyle.MINIMAL);
		System.out.println("\n");

	}

	public static ITaskWithProgress createTask(int max) {

		return new ITaskWithProgress() {

			@Override
			public void run(IProgressMonitor monitor) {
				monitor.beginTask("Downloading Data......", max);
				for (int i = 0; i < max; i++) {
					monitor.worked(1);
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				monitor.done();

			}
		};

	}

}