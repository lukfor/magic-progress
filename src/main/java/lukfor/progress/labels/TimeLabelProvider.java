package lukfor.progress.labels;

import lukfor.progress.IProgressBarLabelProvider;
import lukfor.progress.ProgressBar;
import lukfor.progress.util.TimeUtil;

public class TimeLabelProvider implements IProgressBarLabelProvider {

	@Override
	public String getLabel(ProgressBar progressBar) {
		return "[" + TimeUtil.format(progressBar.getExecutionTime()) + "]";
	}

}
