package lukfor.progress.renderer;

import lukfor.progress.AbstractProgressBarRenderer;
import lukfor.progress.IProgressBarLabelProvider;
import lukfor.progress.IProgressBarStyle;
import lukfor.progress.labels.DefaultLabelProvider;
import lukfor.progress.labels.TimeLabelProvider;
import lukfor.progress.styles.DefaultProgressBarStyle;

public class AnsiProgressBarRenderer extends AbstractProgressBarRenderer {

	private IProgressBarStyle style = new DefaultProgressBarStyle();

	protected IProgressBarLabelProvider labelLeft = new TimeLabelProvider();

	protected IProgressBarLabelProvider labelRight = new DefaultLabelProvider();

	public static float FRAME_RATE = 1 / 10f;

	private long renderTime = 0;

	public AnsiProgressBarRenderer() {

	}

	public AnsiProgressBarRenderer(IProgressBarStyle style) {
		this.style = style;
	}

	public AnsiProgressBarRenderer(IProgressBarStyle style, IProgressBarLabelProvider labelLeft,
			IProgressBarLabelProvider labelRight) {
		this.style = style;
		this.labelLeft = labelLeft;
		this.labelRight = labelRight;
	}

	@Override
	public void begin() {

	}

	@Override
	public void render() {

		float delta = (System.currentTimeMillis() - renderTime) / 1000f;

		if (delta < FRAME_RATE) {
			return;
		}

		String content = getAnsiString();
		target.print("\r");
		target.print(content);

		renderTime = System.currentTimeMillis();

	}

	@Override
	public void finish() {
		String content = getAnsiString();
		target.print("\r");
		target.println(content);
	}

	public String getAnsiString() {
		String content = "";
		if (labelLeft != null) {
			content += labelLeft.getLabel(bar);
		}

		content += style.getBorderLeft();
		int width = style.getWidth() - style.getBorderLeft().length() - style.getBorderRight().length();

		int progress = (int) ((width * bar.getWorked()) / bar.getTotal());
		content += repeat(style.getProgress(), progress);
		String tick = style.getTick();

		// no tick needed when done.
		if (bar.getWorked() == bar.getTotal()) {
			tick = null;
		}
		if (tick != null) {
			content += tick;
		}

		// if complete, no tick but progress

		content += repeat(style.getEmpty(), width - progress - ((tick != null) ? 1 : 0));
		content += style.getBorderRight();

		if (labelRight != null) {
			content += labelRight.getLabel(bar);
		}

		return content;

	}

	protected String repeat(String string, int count) {
		String result = "";
		for (int i = 0; i < count; i++) {
			result += string;
		}
		return result;
	}

	public void setLabelLeft(IProgressBarLabelProvider labelLeft) {
		this.labelLeft = labelLeft;
	}

	public IProgressBarLabelProvider getLabelLeft() {
		return labelLeft;
	}

	public void setLabelRight(IProgressBarLabelProvider labelRight) {
		this.labelRight = labelRight;
	}

	public IProgressBarLabelProvider getLabelRight() {
		return labelRight;
	}

}
