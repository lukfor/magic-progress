package lukfor.progress;

import lukfor.progress.labels.DefaultLabelProvider;
import lukfor.progress.labels.EtaTimeLabelProvider;
import lukfor.progress.labels.TimeLabelProvider;
import lukfor.progress.labels.UnitLabelProvider;
import lukfor.progress.renderer.AnsiProgressBarRenderer;
import lukfor.progress.renderer.SimpleProgressBarRenderer;
import lukfor.progress.styles.DefaultProgressBarStyle;
import lukfor.progress.styles.MinimalProgressBarStyle;
import lukfor.progress.styles.ModernProgressBarStyle;
import lukfor.progress.util.AnsiColors;

public class ProgressBarBuilder {

	public static final IProgressBarStyle DEFAULT_STYLE = new DefaultProgressBarStyle();

	public static final IProgressBarStyle MODERN_STYLE = new ModernProgressBarStyle();

	public static final IProgressBarStyle MINIMAL_STYLE = new MinimalProgressBarStyle();

	public static ProgressBarBuilder Default = new ProgressBarBuilder().style(DEFAULT_STYLE);

	public static ProgressBarBuilder Modern = new ProgressBarBuilder().style(MODERN_STYLE);

	public static ProgressBarBuilder Minimal = new ProgressBarBuilder().style(MINIMAL_STYLE);

	public static ProgressBarBuilder Download = new ProgressBarBuilder().left(new EtaTimeLabelProvider())
			.right(UnitLabelProvider.FILE_SIZE_MB);

	private IProgressBarStyle style = new DefaultProgressBarStyle();

	private IProgressBarLabelProvider left[] = new IProgressBarLabelProvider[] { new TimeLabelProvider() };

	private IProgressBarLabelProvider right[] = new IProgressBarLabelProvider[] { new DefaultLabelProvider() };

	private boolean ansi = true;

	public ProgressBarBuilder() {

	}

	public ProgressBarBuilder style(IProgressBarStyle style) {
		this.style = style;
		return this;
	}

	public ProgressBarBuilder left(IProgressBarLabelProvider... left) {
		this.left = left;
		return this;
	}

	public ProgressBarBuilder right(IProgressBarLabelProvider... right) {
		this.right = right;
		return this;
	}

	public ProgressBarBuilder ansi(boolean ansi) {
		this.ansi = ansi;
		return this;
	}

	public AbstractProgressBarRenderer build() {

		if (ansi) {
			AnsiColors.enable();
		} else {
			AnsiColors.disable();
		}

		AbstractProgressBarRenderer renderer = null;

		if (ansi) {
			renderer = new AnsiProgressBarRenderer();
		} else {
			renderer = new SimpleProgressBarRenderer();
		}
		renderer.setStyle(style);
		renderer.setLeft(left);
		renderer.setRight(right);
		return renderer;

	}

}
