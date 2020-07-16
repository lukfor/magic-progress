
//REPOS bintray-lukfor-maven=https://dl.bintray.com/lukfor/maven,bintray-genepi-maven=https://dl.bintray.com/genepi/maven
//DEPS lukfor:magic-progress:0.1.0,genepi:genepi-io:1.0.12

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import lukfor.progress.*;
import lukfor.progress.renderer.labels.*;
import lukfor.progress.renderer.bars.*;
import lukfor.progress.tasks.AbstractFileTask;
import lukfor.progress.tasks.AbstractUrlTask;
import lukfor.progress.tasks.DownloadTask;

import genepi.io.table.reader.*;

public class TableReader {

	public static void main(String[] args) throws IOException {

		// example: "--no-ansi" flag
		TaskService.setAnsiSupport(true);
		TaskService.setTarget(System.err);
 
		String url = "https://www.stats.govt.nz/assets/Uploads/Business-price-indexes/Business-price-indexes-March-2020-quarter/Download-data/business-price-indexes-march-2020-quarter-csv.csv";
		String file = "test.csv";

		DownloadTask task = new DownloadTask(url, file);
		SumTask task2 = new SumTask(file);

		System.out.println("Download file:");
		TaskService.run(task, ProgressBarBuilder.DOWNLOAD);
		
		System.out.println("Calculate sum:");
		TaskService.run(task2, ProgressBarBuilder.FILE);
		System.out.println("Sum: " + task2.getSum());

	}

	static class SumTask extends AbstractFileTask {

		private double sum = 0;

		public SumTask(String file) throws FileNotFoundException {
			super(file);
		}

		@Override
		public void process(InputStream stream) throws IOException {

			ITableReader reader = new CsvTableReader(new DataInputStream(stream), ',');

			while (reader.next()) {
				if (reader.getString("data_value").isEmpty()) {
					continue;
				}
				double value = reader.getDouble("data_value");
				sum += value;
			}

			reader.close();

		}

		public double getSum() {
			return sum;
		}
	}

}
