package lukfor.progress.styles;

import lukfor.progress.IProgressBarStyle;

public class MinimalProgressBarStyle implements IProgressBarStyle {

	private String progress = "=";

	private String tick = ">";

	private String empty = " ";

	private String borderLeft = "[";

	private String borderRight = "]";

	private int width = 40;

	public String getProgress() {
		return progress;
	}

	public String getTick() {
		return tick;
	}

	public String getEmpty() {
		return empty;
	}

	public String getBorderLeft() {
		return borderLeft;
	}

	public String getBorderRight() {
		return borderRight;
	}

	public int getWidth() {
		return width;
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
