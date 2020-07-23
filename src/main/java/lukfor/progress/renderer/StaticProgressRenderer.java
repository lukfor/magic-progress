package lukfor.progress.renderer;

import lukfor.progress.tasks.monitors.TaskMonitor;
import lukfor.progress.util.AnsiColors;
import lukfor.progress.util.TimeUtil;

public class StaticProgressRenderer extends AbstractProgressRenderer {

	@Override
	public void begin(TaskMonitor monitor) {
		target.println(AnsiColors.blue("[Run]") + "     " + monitor.getTask() + "...");
	}

	@Override
	public void render() {
		System.out.println("renderer!");
	}

	@Override
	public void finish(TaskMonitor monitor) {
		super.finish(monitor);
		if (monitor.isSuccess()) {
			target.println(AnsiColors.green("[Done]") + "    " + monitor.getTask() + ". Execution Time: "
					+ TimeUtil.format(monitor.getExecutionTime()));
		} else 	if (monitor.isCanceled()) {
			target.println(
					AnsiColors.red("[Canceld]") + " " + monitor.getTask() + " failed: " + monitor.getThrowable());
		} else {
			target.println(
					AnsiColors.red("[Error]") + "   " + monitor.getTask() + " failed: " + monitor.getThrowable());
		}
	}

}
