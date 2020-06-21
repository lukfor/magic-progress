package lukfor.progress.renderer;

import java.io.PrintStream;

import lukfor.progress.IProgressBarRenderer;
import lukfor.progress.ProgressBar;
import lukfor.progress.util.AnsiColors;
import lukfor.progress.util.TimeUtil;

public class SimpleProgressBarRenderer implements IProgressBarRenderer {

	private ProgressBar bar;

	private PrintStream target = System.out;

	public SimpleProgressBarRenderer() {

	}

	@Override
	public void setProgressBar(ProgressBar bar) {
		this.bar = bar;
		this.bar.addRenderer(this);
	}

	public void setTarget(PrintStream target) {
		this.target = target;
	}

	@Override
	public void begin() {
		target.println(AnsiColors.blue("[Run]") + "  " + bar.getTask());
	}

	public void render() {

	}

	public void finish() {
		target.println(AnsiColors.green("[Done]") + " Execution Time: " + TimeUtil.format(bar.getExecutionTime()));
	}

}
