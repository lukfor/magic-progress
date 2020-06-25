package lukfor.progress.renderer;

import java.io.PrintStream;

import lukfor.progress.renderer.bars.DefaultProgressBar;
import lukfor.progress.renderer.labels.DefaultLabel;
import lukfor.progress.renderer.labels.TimeLabel;
import lukfor.progress.tasks.monitors.TaskMonitor;

public abstract class AbstractProgressRenderer implements IProgressRenderer {

	protected TaskMonitor monitor;

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
	public void setTaskMonitor(TaskMonitor monitor) {
		this.monitor = monitor;
		monitor.setRenderer(this);
	}

	public TaskMonitor getTaskMonitor() {
		return monitor;
	}

	public void setComponents(IProgressContentProvider... components) {
		this.components = components;
	}

	public IProgressContentProvider[] getComponents() {
		return components;
	}

}
