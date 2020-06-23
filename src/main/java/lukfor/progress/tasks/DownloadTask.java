package lukfor.progress.tasks;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class DownloadTask extends AbstractUrlTask {

	private File file;

	public DownloadTask(String url, String filename) throws IOException {
		super(url);
		this.file = new File(filename);
	}

	public DownloadTask(URL url, File file) throws IOException {
		super(url);
		this.file = file;
	}

	@Override
	public void process(InputStream stream) throws IOException {

		BufferedInputStream bis = new BufferedInputStream(stream);

		FileOutputStream fis = new FileOutputStream(file);

		byte[] buffer = new byte[1024];
		int count = 0;
		while ((count = bis.read(buffer, 0, 1024)) != -1) {
			fis.write(buffer, 0, count);
		}
		fis.close();
		bis.close();

	}

}
