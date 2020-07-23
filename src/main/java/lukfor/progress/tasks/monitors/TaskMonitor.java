package lukfor.progress.tasks.monitors;

import java.util.concurrent.CancellationException;

import lukfor.progress.renderer.IProgressRenderer;

public class TaskMonitor implements ITaskMonitor {

	private long worked = 0;

	private long total;

	private String task;

	private long startTime = -1;

	private long endTime = -1;

	private IProgressRenderer renderer;

	private boolean running = false;

	private boolean done = false;

	private boolean success = false;

	private boolean canceled = false;

	private Throwable throwable;

	@Override
	public void beginTask(String name, long totalWork) {
		this.task = name;
		this.total = totalWork;
		this.running = true;
		if (renderer != null) {
			renderer.begin(this);
		}
	}

	@Override
	public void beginTask(String name) {
		beginTask(name, UNKNOWN);
	}

	@Override
	public void done() {

		if (done) {
			return;
		}

		this.endTime = System.currentTimeMillis();
		this.worked = this.total;
		this.running = false;
		this.done = true;
		this.success = true;

		if (renderer != null) {
			renderer.finish(this);
		}
	}

	@Override
	public void failed(Throwable throwable) {

		if (done && !success) {
			return;
		}

		this.endTime = System.currentTimeMillis();

		this.running = false;
		this.done = true;
		this.success = false;
		this.throwable = throwable;

		if (renderer != null) {
			renderer.finish(this);
		}
	}

	@Override
	public void setTaskName(String name) {
		this.task = name;
	}

	@Override
	public void worked(long work) {
		worked += work;
	}

	public long getWorked() {
		return worked;
	}

	public long getTotal() {
		return total;
	}

	public long getExecutionTime() {
		if (isDone()) {
			return endTime - startTime;
		} else if (isRunning()) {
			return System.currentTimeMillis() - startTime;
		} else {
			return -1;
		}
	}

	public void setRenderer(IProgressRenderer renderer) {
		this.renderer = renderer;
	}

	public String getTask() {
		return task;
	}

	public boolean isRunning() {
		return running;
	}

	public boolean isDone() {
		return done;
	}

	public void start() {
		this.running = true;
		this.done = false;
		this.startTime = System.currentTimeMillis();
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

	@Override
	public boolean isCanceled() {
		return canceled;
	}

	@Override
	public void setCanceled(boolean canceled) {
		this.canceled = canceled;
		this.done = true;
		this.setThrowable(new CancellationException());
		if (isRunning()) {
			this.endTime = this.startTime;
			if (renderer != null) {
				renderer.finish(this);
			}
		} else {

		}
	}

}
