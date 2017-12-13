package self.poc.designpatterns;

public class TemplateMethodPattern {
	public static void main(String[] args) {
		System.out.println("TemplateMethod Pattern Demonstration.");
		System.out.println("-------------------------------------");
		// Construct concrete classes.
		System.out.println("Constructing concrete classes.");
		AbstractClass class1 = new ConcreteClass1();
		AbstractClass class2 = new ConcreteClass2();
		// Call template method.
		System.out.println("Calling template methods.");
		class1.templateMethod();
		class2.templateMethod();
		System.out.println();
	}
}

abstract class AbstractClass {
	public void templateMethod() {
		System.out.println("AbstractClass.templateMethod() called.");
		primitiveOperation1();
		primitiveOperationN();
	}

	public abstract void primitiveOperation1();

	public abstract void primitiveOperationN();
}

class ConcreteClass1 extends AbstractClass {
	public void primitiveOperation1() {
		System.out.println("ConcreteClass1.primitiveOperation1() called.");
	}

	public void primitiveOperationN() {
		System.out.println("ConcreteClass1.primitiveOperationN() called.");
	}
}

class ConcreteClass2 extends AbstractClass {
	public void primitiveOperation1() {
		System.out.println("ConcreteClass2.primitiveOperation1() called.");
	}

	public void primitiveOperationN() {
		System.out.println("ConcreteClass2.primitiveOperationN() called.");
	}
}