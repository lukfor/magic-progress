package lukfor.progress;

public interface IProgressMonitor {

	public final static int UNKNOWN = -1;

	public void beginTask(String name, long totalWork);

	public void done();

	public boolean isCanceled();

	public void setCanceled(boolean value);

	public void setTaskName(String name);

	public void subTask(String name);

	public void worked(long work);

}