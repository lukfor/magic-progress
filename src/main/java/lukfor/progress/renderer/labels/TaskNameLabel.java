package lukfor.progress.renderer.labels;

import lukfor.progress.renderer.IProgressContentProvider;
import lukfor.progress.tasks.monitors.TaskMonitor;

public class TaskNameLabel implements IProgressContentProvider {

	@Override
	public String getContent(TaskMonitor monitor) {
		return monitor.getTask();
	}

}
