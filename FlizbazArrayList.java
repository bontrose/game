package group_project;

public class FlizbazArrayList<E>{
	protected E[] data;
	protected int size;
	
	//Default constructor
	public FlizbazArrayList()
	{
		data = (E[]) new Object[10];
	}
	
	//Custom sized constructor
	public FlizbazArrayList(int size)
	{
		data = (E[]) new Object[size];
	}
	
	
	public boolean add(E element) {
		if(data[size] == null){
			E[] temp = (E[]) new Object[data.length+10];
			
			for(int i = 0; i < data.length; i++){
				temp[i] = data[i];
			}
			data = temp;
		}
		
		data[size] = element;
		size++;
		return true;
	}

	public void add(int index, E element) {
		boundCheck(index);
		E[] temp = (E[]) new Object[size+1];
		
		temp = data.clone();
		for(int i = index+1; i < temp.length; i++){
			temp[i] = data[i-1];
		}
		temp[index] = element;
		
		data = temp;
		
		size++;
	}

	public void clear() {							//Make a new array, point old array at new array, garbage collector does my work for me
		E[] temp = (E[]) new Object[10]; 
		data = temp;
		size = 0;
	}

	
	public boolean contains(E element) {
		boolean result = false;
		int index = 0;
		while(index < this.size())
		{
			if(data[index].equals(element))
			{
				result = true;
			}
			index++;
		}
		return result;										//Because this is a boolean there is not need to code for element not existing
	}
	
	public E get(int index) {
		boundCheck(index);
		return data[index];
	}


	public int indexOf(E element) {
		int indexCounter = 0;
		while(!(data[indexCounter].equals(element)))				//Checking if the element exists
		{
			indexCounter++;
			if(indexCounter >= size)						
			{
				System.out.println("No such element found.");		//If not, print message to console and return -1
				return -1;
			}
		}
		return indexCounter;
	}

	
	public boolean isEmpty() {
		if(this.size() > 0)
		{
			return false;
		}
		return true;
	}

	public boolean remove(E element) {
		int indexCounter= 0;
		
		while(!(data[indexCounter].equals(element)))			//Making sure the element exists
		{
			indexCounter++;
			if(indexCounter >= size)
			{
				System.out.println("No such element found.");
				return false;
			}
		}
		
		
		E[] temp = (E[]) new Object[this.size()-1];				//Removing the element
		temp = data.clone();
		for(int i = indexCounter; i < this.size(); i++){
			temp[i] = data[i+1];
		}
		data = temp;
		size--;
		return true;
	}

	
	public E remove(int index) {
		boundCheck(index);
		
		E[] temp = (E[]) new Object[this.size()];	//We do not need to resize down, there can be a null space because the size field holds how many actual elements exist
		E returnHolder = data[index];				//Gives user "last chance" to use element before completely gone
		for(int i = 0; i < index; i++){				
			temp[i] = data[i];					
		}
		for(int j = index+1; j < temp.length; j++)	//Probably not the best implementation of an array copy and subtraction 
		{											//because of the two for loops, but it works for now. We can optimize later.
			temp[j-1] = data[j];
		}
		data = temp;
		
		size--;
		return returnHolder;
	}

	public E set(int index, E element) {
		boundCheck(index);
		return data[index] = element;
	}

	
	public int size() {
		return size;
	}

	//Check to see if the input is greater than zero and no more than # of elements actually in array
	public void boundCheck(int index)
	{
		if(index < 0 || index >= size)
		{
			throw new IndexOutOfBoundsException();
		}
	}
	
	//For visual clarity I added [ , ] to separate the elements of the array in the toString
	public String toString(){
		StringBuilder result = new StringBuilder();
		result.append("[");
		for(int i = 0; i < this.size(); i++){
			result.append(data[i]);
			if(!(i+1 == this.size()))
			{
				result.append(",");
			}
		}
		result.append("]");
		return result.toString();
	}
	
}
