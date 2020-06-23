package lukfor.progress.renderer;

import lukfor.progress.AbstractProgressBarRenderer;
import lukfor.progress.util.AnsiColors;
import lukfor.progress.util.TimeUtil;

public class SimpleProgressBarRenderer extends AbstractProgressBarRenderer {

	
	@Override
	public void begin() {
		target.println(AnsiColors.blue("[Run]") + "  " + bar.getTask());
	}

	@Override
	public void render() {

	}

	@Override
	public void finish() {
		target.println(AnsiColors.green("[Done]") + " Execution Time: " + TimeUtil.format(bar.getExecutionTime()));
	}

}
