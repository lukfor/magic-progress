package lukfor.progress.renderer;

import lukfor.progress.util.AnsiColors;
import lukfor.progress.util.TimeUtil;

public class StaticProgressRenderer extends AbstractProgressRenderer {

	@Override
	public void begin() {
		target.println(AnsiColors.blue("[Run]") + "  " + monitor.getTask());
	}

	@Override
	public void render() {

	}

	@Override
	public void finish() {
		target.println(AnsiColors.green("[Done]") + " Execution Time: " + TimeUtil.format(monitor.getExecutionTime()));
	}

}
