package lukfor.progress;

import java.io.PrintStream;

public abstract class AbstractProgressBarRenderer implements IProgressBarRenderer {

	protected ProgressBar bar;

	protected PrintStream target = System.out;

	@Override
	public void setTarget(PrintStream target) {
		this.target = target;
	}

	public PrintStream getTarget() {
		return target;
	}

	@Override
	public void setProgressBar(ProgressBar bar) {
		this.bar = bar;
		bar.setRenderer(this);
	}

	public ProgressBar getProgressBar() {
		return bar;
	}
	
}
