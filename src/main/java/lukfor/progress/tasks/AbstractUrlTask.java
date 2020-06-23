package lukfor.progress.tasks;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public abstract class AbstractUrlTask extends AbstractStreamTask {

	private URL url;

	public AbstractUrlTask(String url) throws IOException {
		this(new URL(url));
	}

	public AbstractUrlTask(URL url) throws IOException {
		super(url.openStream());
		this.url = url;
	}

	public long getSize() {

		URLConnection conn = null;
		try {
			conn = url.openConnection();
			if (conn instanceof HttpURLConnection) {
				((HttpURLConnection) conn).setRequestMethod("HEAD");
			}
			conn.getInputStream();
			return conn.getContentLength();
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn instanceof HttpURLConnection) {
				((HttpURLConnection) conn).disconnect();
			}
		}

	}

}
