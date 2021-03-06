package lukfor.progress.renderer;

import java.io.PrintStream;

import lukfor.progress.tasks.TaskFailureStrategy;
import lukfor.progress.tasks.monitors.TaskMonitor;

public interface IProgressRenderer {

	public void setTarget(PrintStream target);

	public void setTaskFailureStrategy(TaskFailureStrategy taskFailureStrategy);
	
	public void addTaskMonitor(TaskMonitor monitor);

	public void begin(TaskMonitor monitor);
	
	public void render();

	public void finish(TaskMonitor monitor);
	
	public boolean isRunning();
	
}
