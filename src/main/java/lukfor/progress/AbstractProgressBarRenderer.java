package lukfor.progress;

import java.io.PrintStream;

import lukfor.progress.labels.DefaultLabelProvider;
import lukfor.progress.labels.TimeLabelProvider;
import lukfor.progress.styles.DefaultProgressBarStyle;

public abstract class AbstractProgressBarRenderer implements IProgressBarRenderer {

	protected ProgressBar bar;

	protected PrintStream target = System.out;

	protected IProgressBarStyle style = new DefaultProgressBarStyle();

	protected IProgressBarLabelProvider left[] = new IProgressBarLabelProvider[] { new TimeLabelProvider() };

	protected IProgressBarLabelProvider right[] = new IProgressBarLabelProvider[] { new DefaultLabelProvider() };

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

	public void setStyle(IProgressBarStyle style) {
		this.style = style;
	}

	public IProgressBarStyle getStyle() {
		return style;
	}

	public void setLeft(IProgressBarLabelProvider... left) {
		this.left = left;
	}

	public IProgressBarLabelProvider[] getLeft() {
		return left;
	}

	public void setRight(IProgressBarLabelProvider... right) {
		this.right = right;
	}

	public IProgressBarLabelProvider[] getRight() {
		return right;
	}

}
