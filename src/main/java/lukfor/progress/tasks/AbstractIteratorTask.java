package lukfor.progress.tasks;

import java.util.Iterator;

import lukfor.progress.tasks.monitors.ITaskMonitor;

public abstract class AbstractIteratorTask<e> implements ITaskRunnable {

	private Iterator<e> iterator;

	public AbstractIteratorTask(Iterator<e> iterator) {
		this.iterator = iterator;
	}

	@Override
	public void run(ITaskMonitor monitor) {

		assert iterator != null : "iterator should not be null";

		monitor.begin("Process collection...", getSize());

		process(new MonitorIterator<e>(monitor, iterator));
		monitor.done();

	}

	public abstract void process(Iterator<e> iterator);

	public abstract long getSize();

	public static class MonitorIterator<f> implements Iterator<f> {

		private Iterator<f> iterator;

		private ITaskMonitor monitor;

		public MonitorIterator(ITaskMonitor monitor, Iterator<f> iterator) {
			this.monitor = monitor;
			this.iterator = iterator;
		}

		@Override
		public f next() {
			monitor.worked(1);
			return iterator.next();
		}

		@Override
		public boolean hasNext() {
			return iterator.hasNext();
		}
	}

}
