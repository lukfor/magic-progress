package lukfor.progress;

import lukfor.progress.styles.DefaultProgressBarStyle;
import lukfor.progress.styles.MinimalProgressBarStyle;
import lukfor.progress.styles.ModernProgressBarStyle;

public interface IProgressBarStyle {

	public static final IProgressBarStyle DEFAULT = new DefaultProgressBarStyle();

	public static final IProgressBarStyle MODERN = new ModernProgressBarStyle();

	public static final IProgressBarStyle MINIMAL = new MinimalProgressBarStyle();

	public String getProgress();

	public String getTick();

	public String getEmpty();

	public String getBorderLeft();

	public String getBorderRight();

	public int getWidth();

	public String getLabel(long worked, long total);

	public String getTime(long time);

}
