package lukfor.progress.renderer;

public class RendererThread implements Runnable {

	public static float FRAME_RATE = 1 / 10f;

	private IProgressRenderer renderer;

	private long renderTime = 0;

	public RendererThread(IProgressRenderer renderer) {
		this.renderer = renderer;
	}

	@Override
	public void run() {

		while (renderer.isRunning()) {

			/*
			 * float delta = (System.currentTimeMillis() - renderTime) / 1000f;
			 * 
			 * if (delta < FRAME_RATE) { return; }
			 */

			renderer.render();

			renderTime = System.currentTimeMillis();

			try {
				Thread.sleep((int) (FRAME_RATE * 1000));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}
