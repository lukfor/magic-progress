package lukfor.progress.executors;

import java.util.List;

import lukfor.progress.tasks.Task;

public interface ITaskExecutor {

	public void setThreads(int threads);

	public int getThreads();

	public void waitForAll();

	public void run(Task... tasks);

	public void run(List<? extends Task> tasks);
	
}
