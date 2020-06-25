package lukfor.progress.tasks;

public class TaskStatus {

	private ITaskRunnable runnable;

	public TaskStatus(ITaskRunnable runnable) {
		this.runnable = runnable;
	}

	public ITaskRunnable getRunnable() {
		return runnable;
	}

}
