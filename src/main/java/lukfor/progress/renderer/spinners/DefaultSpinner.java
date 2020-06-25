package lukfor.progress.renderer.spinners;

import lukfor.progress.renderer.IProgressContentProvider;
import lukfor.progress.tasks.monitors.TaskMonitor;
import lukfor.progress.util.AnsiColors;

public class DefaultSpinner implements IProgressContentProvider {

	public static final float FRAME_RATE = 1 / 100f;

	public static final String SEQUENCE = "⠁⠁⠉⠙⠚⠒⠂⠂⠒⠲⠴⠤⠄⠄⠤⠠⠠⠤⠦⠖⠒⠐⠐⠒⠓⠋⠉⠈⠈ ";

	@Override
	public String getContent(TaskMonitor monitor) {
		if (monitor.getWorked() == monitor.getTotal()) {
			return AnsiColors.green(" ✔️ ");
		} else {
			int frame = getFrame(monitor, FRAME_RATE) % SEQUENCE.length();
			return AnsiColors.cyan(" " + SEQUENCE.charAt(frame) + " ");
		}
	}

	public int getFrame(TaskMonitor monitor, float frameRate) {
		return (int) (monitor.getExecutionTime() * frameRate);
	}

}
