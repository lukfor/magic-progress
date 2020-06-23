package lukfor.progress.labels;

import lukfor.progress.IProgressBarLabelProvider;
import lukfor.progress.ProgressBar;

public class DefaultLabelProvider implements IProgressBarLabelProvider {

	@Override
	public String getLabel(ProgressBar progressBar) {
		return " " + progressBar.getWorked() + "/" + progressBar.getTotal();
	}

}
