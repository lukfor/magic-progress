package lukfor.progress.styles;

import lukfor.progress.IProgressBarStyle;
import lukfor.progress.util.AnsiColors;
import lukfor.progress.util.TimeUtil;

public class DefaultProgressBarStyle implements IProgressBarStyle {

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

	public String getLabel(long worked, long total) {

		// TODO: style. raw, percentage, bytes

		return " " + worked + "/" + total;
	}

	public String getTime(long time) {
		return "[" + TimeUtil.format(time) + "]";
	}

	// TODO: label, time, bar position. layout?

}
