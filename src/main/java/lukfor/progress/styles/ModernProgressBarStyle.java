package lukfor.progress.styles;

public class ModernProgressBarStyle extends AbstractProgressBarStyle {

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

}
