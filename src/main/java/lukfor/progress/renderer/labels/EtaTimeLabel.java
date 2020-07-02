package lukfor.progress.renderer.labels;

import lukfor.progress.renderer.IProgressContentProvider;
import lukfor.progress.tasks.monitors.ITaskMonitor;
import lukfor.progress.tasks.monitors.TaskMonitor;
import lukfor.progress.util.TimeUtil;

public class EtaTimeLabel implements IProgressContentProvider {

	@Override
	public String getContent(TaskMonitor monitor) {

		if (monitor.getTotal() == ITaskMonitor.UNKNOWN || monitor.getTotal() == 0 || monitor.getWorked() == 0) {

			return "ETA: unkown";

		} else {

			long eta = (monitor.getExecutionTime() * monitor.getTotal() / monitor.getWorked());

			long remaining = (eta - monitor.getExecutionTime());
			if (remaining < 0) {
				remaining = 0;
			}

			return "ETA: " + TimeUtil.format(remaining);

		}
	}

}
