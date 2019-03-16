/**
 * https://www.nowcoder.com/questionTerminal/28c1dc06bc9b4afd957b01acdf046e69
 */
import java.util.*;

public class Main {
	public static int[][] map;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while(sc.hasNext()){
			String s = sc.nextLine();
			String r = ""; //反转
			for(int i = s.length()-1; i >= 0; i--){
				r += s.substring(i,	i+1);
			}
//			用这个也可以
//			String r = new StringBuilder(s).reverse().toString();
			map = new int[s.length()+1][s.length()+1];
			System.out.println(s.length() - lcs(s, r));
		}
	}
	private static int lcs(String s, String r) {
		for(int i = 1; i <= s.length(); i++){
			for(int j = 1; j <= r.length(); j++){
				if(s.charAt(i-1) == r.charAt(j-1)){
					map[i][j] = map[i-1][j-1] + 1;
				}else{
					map[i][j] = Math.max(map[i-1][j], map[i][j-1]);
				}
			}
		}
		return map[s.length()][r.length()];
	}
}