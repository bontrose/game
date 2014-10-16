package group_project;

public class Stack<E> {
	FlizbazArrayList<E> arrayList = new FlizbazArrayList<E>();
	public E push(E element)
	{
		arrayList.add(element);
		return arrayList.get(arrayList.size()-1);
	}
	
	public E pop(){
		return arrayList.remove(arrayList.size()-1);
	}
	
	public E peek(){
		return arrayList.get(arrayList.size()-1);
	}
}
