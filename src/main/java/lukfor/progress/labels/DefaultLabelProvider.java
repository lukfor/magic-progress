package lukfor.progress.labels;

import lukfor.progress.IProgressBarLabelProvider;
import lukfor.progress.IProgressBar;
import lukfor.progress.ProgressBar;

public class DefaultLabelProvider implements IProgressBarLabelProvider {

	@Override
	public String getLabel(ProgressBar progressBar) {
		if (progressBar.getTotal() != IProgressBar.UNKNOWN) {
			return " " + progressBar.getWorked() + "/" + progressBar.getTotal();
		} else {
			return " " + progressBar.getWorked();
		}
	}

}
