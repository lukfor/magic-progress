package lukfor.progress.styles;

import lukfor.progress.IProgressBarStyle;
import lukfor.progress.util.AnsiColors;

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

	public String getLabel(int worked, int total) {

		// TODO: style. raw, percentage, bytes

		return " " + worked + "/" + total;
	}

	public String getTime(long time) {

		long sec = time / 1000 % 60;
		String secString = "" + sec;
		if (sec < 10) {
			secString = "0" + sec;
		}

		long min = time / 1000 / 60 % 60;
		String minString = "" + min;
		if (min < 10) {
			minString = "0" + min;
		}

		long hours = time / 1000 / 60 / 60;
		String hoursString = "" + hours;
		if (hours < 10) {
			hoursString = "0" + hours;
		}
		return "[" + hoursString + ":" + minString + ":" + secString + "] ";
	}

	// TODO: label, time, bar position. layout?

}
