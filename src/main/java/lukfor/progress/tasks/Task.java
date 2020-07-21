package lukfor.progress.tasks;

import java.util.concurrent.Callable;

import lukfor.progress.renderer.IProgressRenderer;
import lukfor.progress.tasks.monitors.TaskMonitor;

public class Task implements Callable<TaskStatus> {

	private IProgressRenderer renderer;

	private ITaskRunnable task;

	private TaskStatus status;

	private TaskMonitor monitor;

	public Task(ITaskRunnable task, IProgressRenderer renderer) {
		this.task = task;
		this.status = new TaskStatus(task);
		this.monitor = new TaskMonitor();
		this.renderer = renderer;
		this.renderer.addTaskMonitor(monitor);
	}
	
	public TaskStatus call() {
		status = new TaskStatus(task);
		try {
			monitor.start();
			
			task.run(monitor);
			
			if (!monitor.isDone()) {
				monitor.done();
			}			
			status.setDone(true);
			status.setSuccess(true);
		} catch (Exception e) {
			if (!monitor.isDone()) {
				monitor.failed(e);
			}
			status.setDone(true);
			status.setSuccess(false);
			status.setThrowable(e);
		}
		return status;
	}
	
	
	public IProgressRenderer getRenderer() {
		return renderer;
	}

	public TaskStatus getStatus() {
		return status;
	}

}
