
//REPOS bintry-lukfor-maven=https://dl.bintray.com/lukfor/maven
//DEPS lukfor:magic-progress:0.2.0

import lukfor.progress.*;
import lukfor.progress.renderer.labels.TaskNameLabel;
import lukfor.progress.renderer.spinners.DefaultSpinner;
import lukfor.progress.tasks.ITaskRunnable;
import lukfor.progress.tasks.Task;
import lukfor.progress.tasks.monitors.ITaskMonitor;

public class Example {

	public static void main(String[] args) {

		ITaskRunnable runnable = new ITaskRunnable() {

		    @Override
		    public void run(ITaskMonitor monitor) {
		        monitor.beginTask("My heavy task", 100);
		        for (int i = 0; i < 100; i++) {
		            monitor.worked(1);
		            try {
		                Thread.sleep(10);
		            } catch (Exception e) {}
		        }

		        monitor.done();

		    }

		};

		System.out.println();

		TaskService.run(runnable);

		System.out.println();
	}

}
