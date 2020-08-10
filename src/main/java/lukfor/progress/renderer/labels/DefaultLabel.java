package lukfor.progress.renderer.labels;

import lukfor.progress.renderer.IProgressIndicator;
import lukfor.progress.tasks.monitors.ITaskMonitor;
import lukfor.progress.tasks.monitors.TaskMonitor;

public class DefaultLabel implements IProgressIndicator {

	@Override
	public void render(TaskMonitor monitor, StringBuilder buffer) {

		buffer.append(" ");
		buffer.append(monitor.getWorked());
		if (monitor.getTotal() != ITaskMonitor.UNKNOWN || monitor.getTotal() == 0) {
			buffer.append("/");
			buffer.append(monitor.getTotal());
		}

	}

}
