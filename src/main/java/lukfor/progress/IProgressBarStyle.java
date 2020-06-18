package lukfor.progress;

public interface IProgressBarStyle {
	
	public String getProgress();
	
	public String getTick();

	public String getEmpty();

	public String getBorderLeft();

	public String getBorderRight();

	public int getWidth();

	public String getLabel(int worked, int total);

	public String getTime(long time);

}
