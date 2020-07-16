package lukfor.progress.renderer;

import java.io.PrintStream;

import lukfor.progress.tasks.monitors.TaskMonitor;

public interface IProgressRenderer {

	public void setTarget(PrintStream target);

	public void addTaskMonitor(TaskMonitor monitor);

	public void begin();
	
	public void render(boolean force);

	public void finish();
}
