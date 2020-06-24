package lukfor.progress.renderer;

import lukfor.progress.AbstractProgressBarRenderer;
import lukfor.progress.IProgressBarLabelProvider;

public class AnsiProgressBarRenderer extends AbstractProgressBarRenderer {

	public static float FRAME_RATE = 1 / 10f;

	private long renderTime = 0;

	public AnsiProgressBarRenderer() {

	}

	@Override
	public void begin() {

	}

	@Override
	public void render() {

		float delta = (System.currentTimeMillis() - renderTime) / 1000f;

		if (delta < FRAME_RATE) {
			return;
		}

		String content = buildAnsiString();
		target.print("\r");
		target.print(content);

		renderTime = System.currentTimeMillis();

	}

	@Override
	public void finish() {

		String content = buildAnsiString();
		target.print("\r");
		target.println(content);

	}

	public String buildAnsiString() {

		String content = "";
		if (left != null && left.length > 0) {
			for (IProgressBarLabelProvider l : left) {
				if (l != null) {
					content += l.getLabel(bar);
				}
			}
		}

		if (style != null) {
			content += style.renderBar(bar);
		}

		if (right != null && right.length > 0) {
			for (IProgressBarLabelProvider r : right) {
				if (r != null) {
					content += r.getLabel(bar);
				}
			}
		}

		return content;

	}

}
