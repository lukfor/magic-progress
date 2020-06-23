
//REPOS bintry-lukfor-maven=https://dl.bintray.com/lukfor/maven
//DEPS lukfor:magic-progress:0.0.1

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import lukfor.progress.*;
import lukfor.progress.tasks.AbstractFileTask;
import lukfor.progress.tasks.AbstractUrlTask;

public class Download {

	public static void main(String[] args) throws IOException {

		// example: "--no-ansi" flag
		ProgressMonitor.setAnsiSupport(true);
		ProgressMonitor.setTarget(System.err);

		String url = "http://speedtest.tele2.net/1GB.zip";

		AbstractUrlTask task = new AbstractUrlTask(url) {

			@Override
			public void process(InputStream stream) throws IOException {

				BufferedInputStream bis = new BufferedInputStream(stream);
				byte[] buffer = new byte[1024];
				int count = 0;
				while ((count = bis.read(buffer, 0, 1024)) != -1) {

				}
				bis.close();

			}
		};

		System.out.println("\n");
		System.out.println("\n");
		ProgressMonitor.run(task);
		System.out.println("\n");
		System.out.println("\n");
		System.out.println("\n");

	}

}
