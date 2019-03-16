/**
 * https://www.nowcoder.com/questionTerminal/7e8aa3f9873046d08899e0b44dac5e43
 */
import java.util.*;

public class Main {
	public static int[][] map;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while(sc.hasNext()){
			String s = sc.nextLine();
			System.out.println(upperRight(s));
		}
	}
	private static String upperRight(String s) {
		char[] c = s.toCharArray();
		int count = 0;
		for(int i = 0; i < s.length(); i++){
			if(Character.isLowerCase(c[i])){
				char temp = c[i];
				for(int j = i; j > count; j--){
					c[j] = c[j-1];
				}
				c[count] = temp;
				count++;
			}
		}
		return String.copyValueOf(c);
	}
}