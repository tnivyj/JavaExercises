import java.util.*;

public class Main {
	public static int len;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while(sc.hasNext()){
			int n = sc.nextInt();
			len = n;
			int[] A = new int[n]; //输入的阵列
			for(int i = 0; i < n; i++){
				A[i] = sc.nextInt();
			}
			if(A.length == 1){ //如果输入一个数字则直接输出
				System.out.println(A[0]);
			}else{
				Mergesort(A);
			}
		}
	}

	private static void Mergesort(int[] A) {
		if(A.length > 1){ //如果分割到仅剩一个数字就不再分割
			int[] B = new int[A.length / 2]; // 存放前半段阵列
			int[] C = new int[A.length - A.length / 2]; //存放后半段阵列，这样写是为了防止奇数个时空指针
			for(int i = 0; i < A.length / 2; i++){
				B[i] = A[i];
			}
			int index = 0;
			for(int i = A.length / 2; i < A.length; i++){
				C[index++] = A[i];
			}
			Mergesort(B); //递回
			Mergesort(C);
			Merge(B, C, A);
			if(A.length == len){ //如果返回长度为输入长度，做输出动作
				String result = "";
				for(int i = 0; i < A.length; i++){
					result += A[i] + " ";
				}
				System.out.println(result.trim()); //切割前后空白
			}
		}
	}

	private static void Merge(int[] B, int[] C, int[] A) {
		int i = 0,j = 0,k = 0;
		while(i < B.length && j < C.length){ //将分割的阵列排序
			if(B[i] <= C[j]){
				A[k] = B[i];
				i++;
			}else{
				A[k] = C[j];
				j++;
			}
			k++;
		}
		if(i == B.length){
			int index = k;
			for(int a = j; a < C.length; a++){
				A[index++] = C[a];
			}
		}else{
			int index = k;
			for(int a = i; a < B.length; a++){
				A[index++] = B[a];
			}
		}
	}
}