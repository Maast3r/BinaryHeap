import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BinaryHeap<T extends Comparable<? super T>> {
	private List<T> list = new ArrayList<>();
	
	private class MinComparator implements Comparator<T>{

		

		public MinComparator() {
//			
		}

		@Override
		public int compare(T t1, T t2) {
			if(t1.compareTo(t2)<0){
				return -1;
			}
			if(t1.compareTo(t2)>0){
				return 1;
			}
			return 0;
		}
	}
	
	
	@SuppressWarnings("unused")
	private class MaxComparator implements Comparator<T>{

	

		public MaxComparator() {
//			
		}

		@Override
		public int compare(T t1, T t2) {
			if(t1.compareTo(t2)<0){
				return 1;
			}
			if(t1.compareTo(t2)>0){
				return -1;
			}
			return 0;
		}
	}
	
	
	public BinaryHeap() {
		this.list.add(null);
	}

	public T deleteMin() {
		if (this.list.size() == 1) {
			return null;
		}
		T result = this.list.get(1); // save the result
		this.list.set(1, this.list.get(this.list.size()-1));
		this.list.remove(this.list.size() - 1);
		Comparator<T> comp = new MinComparator();
		percolateDown(1, comp);

		return result;
	}

	public void insert(T x) {
		int hole = this.list.size();
		this.list.add(x);
		for (int i = hole; i > 1; i /= 2) {
			if (this.list.get(i).compareTo(this.list.get(i / 2)) < 0) {
				T temp = this.list.get(i); // swap items if parent
				this.list.set(i, this.list.get(i / 2)); // is greater than
														// child.
				this.list.set(i / 2, temp);
			}

		}

	}
	
	public void percolateDown(int hole, Comparator<T> comp){
		if(this.list.size()==1){
			return;
		}
		int child;
		T temp = this.list.get(hole);
		
		for(; hole*2<this.list.size();hole=child){
		
			child = hole*2;
			if(child != this.list.size()-1){
				if(comp.compare(this.list.get(child+1)
						, this.list.get(child))<0){
					child++;
				}
			}
			if(comp.compare(this.list.get(child), temp)<0){
				this.list.set(hole, this.list.get(child));
			}
			else{
				break;
			}
		}
		this.list.set(hole, temp);
	}
	
	
	public void sort(T[] array) {
		MinComparator comp = new MinComparator();
		this.list.clear();
		this.list.add(null);
		for(int i=0;i<array.length;i++){
			this.list.add(array[i]);
		}
		
		for(int i=this.list.size()/2;i>0;i--){
			this.percolateDown(i, comp);
		}
		for(int i=0;i<array.length;i++){
			array[i]=this.deleteMin();
		}
		

	}

	@Override
	public String toString() {
		return this.list.toString();

	}

}
