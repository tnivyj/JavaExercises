import java.util.*;

public class Main {
	static Point[] p;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		p = new Point[n];
		for(int i = 0; i < n; i++){
			Point a = new Point();
			a.x = sc.nextInt();
			a.y = sc.nextInt();
			p[i] = a;
		}
		Arrays.sort(p); //排序
		System.out.println((int)ClosestPair(0, n-1));
	}

	private static double ClosestPair(int left, int right) {
		if(left + 1 == right){ //只有两个点，直接返回距离
			return Dis(p[left], p[right]);
		}
		int mid = (left + right) / 2; //获得中线
		double d1 = ClosestPair(left, mid); //递回计算左边的最短距离
		double d2 = ClosestPair(mid, right); //递回计算右边的最短距离
		double d = Math.min(d1, d2); //找到两边中的最短距离
		Point[] temp = new Point[p.length]; //缓存距离中线d的点，会存在空位
		int k = 0;
		for(int i = left; i <= right; i++){
			if(Math.abs(p[mid].x - p[i].x) <= d){
				temp[k++] = p[i];
			}
		}
		temp = sort(temp, k);
		for(int i = 0; i < k; i++){ //遍历中间部分的点
			for(int j = i+1; j < k && temp[j].y - temp[i].y < d; j++){
				double d3 = Dis(temp[i], temp[j]);
				d = Math.min(d, d3);
			}
		}
		return d;
	}

	private static Point[] sort(Point[] temp, int k) { //由于缓存的temp不确定长度，所以在这里换成确定长度的阵列仔进行排序
		Point[] s = new Point[k];
		for(int i = 0; i < k; i++){
			s[i] = temp[i];
		}
		Arrays.sort(s);
		return s;
	}

	private static double Dis(Point p1, Point p2) { //计算两点间距离
		return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
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