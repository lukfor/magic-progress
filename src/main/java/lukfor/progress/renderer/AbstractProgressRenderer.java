package lukfor.progress.renderer;

import java.io.PrintStream;
import java.util.List;
import java.util.Vector;

import lukfor.progress.renderer.bars.DefaultProgressBar;
import lukfor.progress.renderer.labels.DefaultLabel;
import lukfor.progress.renderer.labels.TimeLabel;
import lukfor.progress.tasks.TaskFailureStrategy;
import lukfor.progress.tasks.monitors.TaskMonitor;

public abstract class AbstractProgressRenderer implements IProgressRenderer {

	protected List<TaskMonitor> monitors = new Vector<TaskMonitor>();;

	protected PrintStream target = System.out;

	protected TaskFailureStrategy taskFailureStrategy;

	protected IProgressIndicator components[] = new IProgressIndicator[] { new TimeLabel(), new DefaultProgressBar(),
			new DefaultLabel() };

	@Override
	public void setTarget(PrintStream target) {
		this.target = target;
	}

	public PrintStream getTarget() {
		return target;
	}

	@Override
	public void setTaskFailureStrategy(TaskFailureStrategy taskFailureStrategy) {
		this.taskFailureStrategy = taskFailureStrategy;
	}

	@Override
	public void addTaskMonitor(TaskMonitor monitor) {
		monitors.add(monitor);
		monitor.setRenderer(this);
	}

	public List<TaskMonitor> getTaskMonitors() {
		return monitors;
	}

	public void setComponents(IProgressIndicator... components) {
		this.components = components;
	}

	public IProgressIndicator[] getComponents() {
		return components;
	}

	@Override
	public boolean isRunning() {

		for (TaskMonitor monitor : monitors) {
			if (!monitor.isDone()) {
				return true;
			}
		}

		return false;
	}

	@Override
	public void finish(TaskMonitor monitor) {

		if (!monitor.isSuccess() && !monitor.isCanceled()) {

			if (taskFailureStrategy == TaskFailureStrategy.CANCEL_TASKS) {
				// cancel other monitors
				for (TaskMonitor _monitor : monitors) {
					if (monitor != _monitor) {
						_monitor.setCanceled(true);
					}
				}
			}

		}
	}

}
