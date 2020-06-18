package lukfor.progress;

import java.io.PrintStream;

public interface IProgressBarRenderer {

	public void setTarget(PrintStream target);

	public void setProgressBar(ProgressBar bar);

	public void begin();
	
	public void render();

	public void finish();
}
