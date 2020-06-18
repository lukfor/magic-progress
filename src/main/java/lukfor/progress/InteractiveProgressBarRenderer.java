package lukfor.progress;

import java.io.PrintStream;

public class InteractiveProgressBarRenderer implements IProgressBarRenderer {

	private ProgressBar bar;

	private IProgressBarStyle style;

	private float FRAME_RATE = 1 / 10f;

	private long renderTime = 0;

	private PrintStream target = System.out;

	public InteractiveProgressBarRenderer(IProgressBarStyle style) {
		this.style = style;
	}
	
	@Override
	public void setProgressBar(ProgressBar bar) {
		this.bar = bar;
		this.bar.addRenderer(this);
	}

	public void setTarget(PrintStream target) {
		this.target = target;
	}

	public PrintStream getTarget() {
		return target;
	}
	
	public void begin() {
		
	}

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

	public String getAnsiString() {
		String content = "";
		String time = style.getTime(bar.getExecutionTime());
		if (time != null) {
			content += time;
		}

		content += style.getBorderLeft();
		int width = style.getWidth() - style.getBorderLeft().length() - style.getBorderRight().length();

		int progress = (width * bar.getWorked()) / bar.getTotal();
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

		String label = style.getLabel(bar.getWorked(), bar.getTotal());
		if (label != null) {
			content += label;
		}

		return content;

	}

	public void finish() {
		String content = getAnsiString();
		target.print("\r");
		target.println(content);
	}

	protected String repeat(String string, int count) {
		String result = "";
		for (int i = 0; i < count; i++) {
			result += string;
		}
		return result;
	}

}
