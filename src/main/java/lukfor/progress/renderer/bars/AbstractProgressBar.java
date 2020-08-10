package lukfor.progress.renderer.bars;

import lukfor.progress.renderer.IProgressIndicator;
import lukfor.progress.tasks.monitors.ITaskMonitor;
import lukfor.progress.tasks.monitors.TaskMonitor;
import lukfor.progress.util.AnsiColors;

public class AbstractProgressBar implements IProgressIndicator {

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

	@Override
	public void render(TaskMonitor monitor, StringBuilder buffer) {

		buffer.append(getBorderLeft());
		int width = getWidth() - getBorderLeft().length() - getBorderRight().length();

		String tick = getTick();

		if (monitor.getTotal() == ITaskMonitor.UNKNOWN || monitor.getTotal() == 0) {
			int frame = (int) (monitor.getExecutionTime() * SPEED);
			int position = frame % width;
			if (position > 0) {
				buffer.append(repeat(getEmpty(), position - 1));
			}
			if (tick != null) {
				buffer.append(tick);
			} else {
				buffer.append(getProgress());
			}
			if (width > position) {
				buffer.append(repeat(getEmpty(), width - position - (position == 0 ? 1 : 0)));
			}
		} else {

			int progress = (int) ((width * monitor.getWorked()) / monitor.getTotal());
			buffer.append(repeat(getProgress(), progress));

			// no tick needed when done.
			if (monitor.getWorked() == monitor.getTotal()) {
				tick = null;
			}
			if (tick != null) {
				buffer.append(tick);
			}

			// if complete, no tick but progress

			buffer.append(repeat(getEmpty(), width - progress - ((tick != null) ? 1 : 0)));

		}

		buffer.append(getBorderRight());

	}

	protected StringBuilder repeat(String string, int count) {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < count; i++) {
			result.append(string);
		}
		return result;
	}

}
