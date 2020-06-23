package lukfor.progress.tasks;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public abstract class AbstractFileTask extends AbstractStreamTask {

	private File file;

	public AbstractFileTask(String filename) throws FileNotFoundException {
		this(new File(filename));
	}

	public AbstractFileTask(File file) throws FileNotFoundException {
		super(new FileInputStream(file));
		this.file = file;
	}

	public long getSize() {
		return file.length();
	}

}
