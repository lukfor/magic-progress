package lukfor.progress.renderer.labels;

import lukfor.progress.renderer.IProgressIndicator;
import lukfor.progress.tasks.monitors.TaskMonitor;
import lukfor.progress.util.TimeUtil;

public class TimeLabel implements IProgressIndicator {

	@Override
	public void render(TaskMonitor monitor, StringBuilder buffer) {
		buffer.append("[");
		buffer.append(TimeUtil.format(monitor.getExecutionTime()));
		buffer.append("]");
	}

}
