package lukfor.progress.styles;

import lukfor.progress.IProgressBarStyle;
import lukfor.progress.util.TimeUtil;

public class ModernProgressBarStyle implements IProgressBarStyle {

	private String progress = "█";

	private String tick = null;

	private String empty = "░";

	private String borderLeft = "";

	private String borderRight = "";

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

	public String getLabel(long worked, long total) {

		// TODO: style. raw, percentage, bytes

		return " " + worked + "/" + total;
	}

	public String getTime(long time) {
		return "[" + TimeUtil.format(time) + "]";
	}

	// TODO: label, time, bar position. layout?

}
