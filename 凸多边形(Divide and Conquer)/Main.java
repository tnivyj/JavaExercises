import java.math.BigDecimal;
import java.util.*;

public class Main {
	static Point[] p;
	static ArrayList<Point> result;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt(); //总共n个坐标
		p = new Point[n];
		for(int i = 0; i < n; i++){ //输入坐标
			Point a = new Point();
			a.x = sc.nextInt();
			a.y = sc.nextInt();
			p[i] = a;
		}
		Arrays.sort(p); //按照x，y轴排序排序
		result = new ArrayList<>();
		result.add(p[0]);
		result.add(p[p.length-1]); //先把最左和最右的两点存入
		ArrayList<Point> S1 = new ArrayList<>(); //上
		ArrayList<Point> S2 = new ArrayList<>(); //下
		for(int i = 1; i < p.length-1; i++){
			if(new BigDecimal(p[i].y).compareTo(computeY(p[0], p[p.length-1],p[i])) >= 0){
				S1.add(p[i]);
			}else{
				S2.add(p[i]);
			}
		}
		upMax(p[0], p[p.length-1], S1); //对上半部分进行操作
		downMax(p[0], p[p.length-1], S2); //对下半部分进行操作
		Collections.sort(result); //对答案进行排序
		for(int i = 0; i < result.size(); i++){ //output
			System.out.println(result.get(i).x + " " + result.get(i).y);
		}
	}
	private static void downMax(Point left, Point right, ArrayList<Point> s2) { //寻找下半部分的最大点
		if(s2.size() == 0){ //当集合为空时，直接返回
			return;
		}else if(s2.size() == 1){ //当集合仅剩一个元素时，存入答案并返回
			result.add(s2.get(0));
			return;
		}
		BigDecimal min = new BigDecimal(Integer.MAX_VALUE);
		Point MinP = new Point();
		BigDecimal D = computeD(left, right, left); //计算原本方程的截距
		for(int i = 0; i < s2.size(); i++){
			BigDecimal d = computeD(left, right, s2.get(i)); //计算原本方程平移经过该点时的截距
			if(d.compareTo(D) < 0 && d.compareTo(min) < 0){ //所得到的截距应比原本的小，并且小于当前最小截距
				min = d;
				MinP = s2.get(i); //记录该点
			}
		}
		result.add(MinP); //将该点存入答案
		downMax(left, MinP, findDownSet(left, MinP, s2)); //递回寻找到的点的左半段
		downMax(MinP, right, findDownSet(MinP, right, s2)); // 递回寻找到的点的右半段
	}
	private static ArrayList<Point> findDownSet(Point left, Point right, ArrayList<Point> s2) { //寻找小于直线的坐标集合
		ArrayList<Point> a = new ArrayList<>();
		for(int i = 0; i < s2.size(); i++){
			if(computeY(left, right, s2.get(i)) != null){
				if(new BigDecimal(s2.get(i).y).compareTo(computeY(left, right, s2.get(i))) < 0){
					if(s2.get(i) != left && s2.get(i) != right){
						a.add(s2.get(i));
					}
				}
			}
		}
		return a;
	}
	private static void upMax(Point left, Point right, ArrayList<Point> s1) {
		if(s1.size() == 0){
			return;
		}else if(s1.size() == 1){
			result.add(s1.get(0));
			return;
		}
		BigDecimal max = new BigDecimal(Integer.MIN_VALUE);
		Point MaxP = new Point();
		BigDecimal D = computeD(left, right, left);
		for(int i = 0; i < s1.size(); i++){
			BigDecimal d = computeD(left, right, s1.get(i));
			if(d.compareTo(D) > 0 && d.compareTo(max) > 0){
				max = d;
				MaxP = s1.get(i);
			}
		}
		result.add(MaxP);
		upMax(left, MaxP, findUpSet(left, MaxP, s1));
		upMax(MaxP, right, findUpSet(MaxP, right, s1));
	}
	private static ArrayList<Point> findUpSet(Point left, Point right, ArrayList<Point> s1) {
		ArrayList<Point> a = new ArrayList<>();
		for(int i = 0; i < s1.size(); i++){
			if(computeY(left, right, s1.get(i)) != null){
				if(new BigDecimal(s1.get(i).y).compareTo(computeY(left, right, s1.get(i))) > 0){
					if(s1.get(i) != left && s1.get(i) != right){
						a.add(s1.get(i));
					}
				}
			}
		}
		return a;
	}
	private static BigDecimal computeD(Point left, Point right, Point point) { //计算截距
		return new BigDecimal(point.y - ((1.0*(right.y - left.y))/(1.0*(right.x - left.x)) * point.x));
	}
	private static BigDecimal computeY(Point left, Point right, Point point) {
		if(right.x != left.x){
			return new BigDecimal(((1.0*(right.y - left.y))/(1.0*(right.x - left.x))) * (point.x - left.x) + left.y);
		}
		return null;
	}

}
class Point implements Comparable<Point>{ //继承一下这个类
	int x;
	int y;
	public int compareTo(Point p){ //写了这个就可以用Arrays.sort()
		if(this.x == p.x){
			if(this.y < p.y){
				return -1;
			}else if(this.y == p.y){
				return 0;
			}
		}else if(this.x < p.x){
			return -1;
		}else{
			return 1;
		}
		return 0;
	}
}