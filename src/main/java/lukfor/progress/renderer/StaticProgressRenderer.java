package lukfor.progress.renderer;

import lukfor.progress.tasks.monitors.TaskMonitor;
import lukfor.progress.util.AnsiColors;
import lukfor.progress.util.TimeUtil;

public class StaticProgressRenderer extends AbstractProgressRenderer {

	@Override
	public void begin(TaskMonitor monitor) {
		target.println(AnsiColors.blue("[Run]") + "   " + monitor.getTask() + "...");
	}

	@Override
	public void render() {
		System.out.println("renderer!");
	}

	@Override
	public void finish(TaskMonitor monitor) {
		if (monitor.isSuccess()) {
			target.println(AnsiColors.green("[Done]") + "  " + monitor.getTask() + ". Execution Time: "
					+ TimeUtil.format(monitor.getExecutionTime()));
		} else {
			target.println(
					AnsiColors.red("[Error]") + " " + monitor.getTask() + " failed: " + monitor.getThrowable());
		}
	}

}
