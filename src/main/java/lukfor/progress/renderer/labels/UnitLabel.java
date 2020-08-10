package lukfor.progress.renderer.labels;

import lukfor.progress.renderer.IProgressIndicator;
import lukfor.progress.tasks.monitors.ITaskMonitor;
import lukfor.progress.tasks.monitors.TaskMonitor;

public class UnitLabel implements IProgressIndicator {

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
	public void render(TaskMonitor monitor, StringBuilder buffer) {

		float worked = monitor.getWorked() / factor;

		if (monitor.getTotal() != ITaskMonitor.UNKNOWN) {
			float total = monitor.getTotal() / factor;
			String label = String.format(FORMAT, worked, unit, total, unit);
			buffer.append(label);
		} else {
			String label = String.format(FORMAT_UNKNOWN, worked, unit);
			buffer.append(label);
		}

	}

}
