package lukfor.progress;

public class ProgressBar implements IProgressMonitor {

	private int worked = 0;

	private int total;

	private String task;

	private long startTime = -1;

	private IProgressBarRenderer renderer;

	@Override
	public void beginTask(String name, int totalWork) {
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
	public void internalWorked(double work) {
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
	public void worked(int work) {
		worked++;
		if (renderer != null) {
			renderer.render();
		}
	}

	public int getWorked() {
		return worked;
	}

	public int getTotal() {
		return total;
	}

	public long getExecutionTime() {
		if (startTime > 0) {
			return System.currentTimeMillis() - startTime;
		} else {
			return -1;
		}
	}

	public void addRenderer(IProgressBarRenderer renderer) {
		this.renderer = renderer;
	}

	public String getTask() {
		return task;
	}

}
