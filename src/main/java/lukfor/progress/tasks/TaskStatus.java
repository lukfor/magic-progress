package lukfor.progress.tasks;

public class TaskStatus {

	private ITaskRunnable runnable;

	private boolean done = false;

	private boolean success = false;

	private Throwable throwable;

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

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setThrowable(Throwable throwable) {
		this.throwable = throwable;
	}

	public Throwable getThrowable() {
		return throwable;
	}

}
