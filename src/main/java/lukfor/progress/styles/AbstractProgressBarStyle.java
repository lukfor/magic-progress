package lukfor.progress.styles;

import lukfor.progress.IProgressBarStyle;
import lukfor.progress.IProgressBar;
import lukfor.progress.ProgressBar;
import lukfor.progress.util.AnsiColors;

public class AbstractProgressBarStyle implements IProgressBarStyle {

	public static float SPEED = 1 / 100f;
	
	private String progress = AnsiColors.cyan("#");

	private String tick = AnsiColors.cyan(">");

	private String empty = AnsiColors.blue("-");

	private String borderLeft = "[";

	private String borderRight = "]";

	private int width = 40;

	public String getProgress() {
		return progress;
	}

	public void setProgress(String progress) {
		this.progress = progress;
	}

	public String getTick() {
		return tick;
	}

	public void setTick(String tick) {
		this.tick = tick;
	}

	public String getEmpty() {
		return empty;
	}

	public void setEmpty(String empty) {
		this.empty = empty;
	}

	public String getBorderLeft() {
		return borderLeft;
	}

	public void setBorderLeft(String borderLeft) {
		this.borderLeft = borderLeft;
	}

	public String getBorderRight() {
		return borderRight;
	}

	public void setBorderRight(String borderRight) {
		this.borderRight = borderRight;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	
	public String renderBar(ProgressBar bar) {
		String content = getBorderLeft();
		int width = getWidth() - getBorderLeft().length() - getBorderRight().length();

		String tick = getTick();

		if (bar.getTotal() == IProgressBar.UNKNOWN) {
			int frame = (int) (bar.getExecutionTime() * SPEED);
			int position = frame % width;
			if (position > 0) {
				content += repeat(getEmpty(), position - 1);
			}
			if (tick != null) {
				content += tick;
			} else {
				content += getProgress();
			}
			if (width > position) {
				content += repeat(getEmpty(), width - position - (position == 0 ? 1 : 0));
			}
		} else {

			int progress = (int) ((width * bar.getWorked()) / bar.getTotal());
			content += repeat(getProgress(), progress);

			// no tick needed when done.
			if (bar.getWorked() == bar.getTotal()) {
				tick = null;
			}
			if (tick != null) {
				content += tick;
			}

			// if complete, no tick but progress

			content += repeat(getEmpty(), width - progress - ((tick != null) ? 1 : 0));

		}

		content += getBorderRight();
		
		return content;
		
	}
	
	protected String repeat(String string, int count) {
		String result = "";
		for (int i = 0; i < count; i++) {
			result += string;
		}
		return result;
	}


}
