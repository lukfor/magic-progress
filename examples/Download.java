
//REPOS bintry-lukfor-maven=https://dl.bintray.com/lukfor/maven
//DEPS lukfor:magic-progress:0.0.1

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import lukfor.progress.*;
import lukfor.progress.labels.*;
import lukfor.progress.renderer.AnsiProgressBarRenderer;
import lukfor.progress.renderer.*;
import lukfor.progress.tasks.AbstractFileTask;
import lukfor.progress.tasks.AbstractUrlTask;
import lukfor.progress.tasks.DownloadTask;

public class Download {

	public static void main(String[] args) throws IOException {

		// example: "--no-ansi" flag
		ProgressMonitor.setAnsiSupport(true);
		ProgressMonitor.setTarget(System.err);

		String url = "http://speedtest.tele2.net/1GB.zip";
		String file = "test.zip";

		DownloadTask task = new DownloadTask(url, file);

		AnsiProgressBarRenderer renderer = new AnsiProgressBarRenderer();
		renderer.setLabelRight(UnitLabelProvider.FILE_SIZE_MB);
		renderer.setLabelLeft(new EtaTimeLabelProvider());

		ProgressMonitor.run(task, renderer);

	}

}
