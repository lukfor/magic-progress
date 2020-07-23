package lukfor.progress.renderer;

import lukfor.progress.tasks.monitors.TaskMonitor;

public class AnimatedProgressRenderer extends AbstractProgressRenderer {

	private int lines = 0;

	public static final String ANSI_CSI = (char) 27 + "[";

	public AnimatedProgressRenderer() {

	}

	@Override
	public synchronized void begin(TaskMonitor monitor) {

	}

	@Override
	public synchronized void render() {

		StringBuilder content = buildAnsiString();
		String string = content.toString();

		// move cursor up
		if (lines > 0) {
			target.print(ANSI_CSI + (lines) + "A"); // up xx lines
		}

		target.print("\r");
		target.println(string);

		lines = countLines(string);

	}

	@Override
	public synchronized void finish(TaskMonitor monitor) {
		super.finish(monitor);
		render();
	}

	public StringBuilder buildAnsiString() {

		StringBuilder buffer = new StringBuilder();

		for (TaskMonitor monitor : monitors) {

			if (!monitor.isRunning() && !monitor.isDone()) {
				continue;
			}

			if (monitor != monitors.get(0)) {
				buffer.append("\n");
			}

			if (components != null && components.length > 0) {
				for (IProgressIndicator component : components) {
					if (component != null) {
						component.render(monitor, buffer);
					}
				}
			}

		}

		return buffer;

	}

	private static int countLines(String str) {
		String[] lines = str.split("\r\n|\r|\n");
		return lines.length;
	}

}
