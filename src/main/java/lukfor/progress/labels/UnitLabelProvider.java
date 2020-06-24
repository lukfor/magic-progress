package lukfor.progress.labels;

import lukfor.progress.IProgressBarLabelProvider;
import lukfor.progress.IProgressBar;
import lukfor.progress.ProgressBar;

public class UnitLabelProvider implements IProgressBarLabelProvider {

	private String unit;

	private float factor;

	public static String FORMAT = " %.2f %s/%.2f %s";

	public static String FORMAT_UNKNOWN = " %.2f %s";

	public static UnitLabelProvider FILE_SIZE_GB = new UnitLabelProvider("GB", 1024 * 1024 * 1024);

	public static UnitLabelProvider FILE_SIZE_MB = new UnitLabelProvider("MB", 1024 * 1024);

	public static UnitLabelProvider FILE_SIZE_KB = new UnitLabelProvider("KB", 1024);

	public UnitLabelProvider(String unit, float factor) {
		this.unit = unit;
		this.factor = factor;
	}

	@Override
	public String getLabel(ProgressBar progressBar) {

		float worked = progressBar.getWorked() / factor;

		if (progressBar.getTotal() != IProgressBar.UNKNOWN) {
			float total = progressBar.getTotal() / factor;
			return String.format(FORMAT, worked, unit, total, unit);
		} else {
			return String.format(FORMAT_UNKNOWN, worked, unit);
		}

	}

}
