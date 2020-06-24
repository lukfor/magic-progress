package lukfor.progress;

public class ProgressBar implements IProgressBar {

	private long worked = 0;

	private long total;

	private String task;

	private long startTime = -1;

	private IProgressBarRenderer renderer;

	@Override
	public void beginTask(String name, long totalWork) {
		this.task = name;
		this.total = totalWork;
		this.startTime = System.currentTimeMillis();
		if (renderer != null) {
			renderer.begin();
		}
	}

	@Override
	public void done() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isCanceled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setCanceled(boolean value) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setTaskName(String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public void subTask(String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public void worked(long work) {
		worked += work;
		if (renderer != null) {
			renderer.render();
		}
	}

	public long getWorked() {
		return worked;
	}

	public long getTotal() {
		return total;
	}

	public long getExecutionTime() {
		if (startTime > 0) {
			return System.currentTimeMillis() - startTime;
		} else {
			return -1;
		}
	}

	public void setRenderer(IProgressBarRenderer renderer) {
		this.renderer = renderer;
	}

	public String getTask() {
		return task;
	}

}
