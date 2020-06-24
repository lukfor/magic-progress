package lukfor.progress.util;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

import lukfor.progress.IProgressBar;

public final class CountingInputStream extends FilterInputStream {

	private long count;

	private long mark = -1;

	private IProgressBar monitor;

	public CountingInputStream(InputStream in, IProgressBar monitor) {
		super(in);
		this.monitor = monitor;
	}

	public long getCount() {
		return count;
	}

	@Override
	public int read() throws IOException {
		int result = in.read();
		if (result != -1) {
			count++;
			monitor.worked(1);
		}
		return result;
	}

	@Override
	public int read(byte[] b, int off, int len) throws IOException {
		int result = in.read(b, off, len);
		if (result != -1) {
			count += result;
			monitor.worked(result);
		}
		return result;
	}

	@Override
	public long skip(long n) throws IOException {
		long result = in.skip(n);
		count += result;
		monitor.worked(result);
		return result;
	}

	@Override
	public synchronized void mark(int readlimit) {
		in.mark(readlimit);
		mark = count;
	}

	@Override
	public synchronized void reset() throws IOException {
		if (!in.markSupported()) {
			throw new IOException("Mark not supported");
		}
		if (mark == -1) {
			throw new IOException("Mark not set");
		}

		in.reset();
		count = mark;
	}
}