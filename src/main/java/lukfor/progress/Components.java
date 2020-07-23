package lukfor.progress;

import lukfor.progress.renderer.Group;
import lukfor.progress.renderer.IProgressContentProvider;
import lukfor.progress.renderer.bars.DefaultProgressBar;
import lukfor.progress.renderer.bars.MinimalProgressBar;
import lukfor.progress.renderer.bars.ModernProgressBar;
import lukfor.progress.renderer.labels.DefaultLabel;
import lukfor.progress.renderer.labels.EtaTimeLabel;
import lukfor.progress.renderer.labels.TimeLabel;
import lukfor.progress.renderer.labels.UnitLabel;
import lukfor.progress.renderer.spinners.DefaultSpinner;

public class Components {

	public static final IProgressContentProvider PROGRESS_BAR = new DefaultProgressBar();

	public static final IProgressContentProvider PROGRESS_BAR_MODERN = new ModernProgressBar();

	public static final IProgressContentProvider PROGRESS_BAR_MINIMAL = new MinimalProgressBar();

	public static final IProgressContentProvider SPINNER = new DefaultSpinner();

	public static final Group DEFAULT = new Group(new TimeLabel(), PROGRESS_BAR, new DefaultLabel());

	public static final Group MODERN = new Group(new TimeLabel(), PROGRESS_BAR_MODERN, new DefaultLabel());

	public static final Group MINIMAL = new Group(new TimeLabel(), PROGRESS_BAR_MINIMAL, new DefaultLabel());

	public static final Group DOWNLOAD = new Group(new EtaTimeLabel(), PROGRESS_BAR, UnitLabel.FILE_SIZE_MB);

	public static final Group FILE = new Group(new EtaTimeLabel(), PROGRESS_BAR, UnitLabel.FILE_SIZE_MB);

}
