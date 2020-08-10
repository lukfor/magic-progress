package lukfor.progress.renderer.labels;

import lukfor.progress.renderer.IProgressIndicator;
import lukfor.progress.tasks.monitors.TaskMonitor;

public class StringLabel implements IProgressIndicator {

	private String text;

	public StringLabel(String text) {
		this.text = text;
	}

	@Override
	public void render(TaskMonitor monitor, StringBuilder buffer) {	
		buffer.append(this.text);				
	}

}
