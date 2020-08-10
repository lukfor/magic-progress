package lukfor.progress.renderer;

import lukfor.progress.tasks.monitors.TaskMonitor;

public class ProgressIndicatorGroup implements IProgressIndicator {

	protected IProgressIndicator components[];

	public ProgressIndicatorGroup(IProgressIndicator... components) {
		this.components = components;
	}

	@Override
	public void render(TaskMonitor monitor, StringBuilder buffer) {

		if (components != null && components.length > 0) {
			for (IProgressIndicator component : components) {
				if (component != null) {
					component.render(monitor, buffer);
				}
			}
		}

	}

}
