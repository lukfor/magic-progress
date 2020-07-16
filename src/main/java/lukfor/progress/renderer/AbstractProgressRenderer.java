package lukfor.progress.renderer;

import java.io.PrintStream;
import java.util.List;
import java.util.Vector;

import lukfor.progress.renderer.bars.DefaultProgressBar;
import lukfor.progress.renderer.labels.DefaultLabel;
import lukfor.progress.renderer.labels.TimeLabel;
import lukfor.progress.tasks.monitors.TaskMonitor;

public abstract class AbstractProgressRenderer implements IProgressRenderer {

	protected List<TaskMonitor> monitors = new Vector<TaskMonitor>();;

	protected PrintStream target = System.out;

	protected IProgressContentProvider components[] = new IProgressContentProvider[] { new TimeLabel(),
			new DefaultProgressBar(), new DefaultLabel() };

	@Override
	public void setTarget(PrintStream target) {
		this.target = target;
	}

	public PrintStream getTarget() {
		return target;
	}

	@Override
	public void addTaskMonitor(TaskMonitor monitor) {
		monitors.add(monitor);
	}

	public List<TaskMonitor> getTaskMonitors() {
		return monitors;
	}

	public void setComponents(IProgressContentProvider... components) {
		this.components = components;
	}

	public IProgressContentProvider[] getComponents() {
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

}
