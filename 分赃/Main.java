/**
 * https://e-tutor.itsa.org.tw/e-Tutor/mod/programming/view.php?id=6982
 **/
import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while(sc.hasNext()){
			int k = sc.nextInt();
			int[] n = new int[k];
			int sum = 0;
			for(int i = 0; i < k; i++){
				n[i] = sc.nextInt();
				sum += n[i];
			}
			
			//compute
			int min = Integer.MAX_VALUE;
			int num1 = 0;
			int num2 = 0;
			for(int i = 0; i < Math.pow(2, n.length)-1; i++){
				num1 = subset(i, n);
				num2 = sum - num1;
				if(Math.abs(num1 - num2) < min){
					min = Math.abs(num1 - num2);
				}
			}
			System.out.println(min);
		}
	}

	private static int subset(int i, int[] n) {
		int num = 0;
		for(int j = 0; j < n.length; j++){
			if(i % 2 == 1){ // 判断i是否为奇数
				num += n[j];
			}
			i = i >> 1; // 相当于i/2
		}
		return num;
	}
}