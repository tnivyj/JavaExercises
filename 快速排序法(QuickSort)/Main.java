import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while(sc.hasNext()){
			int n = sc.nextInt();
			int[] A = new int[n]; //需要排列的阵列
			for(int i = 0; i < n; i++){
				A[i] = sc.nextInt();
			}
			Quicksort(A, 0, A.length-1);
			String result = ""; //output
			for(int i = 0; i < n; i++){
				result += A[i] + " ";
			}
			System.out.println(result.trim());
		}
	}

	private static void Quicksort(int[] A, int l, int r) {
		if(l < r){
			int s = Partition(A, l, r);
			Quicksort(A, l, s-1);
			Quicksort(A, s+1, r);
		}
	}

	private static int Partition(int[] A, int l, int r) {
		int p = A[l]; //判断指标
		int i = l+1; //i要寻找比p大的
		int j = r; //j要寻找比p小的
		while(i < j){ //当i大于等于j时暂停
			try{
				while(A[i] < p){ //当A[i]大于等于p时暂停
					i++;
				}
			}catch(ArrayIndexOutOfBoundsException e){ //防止右边数字均小于p时的报错
				i--;
			}
			while(A[j] > p){ //当A[j]小于等于p时暂停
				j--;
			}
			swap(A, i, j);
		}
		swap(A, i, j);
		if(A[l] > A[j]){ //当i等于j的时候，可能会产生将原本排好位子的数字交换，这样写可以防止交换已排好的位子
			swap(A, l, j);
		}
		return j;
	}

	private static int[] swap(int[] A, int i, int j) { //交换位子
		int c = A[i];
		A[i] = A[j];
		A[j] = c;
		return A;
	}
}