package lukfor.progress.tasks;

public class TaskStatus {

	private ITaskRunnable runnable;

	private boolean done = false;

	public TaskStatus(ITaskRunnable runnable) {
		this.runnable = runnable;
	}

	public ITaskRunnable getRunnable() {
		return runnable;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

}
