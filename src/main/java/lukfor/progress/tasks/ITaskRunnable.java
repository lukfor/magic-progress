package lukfor.progress.tasks;

import lukfor.progress.tasks.monitors.ITaskMonitor;

public interface ITaskRunnable {

	public void run(ITaskMonitor monitor);

}
