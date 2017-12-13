package self.poc.designpatterns;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class IteratorPattern {
	public static void main(String[] args) {
		System.out.println("Iterator Pattern Demonstration.");
		System.out.println("-------------------------------");
		System.out.println("Building string array of books.");
		String[] books = new String[8];
		books[0] = "PowerBuilder Developers Guide, 1994";
		books[1] = "Informix Developers Guide, 1995";
		books[2] = "Informix Universal Data Option, 1996";
		books[3] = "SQL Server Developers Guide, 1999";
		books[4] = "SilverStream Success I, 1999";
		books[5] = "SilverStream Success II, 2000";
		books[6] = "J2EE Unleashed, 2001";
		books[7] = "Enterprise Architect Study Guide, 2002";
		// Turn the string array into a collection.
		System.out.println("Turning string array into a collection.");
		CollectionIF collection = new ConcreteCollection(books);
		// Get an iterator for the collection.
		System.out.println("Getting an iterator for the collection..");
		IteratorIF iterator = collection.iterator();
		// Iterate through and print each object in the list.
		System.out.println("Iterate through the list.");
		int i = 0;
		while (iterator.hasNext()) {
			System.out.println((++i) + " " + iterator.next());
		}
		System.out.println();
	}
}

interface CollectionIF {
	// Interface for creating a
	// collection that needs iterating.
	public IteratorIF iterator();

	public Collection elements();
}

class ConcreteCollection implements CollectionIF {
	// Builds an iterable list of elements
	private List list = new ArrayList();

	public ConcreteCollection(Object[] objectList) {
		for (int i = 0; i < objectList.length; i++) {
			list.add(objectList[i]);
		}
	}

	public IteratorIF iterator() {
		return new ConcreteIterator(this);
	}

	public Collection elements() {
		return Collections.unmodifiableList(list);
	}
}

class ConcreteIterator implements IteratorIF {
	private List list;
	private int index;

	public ConcreteIterator(CollectionIF parm) {
		list = (List) parm.elements();
		index = 0;
	}

	public Object next() throws RuntimeException {
		try {
			return list.get(index++);
		} catch (IndexOutOfBoundsException ioobe) {
			throw new RuntimeException("No Such Element");
		}
	}

	public boolean hasNext() {
		return (index < list.size()) ? true : false;
	}
}

interface IteratorIF {
	// Interface for Iterators.
	public boolean hasNext();

	public Object next();
}