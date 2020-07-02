package lukfor.progress.renderer.labels;

import lukfor.progress.renderer.IProgressContentProvider;
import lukfor.progress.tasks.monitors.ITaskMonitor;
import lukfor.progress.tasks.monitors.TaskMonitor;

public class DefaultLabel implements IProgressContentProvider {

	@Override
	public String getContent(TaskMonitor monitor) {
		if (monitor.getTotal() != ITaskMonitor.UNKNOWN || monitor.getTotal() == 0) {
			return " " + monitor.getWorked() + "/" + monitor.getTotal();
		} else {
			return " " + monitor.getWorked();
		}
	}

}
