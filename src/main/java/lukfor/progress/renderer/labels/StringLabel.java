package lukfor.progress.renderer.labels;

import lukfor.progress.renderer.IProgressContentProvider;
import lukfor.progress.tasks.monitors.TaskMonitor;

public class StringLabel implements IProgressContentProvider {

	private String text;

	public StringLabel(String text) {
		this.text = text;
	}

	@Override
	public String getContent(TaskMonitor monitor) {
		return this.text;
	}

}
