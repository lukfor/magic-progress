package lukfor.progress;

public interface IProgressBarLabelProvider {

	public static final IProgressBarLabelProvider EMPTY = null;
	
	public String getLabel(ProgressBar progressBar);
	
}
