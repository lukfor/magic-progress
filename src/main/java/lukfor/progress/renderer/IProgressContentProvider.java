package lukfor.progress.renderer;

import lukfor.progress.tasks.monitors.TaskMonitor;

public interface IProgressContentProvider {

	public static final IProgressContentProvider EMPTY = null;

	public String getContent(TaskMonitor monitor);

}
