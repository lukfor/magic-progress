package lukfor.progress.tasks;

import java.util.Collection;
import java.util.Iterator;

public abstract class AbstractCollectionTask<e> extends AbstractIteratorTask<e> {

	private Collection<e> collection;

	public AbstractCollectionTask(Collection<e> collection) {
		super(collection.iterator());
		this.collection = collection;
	}

	public abstract void process(Iterator<e> iterator);

	@Override
	public long getSize() {	
		return collection.size();
	}
	
}
