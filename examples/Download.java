
//REPOS bintry-lukfor-maven=https://dl.bintray.com/lukfor/maven
//DEPS lukfor:magic-progress:0.2.0

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import lukfor.progress.*;
import lukfor.progress.renderer.labels.*;
import lukfor.progress.renderer.bars.*;
import lukfor.progress.tasks.AbstractFileTask;
import lukfor.progress.tasks.AbstractUrlTask;
import lukfor.progress.tasks.DownloadTask;

public class Download {

	public static void main(String[] args) throws IOException {

		// example: "--no-ansi" flag
		TaskService.setAnsiSupport(true);
		TaskService.setTarget(System.err);

		String url = "http://speedtest.tele2.net/1GB.zip";
		String file = "test.zip";

		DownloadTask task = new DownloadTask(url, file);
 
		TaskService.run(task, ProgressBarBuilder.DOWNLOAD);

	}

}
