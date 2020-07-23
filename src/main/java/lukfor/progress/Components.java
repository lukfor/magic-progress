package lukfor.progress;

import lukfor.progress.renderer.IProgressIndicator;
import lukfor.progress.renderer.ProgressIndicatorGroup;
import lukfor.progress.renderer.bars.DefaultProgressBar;
import lukfor.progress.renderer.bars.MinimalProgressBar;
import lukfor.progress.renderer.bars.ModernProgressBar;
import lukfor.progress.renderer.labels.DefaultLabel;
import lukfor.progress.renderer.labels.EtaTimeLabel;
import lukfor.progress.renderer.labels.StringLabel;
import lukfor.progress.renderer.labels.TaskNameLabel;
import lukfor.progress.renderer.labels.TimeLabel;
import lukfor.progress.renderer.labels.UnitLabel;
import lukfor.progress.renderer.spinners.DefaultSpinner;

public class Components {

	public static final IProgressIndicator PROGRESS_BAR = new DefaultProgressBar();

	public static final IProgressIndicator PROGRESS_BAR_MODERN = new ModernProgressBar();

	public static final IProgressIndicator PROGRESS_BAR_MINIMAL = new MinimalProgressBar();

	public static final IProgressIndicator SPINNER = new DefaultSpinner();

	public static final IProgressIndicator TIME = new TimeLabel();

	public static final IProgressIndicator ETA = new EtaTimeLabel();

	public static final IProgressIndicator PROGRESS_LABEL = new DefaultLabel();

	public static final IProgressIndicator TASK_NAME = new TaskNameLabel();

	public static final IProgressIndicator LINE_BREAK = new StringLabel("\n");

	public static final IProgressIndicator FILE_SIZE_GB = new UnitLabel("GB", 1024 * 1024 * 1024);

	public static final IProgressIndicator FILE_SIZE_MB = new UnitLabel("MB", 1024 * 1024);

	public static final IProgressIndicator FILE_SIZE_KB = new UnitLabel("KB", 1024);

	public static final ProgressIndicatorGroup DEFAULT = new ProgressIndicatorGroup(TIME, PROGRESS_BAR, PROGRESS_LABEL);

	public static final ProgressIndicatorGroup MODERN = new ProgressIndicatorGroup(TIME, PROGRESS_BAR_MODERN,
			PROGRESS_LABEL);

	public static final ProgressIndicatorGroup MINIMAL = new ProgressIndicatorGroup(TIME, PROGRESS_BAR_MINIMAL,
			PROGRESS_LABEL);

	public static final ProgressIndicatorGroup DOWNLOAD = new ProgressIndicatorGroup(ETA, PROGRESS_BAR, FILE_SIZE_MB);

	public static final ProgressIndicatorGroup FILE = new ProgressIndicatorGroup(ETA, PROGRESS_BAR, FILE_SIZE_MB);

}
