package lukfor.progress.tasks.monitors;

import lukfor.progress.renderer.IProgressRenderer;

public class TaskMonitor implements ITaskMonitor {

	private long worked = 0;

	private long total;

	private String task;

	private long startTime = -1;

	private long endTime = -1;

	private IProgressRenderer renderer;

	private boolean running = false;

	@Override
	public void beginTask(String name, long totalWork) {
		this.task = name;
		this.total = totalWork;
		this.running = true;
		if (renderer != null) {
			renderer.begin();
		}
	}

	@Override
	public void beginTask(String name) {
		this.task = name;
		this.total = UNKNOWN;
		this.running = true;
		if (renderer != null) {
			renderer.begin();
		}
	}

	@Override
	public void done() {
		this.endTime = System.currentTimeMillis();
		this.worked = this.total;
	}

	@Override
	public void setTaskName(String name) {
		this.task = name;
	}

	@Override
	public void worked(long work) {
		worked += work;
		if (renderer != null) {
			renderer.render(false);
		}
		if (worked >= total) {
			this.endTime = System.currentTimeMillis();
		}
	}

	public long getWorked() {
		return worked;
	}

	public long getTotal() {
		return total;
	}

	public long getExecutionTime() {
		if (startTime > 0) {
			if (endTime > 0) {
				return endTime - startTime;
			} else {
				return System.currentTimeMillis() - startTime;
			}
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
	
	public void start() {
		this.running = true;
		this.startTime = System.currentTimeMillis();
		if (renderer != null) {
			renderer.render(true);
		}
	}

}
