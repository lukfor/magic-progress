package lukfor.progress.renderer.spinners;

import lukfor.progress.renderer.IProgressIndicator;
import lukfor.progress.tasks.monitors.TaskMonitor;
import lukfor.progress.util.AnsiColors;

public class DefaultSpinner implements IProgressIndicator {

	public static final float FRAME_RATE = 1 / 100f;

	public static final String SEQUENCE = "⠁⠁⠉⠙⠚⠒⠂⠂⠒⠲⠴⠤⠄⠄⠤⠠⠠⠤⠦⠖⠒⠐⠐⠒⠓⠋⠉⠈⠈ ";

	@Override
	public void render(TaskMonitor monitor, StringBuilder buffer) {

		if (monitor.isDone()) {
			if (monitor.isSuccess()) {
				buffer.append(AnsiColors.green(" ✔️ "));
			} else {
				buffer.append(AnsiColors.red(" ❌ "));
			}
		} else {
			int frame = getFrame(monitor, FRAME_RATE) % SEQUENCE.length();
			buffer.append(AnsiColors.cyan(" " + SEQUENCE.charAt(frame) + " "));
		}

	}

	public int getFrame(TaskMonitor monitor, float frameRate) {
		return (int) (monitor.getExecutionTime() * frameRate);
	}

}
