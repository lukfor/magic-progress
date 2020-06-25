package lukfor.progress.renderer.labels;

import lukfor.progress.renderer.IProgressContentProvider;
import lukfor.progress.tasks.monitors.ITaskMonitor;
import lukfor.progress.tasks.monitors.TaskMonitor;

public class UnitLabel implements IProgressContentProvider {

	private String unit;

	private float factor;

	public static String FORMAT = " %.2f %s/%.2f %s";

	public static String FORMAT_UNKNOWN = " %.2f %s";

	public static UnitLabel FILE_SIZE_GB = new UnitLabel("GB", 1024 * 1024 * 1024);

	public static UnitLabel FILE_SIZE_MB = new UnitLabel("MB", 1024 * 1024);

	public static UnitLabel FILE_SIZE_KB = new UnitLabel("KB", 1024);

	public UnitLabel(String unit, float factor) {
		this.unit = unit;
		this.factor = factor;
	}

	@Override
	public String getContent(TaskMonitor monitor) {

		float worked = monitor.getWorked() / factor;

		if (monitor.getTotal() != ITaskMonitor.UNKNOWN) {
			float total = monitor.getTotal() / factor;
			return String.format(FORMAT, worked, unit, total, unit);
		} else {
			return String.format(FORMAT_UNKNOWN, worked, unit);
		}

	}

}
