package lukfor.progress.renderer;

import lukfor.progress.tasks.monitors.TaskMonitor;

public class Group implements IProgressContentProvider {

	protected IProgressContentProvider components[];

	public Group(IProgressContentProvider... components) {
		this.components = components;
	}

	@Override
	public String getContent(TaskMonitor monitor) {

		String content = "";
		
		if (components != null && components.length > 0) {
			for (IProgressContentProvider component : components) {
				if (component != null) {
					content += component.getContent(monitor);
				}
			}
		}

		return content;
	}

}
