package lukfor.progress.tasks;

import java.io.IOException;
import java.io.InputStream;

import lukfor.progress.IProgressBar;
import lukfor.progress.ITaskWithProgress;
import lukfor.progress.util.CountingInputStream;

public abstract class AbstractStreamTask implements ITaskWithProgress {

	private InputStream stream;

	public AbstractStreamTask(InputStream stream) {
		this.stream = stream;
	}

	@Override
	public void run(IProgressBar monitor) {
		long size = getSize();
		monitor.beginTask("Process stream...", size);

		CountingInputStream countingStream = new CountingInputStream(stream, monitor);
		try {
			process(countingStream);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public abstract void process(InputStream stream) throws IOException;

	public abstract long getSize();

}
