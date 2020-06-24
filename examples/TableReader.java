
//REPOS bintray-lukfor-maven=https://dl.bintray.com/lukfor/maven,bintray-genepi-maven=https://dl.bintray.com/genepi/maven
//DEPS lukfor:magic-progress:0.0.1,genepi:genepi-io:1.0.12

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import lukfor.progress.*;
import lukfor.progress.labels.*;
import lukfor.progress.renderer.AnsiProgressBarRenderer;
import lukfor.progress.renderer.*;
import lukfor.progress.tasks.AbstractFileTask;
import lukfor.progress.tasks.AbstractUrlTask;
import lukfor.progress.tasks.DownloadTask;
import genepi.io.table.reader.*;

public class TableReader {

	public static void main(String[] args) throws IOException {

		// example: "--no-ansi" flag
		ProgressMonitor.setAnsiSupport(true);
		ProgressMonitor.setTarget(System.err);

		String url = "https://www.stats.govt.nz/assets/Uploads/Business-price-indexes/Business-price-indexes-March-2020-quarter/Download-data/business-price-indexes-march-2020-quarter-csv.csv";
		String file = "test.csv";

		DownloadTask task = new DownloadTask(url, file);
		SumTask task2 = new SumTask(file);

		AnsiProgressBarRenderer renderer = new AnsiProgressBarRenderer();
		renderer.setRight(UnitLabelProvider.FILE_SIZE_MB);

		System.out.println("Download file:");
		ProgressMonitor.run(task, renderer);
		System.out.println("Calculate sum:");
		ProgressMonitor.run(task2, renderer);
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
