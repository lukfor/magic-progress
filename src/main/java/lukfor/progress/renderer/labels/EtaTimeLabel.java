package lukfor.progress.renderer.labels;

import lukfor.progress.renderer.IProgressIndicator;
import lukfor.progress.tasks.monitors.ITaskMonitor;
import lukfor.progress.tasks.monitors.TaskMonitor;
import lukfor.progress.util.TimeUtil;

public class EtaTimeLabel implements IProgressIndicator {

	@Override
	public void render(TaskMonitor monitor, StringBuilder buffer) {

		if (monitor.getTotal() == ITaskMonitor.UNKNOWN || monitor.getTotal() == 0 || monitor.getWorked() == 0) {

			buffer.append("ETA: unkown");

		} else {

			long eta = (monitor.getExecutionTime() * monitor.getTotal() / monitor.getWorked());

			long remaining = (eta - monitor.getExecutionTime());
			if (remaining < 0) {
				remaining = 0;
			}

			buffer.append("ETA: ");
			buffer.append(TimeUtil.format(remaining));

		}

	}

}
