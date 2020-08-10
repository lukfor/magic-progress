package lukfor.progress.tasks.monitors;

public interface ITaskMonitor {

	public final static int UNKNOWN = -1;

	public void begin(String name);

	public void begin(String name, long totalWork);

	public void done();

	public void failed(Throwable throwable);

	public void update(String name);

	public void worked(long work);

	public boolean isCanceled();

	public void setCanceled(boolean canceled);

}