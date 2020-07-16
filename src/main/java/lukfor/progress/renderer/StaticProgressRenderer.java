package lukfor.progress.renderer;

import lukfor.progress.util.AnsiColors;
import lukfor.progress.util.TimeUtil;

public class StaticProgressRenderer extends AbstractProgressRenderer {

	@Override
	public void begin() {
		target.println(AnsiColors.blue("[Run]") + "  " + monitors.get(0).getTask() + "...");
	}

	@Override
	public void render() {

	}

	@Override
	public void finish() {
		target.println(AnsiColors.green("[Done] ") +  monitors.get(0).getTask() + " done! Execution Time: " + TimeUtil.format(monitors.get(0).getExecutionTime()));
	}

}
