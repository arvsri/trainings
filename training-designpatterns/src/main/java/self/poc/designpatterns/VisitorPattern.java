package self.poc.designpatterns;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class VisitorPattern {
	public static void main(String[] args) {
		System.out.println("Visitor Pattern Demonstration.");
		System.out.println("------------------------------");
		// Construct list of elements.
		System.out.println("Constructing two elements.");
		ElementIF[] elements = new ElementIF[2];
		elements[0] = new ConcreteElementA();
		elements[1] = new ConcreteElementB();
		// Construct object structure.
		System.out.println("Constructing object structure.");
		ObjectStructure objectStructure = new ObjectStructure(elements);
		// Visit elements in object structure.
		System.out.println("Visiting elements in object structure.");
		objectStructure.visitElements();
		System.out.println();
	}
}

class ConcreteElementA implements ElementIF {
	public void accept(VisitorIF parm) {
		parm.visitConcreteElementA(this);
	}

	public void operationA() {
		System.out.println("ConcreteElementA.operationA() called.");
	}
}

class ConcreteElementB implements ElementIF {
	public void accept(VisitorIF parm) {
		parm.visitConcreteElementB(this);
	}

	public void operationB() {
		System.out.println("ConcreteElementB.operationB() called.");
	}
}

class ConcreteVisitor implements VisitorIF {
	public void visitConcreteElementA(ConcreteElementA parm) {
		parm.operationA();
	}

	public void visitConcreteElementB(ConcreteElementB parm) {
		parm.operationB();
	}
}

interface ElementIF {
	public void accept(VisitorIF parm);
}

class ObjectStructure {
	private List objectStruct;
	private VisitorIF visitor;

	public ObjectStructure(ElementIF[] parm) {
		objectStruct = Arrays.asList(parm);
	}

	public void visitElements() {
		if (visitor == null) {
			visitor = new ConcreteVisitor();
		}
		for (Iterator i = objectStruct.iterator(); i.hasNext();) {
			((ElementIF) i.next()).accept(visitor);
		}
	}
}

interface VisitorIF {
	public void visitConcreteElementA(ConcreteElementA parm);

	public void visitConcreteElementB(ConcreteElementB parm);
}
