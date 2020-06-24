package lukfor.progress;

public interface IProgressBarStyle {

	public static final IProgressBarStyle EMPTY = null;

	public String renderBar(ProgressBar bar);

}
