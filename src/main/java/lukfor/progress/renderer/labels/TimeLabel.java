package lukfor.progress.renderer.labels;

import lukfor.progress.renderer.IProgressContentProvider;
import lukfor.progress.tasks.monitors.TaskMonitor;
import lukfor.progress.util.TimeUtil;

public class TimeLabel implements IProgressContentProvider {

	@Override
	public String getContent(TaskMonitor monitor) {
		return "[" + TimeUtil.format(monitor.getExecutionTime()) + "]";
	}

}
