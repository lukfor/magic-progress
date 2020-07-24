package lukfor.progress.tasks.monitors;

public class TaskMonitorMock implements ITaskMonitor {

	@Override
	public void begin(String name) {

	}

	@Override
	public void begin(String name, long totalWork) {

	}

	@Override
	public void done() {

	}
	
	@Override
	public void failed(Throwable throwable) {
		
	}

	@Override
	public void update(String name) {

	}

	@Override
	public void worked(long work) {

	}

	@Override
	public boolean isCanceled() {
		return false;
	}

	@Override
	public void setCanceled(boolean canceled) {
		
	}

}
