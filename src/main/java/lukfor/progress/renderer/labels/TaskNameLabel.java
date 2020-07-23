package lukfor.progress.renderer.labels;

import lukfor.progress.renderer.IProgressIndicator;
import lukfor.progress.tasks.monitors.TaskMonitor;

public class TaskNameLabel implements IProgressIndicator {

	@Override
	public void render(TaskMonitor monitor, StringBuilder buffer) {
		buffer.append(monitor.getTask());
	}

}
