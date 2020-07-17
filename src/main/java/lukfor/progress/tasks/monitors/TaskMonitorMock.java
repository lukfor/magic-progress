package lukfor.progress.tasks.monitors;

public class TaskMonitorMock implements ITaskMonitor {

	@Override
	public void beginTask(String name) {

	}

	@Override
	public void beginTask(String name, long totalWork) {

	}

	@Override
	public void done() {

	}

	@Override
	public void setTaskName(String name) {

	}

	@Override
	public void worked(long work) {

	}

	@Override
	public boolean isCanceled() {
		return false;
	}

	@Override
	public void setCanceled() {
		
	}

}
