package group_project;

public class FlizbazArrayList_Test {
	public static void main(String[] args)
	{
		FlizbazArrayList<Integer> test1 = new FlizbazArrayList<Integer>();
		FlizbazArrayList<Double> test2 = new FlizbazArrayList<Double>(300);
		FlizbazArrayList<String> test3 = new FlizbazArrayList<String>();
		
		//Test add integers, filling with default size of 10
		for(int i = 0; i < 10; i++)
		{
			test1.add(i);
			System.out.println(test1);
		}
		
		//Test index removal of Test1 until empty
		System.out.println(test1);
		test1.remove(0);
		System.out.println(test1);
		test1.remove(1);
		System.out.println(test1);
		test1.remove(2);
		System.out.println(test1);
		test1.remove(3);
		System.out.println(test1);
		test1.remove(5);
		System.out.println(test1);
		test1.remove(0);	test1.remove(0);	test1.remove(0);	test1.remove(0);	test1.remove(0);
		System.out.println(test1);
	
		//Test add doubles, filling with 300 doubles
		for(int i = 0; i < 300; i++)
		{
			test2.add((double)i);
		}
		System.out.println(test2); //Printing this out at once, rather than 300 times
		
		//Test add Strings,
		test3.add("Zero");
		test3.add("One");
		test3.add("Two");
		test3.add("Three");
		test3.add("Four");
		test3.add("Five");
		System.out.println(test3);
		
		//Testing get
		System.out.println();
		System.out.println("Get test: " + test3.get(4) + " " + test3.get(1) + " " + test3.get(2) + " " + test2.get(273));
		
		//Testing size
		System.out.println();
		System.out.println("Test1 size = " + test1.size() + " " + "Test2 size = " + test2.size() + " " + "Test3 size = " + test3.size());
		
		//Testing isEmpty
		System.out.println();
		System.out.println("Test1 isEmpty? " + test1.isEmpty() + " " + "Test2 isEmpty? " + test2.isEmpty() + " " + "Test3 isEmpty? " + test3.isEmpty());
		
		//Testing clear
		test2.clear();
		System.out.println("Cleared out test 2 " + test2);
		
		//Testing contains
		System.out.println("Testing contains " + test3.contains("Two"));
		
		//Two parameter add test
		test3.add(5, "NaN");
		System.out.println(test3);
		test3.add(0, "NaN");
		System.out.println(test3);
		test3.add(3, "NaN");
		System.out.println(test3);
		
		//Element remove test
		test3.remove("Three");
		System.out.println(test3);
		test3.remove("NaN");
		System.out.println(test3);
		test3.remove("Flizbaz");
		System.out.println(test3);
	}
}
