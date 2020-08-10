package lukfor.progress.renderer;

import lukfor.progress.tasks.monitors.TaskMonitor;

public interface IProgressIndicator {

	public static final IProgressIndicator EMPTY = null;

	public void render(TaskMonitor monitor, StringBuilder buffer);

}
