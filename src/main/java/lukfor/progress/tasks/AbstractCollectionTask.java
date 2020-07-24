package lukfor.progress.tasks;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

public abstract class AbstractCollectionTask<e> extends AbstractIteratorTask<e> {

	private Collection<e> collection;

	public AbstractCollectionTask(Collection<e> collection) {
		super(collection.iterator());
		this.collection = collection;
	}

	public abstract void process(Iterator<e> iterator) throws IOException;

	@Override
	public long getSize() {	
		return collection.size();
	}
	
}
