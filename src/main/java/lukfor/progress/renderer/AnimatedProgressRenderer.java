package lukfor.progress.renderer;

public class AnimatedProgressRenderer extends AbstractProgressRenderer {

	public static float FRAME_RATE = 1 / 10f;

	private long renderTime = 0;

	public AnimatedProgressRenderer() {

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
		if (components != null && components.length > 0) {
			for (IProgressContentProvider component : components) {
				if (component != null) {
					content += component.getContent(monitor);
				}
			}
		}

		return content;

	}

}
