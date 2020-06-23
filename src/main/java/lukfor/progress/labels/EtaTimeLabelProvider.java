package lukfor.progress.labels;

import lukfor.progress.IProgressBarLabelProvider;
import lukfor.progress.IProgressMonitor;
import lukfor.progress.ProgressBar;
import lukfor.progress.util.TimeUtil;

public class EtaTimeLabelProvider implements IProgressBarLabelProvider {

	@Override
	public String getLabel(ProgressBar progressBar) {

		if (progressBar.getTotal() == IProgressMonitor.UNKNOWN) {

			return "ETA: unkown";

		} else {

			long eta = (progressBar.getExecutionTime() * progressBar.getTotal() / progressBar.getWorked());

			long remaining = (eta - progressBar.getExecutionTime());
			if (remaining < 0) {
				remaining = 0;
			}

			return "ETA: " + TimeUtil.format(remaining);

		}
	}

}
