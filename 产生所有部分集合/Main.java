import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while(sc.hasNext()){
			int n = sc.nextInt();
			int[] objects = new int[n];
			for(int i = 0; i < n; i++){
				objects[i] = sc.nextInt();
			}
			for(int i = 0; i < Math.pow(2, objects.length) - 1; i++){
				System.out.println(subset(i, objects));
			}
		}
	}

	private static String subset(int i, int[] objects) {
		String result = "";
		for(int j = 0; j < objects.length; j++){
			if(i % 2 == 1){
				result += objects[j] + " ";
			}
			i = i >> 1;
		}
		return result;
	}
}