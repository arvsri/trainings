package self.poc.designpatterns;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CompositePattern {
	public static void main(String[] args) {
		System.out.println("Composite Pattern Demonstration.");
		System.out.println("--------------------------------");
		System.out.println("Creating leaves, branches and trunk");
		// Create leaves
		Component leaf1 = new Leaf(" leaf#1");
		Component leaf2 = new Leaf(" leaf#2");
		Component leaf3 = new Leaf(" leaf#3");
		// Create branches
		Component branch1 = new Composite(" branch1");
		Component branch2 = new Composite(" branch2");
		// Create trunk
		Component trunk = new Composite("trunk");
		// Add leaf1 and leaf2 to branch1
		branch1.add(leaf1);
		branch1.add(leaf2);
		// Add branch1 to trunk
		trunk.add(branch1);
		// Add leaf3 to branch2
		branch2.add(leaf3);
		// Add branch2 to trunk
		trunk.add(branch2);
		// Show trunk composition
		System.out.println("Displaying trunk composition:");
		trunk.display();
		// Remove branch1 and branch2 from trunk
		trunk.remove(branch1);
		trunk.remove(branch2);
		// Show trunk composition now
		System.out.println("Displaying trunk composition now:");
		trunk.display();
		System.out.println();
	}
}

abstract class Component {
	public abstract void display();

	public void add(Component c) {
	}// override in concrete class; }

	public void remove(Component c) {
	}// override in concrete class; }

	public Component getChild(int index) {
		return null;
	}

	public String getName() {
		return null;
	}
}

class Composite extends Component {
	String name = null;
	List children = new ArrayList();

	public Composite(String parm) {
		this.name = parm;
		System.out.println(parm.trim() + " constructed.");
	}

	public String getName() {
		return name;
	}

	public Component getChild(int parm) {
		Component child;
		try {
			child = (Component) children.get(parm);
		} catch (IndexOutOfBoundsException ioobe) {
			child = null;
		}
		return child;
	}

	public void add(Component parm) {
		try {
			System.out.println("Adding " + parm.getName().trim() + " to "
					+ this.getName().trim());
			children.add(parm);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void remove(Component parm) {
		try {
			System.out.println("Removing " + parm.getName().trim() + " from "
					+ this.getName().trim());
			children.remove(parm);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void display() {
		Iterator iterator = children.iterator();
		System.out.println(this.getName()
				+ (iterator.hasNext() ? " with the following: "
						: " that is bare."));
		while (iterator.hasNext()) {
			((Component) iterator.next()).display();
		}
	}
}

class Leaf extends Component {
	private String name;

	public Leaf(String parm) {
		this.name = parm;
		System.out.println(parm.trim() + " constructed.");
	}

	public void display() {
		System.out.println(this.getName());
	}

	public String getName() {
		return name;
	}
}