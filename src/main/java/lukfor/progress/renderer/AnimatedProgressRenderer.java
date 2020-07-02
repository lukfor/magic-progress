package lukfor.progress.renderer;

import lukfor.progress.tasks.monitors.TaskMonitor;

public class AnimatedProgressRenderer extends AbstractProgressRenderer {

	public static float FRAME_RATE = 1 / 10f;

	private long renderTime = 0;

	private int lines = 0;

	public static final String ANSI_CSI = (char)27 + "[";

	public AnimatedProgressRenderer() {

	}

	@Override
	public synchronized void begin() {
		render();
	}

	@Override
	public synchronized void render() {

		float delta = (System.currentTimeMillis() - renderTime) / 1000f;

		if (delta < FRAME_RATE) {
			return;
		}

		String content = buildAnsiString();

		// move cursor up
		if (lines > 0) {
			System.out.print(ANSI_CSI + (lines) + "A"); // up xx lines
		}

		target.print("\r");
		target.println(content);

		lines = countLines(content);

		renderTime = System.currentTimeMillis();

	}

	@Override
	public synchronized void finish() {

		String content = buildAnsiString();
		
		// move cursor up
		if (lines > 0) {
			System.out.print(ANSI_CSI + (lines) + "A"); // up xx lines
		}
		
		target.print("\r");
		target.println(content);

		lines = countLines(content);
		
	}

	public String buildAnsiString() {

		String content = "";
		int i = 0;
		for (TaskMonitor monitor : monitors) {
			i++;
			if (components != null && components.length > 0) {
				for (IProgressContentProvider component : components) {
					if (component != null) {
						content += component.getContent(monitor);
					}
				}
			}
			if (i != monitors.size()) {
				content += "\n";
			}
		}

		return content;

	}

	private static int countLines(String str) {
		String[] lines = str.split("\r\n|\r|\n");
		return lines.length;
	}

}
