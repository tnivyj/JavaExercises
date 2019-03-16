/**
 * https://e-tutor.itsa.org.tw/e-Tutor/mod/programming/view.php?id=43722
 */
import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		for(int i = 0; i < n; i++){
			int m = sc.nextInt();
			int[][] xian = new int[m][2];
			for(int j = 0; j < m; j++){
				xian[j][0] = sc.nextInt();
				xian[j][1] = sc.nextInt();
			}
			System.out.println(sum(sort(xian)));
		}
	}
	
	private static int[][] sort(int[][] xian) {
		for(int i = 0; i < xian.length-1; i++){
			for(int j = 0; j < xian.length-1; j++){
				if(xian[j][0] > xian[j+1][0]){
					int[] d = xian[j];
					xian[j] = xian[j+1];
					xian[j+1] = d;
				}
			}
		}
		return xian;
	}

	private static int sum(int[][] xian) {
		ArrayList<Integer> start = new ArrayList<Integer>();
		ArrayList<Integer> end = new ArrayList<Integer>();
		start.add(xian[0][0]);
		end.add(xian[0][1]);
		for(int i = 1; i < xian.length; i++){
			if(xian[i][0] > end.get(end.size()-1)){
				start.add(xian[i][0]);
				end.add(xian[i][1]);
			}else if(xian[i][0] <= end.get(end.size()-1)){
				if(xian[i][1] > end.get(end.size()-1)){
					end.set(end.size()-1, xian[i][1]);
				}
			}
		}
		int sum = 0;
		for(int i = 0; i < start.size(); i++){
			sum += end.get(i) - start.get(i);
		}
		return sum;
	}
}