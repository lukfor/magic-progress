package lukfor.progress;

import lukfor.progress.renderer.AbstractProgressRenderer;
import lukfor.progress.renderer.AnimatedProgressRenderer;
import lukfor.progress.renderer.IProgressContentProvider;
import lukfor.progress.renderer.StaticProgressRenderer;
import lukfor.progress.renderer.bars.DefaultProgressBar;
import lukfor.progress.renderer.bars.MinimalProgressBar;
import lukfor.progress.renderer.bars.ModernProgressBar;
import lukfor.progress.renderer.labels.DefaultLabel;
import lukfor.progress.renderer.labels.EtaTimeLabel;
import lukfor.progress.renderer.labels.TimeLabel;
import lukfor.progress.renderer.labels.UnitLabel;

public class ProgressBarBuilder {

	public static final IProgressContentProvider DEFAULT_STYLE = new DefaultProgressBar();

	public static final IProgressContentProvider MODERN_STYLE = new ModernProgressBar();

	public static final IProgressContentProvider MINIMAL_STYLE = new MinimalProgressBar();

	public static final ProgressBarBuilder DEFAULT = new ProgressBarBuilder().components(new TimeLabel(), DEFAULT_STYLE,
			new DefaultLabel());

	public static final ProgressBarBuilder MODERN = new ProgressBarBuilder().components(new TimeLabel(), MODERN_STYLE,
			new DefaultLabel());

	public static final ProgressBarBuilder MINIMAL = new ProgressBarBuilder().components(new TimeLabel(), MINIMAL_STYLE,
			new DefaultLabel());

	public static final ProgressBarBuilder DOWNLOAD = new ProgressBarBuilder().components(new EtaTimeLabel(),
			DEFAULT_STYLE, UnitLabel.FILE_SIZE_MB);

	public static final ProgressBarBuilder FILE = new ProgressBarBuilder().components(new EtaTimeLabel(),
			DEFAULT_STYLE, UnitLabel.FILE_SIZE_MB);
	
	private IProgressContentProvider components[] = new IProgressContentProvider[] { new TimeLabel(),
			new DefaultProgressBar(), new DefaultLabel() };

	private boolean animated = true;

	public ProgressBarBuilder() {

	}

	public ProgressBarBuilder animated(boolean animated) {
		this.animated = animated;
		return this;
	}

	public ProgressBarBuilder components(IProgressContentProvider... components) {
		this.components = components;
		return this;
	}

	public AbstractProgressRenderer build() {

		AbstractProgressRenderer renderer = null;

		if (animated) {
			renderer = new AnimatedProgressRenderer();
		} else {
			renderer = new StaticProgressRenderer();
		}
		renderer.setComponents(components);
		return renderer;

	}

}
