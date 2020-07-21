package lukfor.progress.tasks.monitors;

public interface ITaskMonitor {

	public final static int UNKNOWN = -1;

	public void beginTask(String name);
	
	public void beginTask(String name, long totalWork);

	public void done();
	
	public void failed(Throwable throwable);

	public void setTaskName(String name);

	public void worked(long work);

    public boolean isCanceled();

    public void setCanceled();
    
}