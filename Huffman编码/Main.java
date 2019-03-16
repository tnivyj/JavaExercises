/**
 * https://e-tutor.itsa.org.tw/e-Tutor/mod/programming/view.php?id=44619
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;

import javax.swing.SortOrder;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine(); // input String
		HashMap<String,Integer> map = new HashMap<String,Integer>(); // 频率
		ArrayList<String> shunxu = new ArrayList<String>();
		for(int i = 0; i < input.length(); i++){
			String c = String.valueOf(input.charAt(i));
			if(!map.containsKey(c)){
				map.put(c, 1);
				shunxu.add(c);
			}else{
				map.put(c, map.get(c)+1);
			}
		}
		ArrayList<Node> R = new ArrayList<Node>();
		for(int i = 0; i < shunxu.size(); i++){
			Node n = new Node();
			n.name = shunxu.get(i);
			n.val = map.get(shunxu.get(i));
			R.add(n);
		}
		ArrayList<Node> N = new ArrayList<Node>();
		while(!R.isEmpty() || N.size() != 1){
			Node s1 = selectMin(R, N); //子节点
			Node s2 = selectMin(R, N); //子节点
			Node n = new Node(); // 找出的最小值的父节点
			n.val = s1.val + s2.val;
			if(s1.index == -1 && s2.index == -1){ //两个最小值都从R中找到  *index用于判断是否从N中
				n.left = s1;
				n.right = s2;
				N.add(n);
			}else if(s1.index != -1 && s2.index != -1){ //两个最小值都从N中找到
				if(s2.index < s1.index){ //选择索引值在前面的作为左子树
					n.left = s2;
					n.right = s1;
				}else{
					n.left = s1;
					n.right = s2;
				}
				N.remove(Math.max(s1.index, s2.index));
				N.remove(Math.min(s1.index, s2.index));
				N.add(Math.min(s1.index, s2.index), n);
			}else{ //一个为R中取得，一个为N中取得
				if(s1.index != -1){ //s1从N中取出
					n.left = s1;
					n.right = s2;
					N.remove(s1.index);
					N.add(s1.index,n);
				}else{
					n.left = s2;
					n.right = s1;
					N.remove(s2.index);
					N.add(s2.index,n);
				}
			}
		}
		ArrayList<Node> leaves = new ArrayList<>();
		huffmanCode(N.get(0), "", 0 ,leaves);
		for(int i=0;i<leaves.size();i++){
			for(int j=i+1;j<leaves.size();j++){
				if(leaves.get(i).val>leaves.get(j).val){
					Node tmp = leaves.get(i);
					leaves.set(i, leaves.get(j));
					leaves.set(j, tmp);
				}
			}
		}
		for(Node i : leaves){
			System.out.println(i.code+" -> "+i.name);
		}
	}

	private static void huffmanCode(Node node, String code, int val, ArrayList<Node> leaves) {
		if(node.left==null&& node.right==null){
			node.code=code;
			node.val=val*1000-code.length();
			leaves.add(node);
			return;
		}
		huffmanCode(node.left, code+"0", (val<<1)+0, leaves);
		huffmanCode(node.right, code+"1", (val<<1)+1, leaves);
	}

	private static Node selectMin(ArrayList<Node> R, ArrayList<Node> N) {
		int min = Integer.MAX_VALUE;
		String minName = "";
		int num1 = -1;
		int num2 = -1;
		for(int i = 0; i < R.size(); i++){
			if(R.get(i).val < min){
				min = R.get(i).val;
				minName = R.get(i).name;
				num1 = i;
			}
		}
		for(int j = 0; j < N.size(); j++){
			if(N.get(j).flag == false && N.get(j).val < min){
				min = N.get(j).val;
				minName = N.get(j).name;
				num2 = j;
			}
		}
		Node child = new Node();
		if(num2 != -1){ //N
			num1 = -1;
			child.index = num2;
			N.get(num2).flag = true;
			child.left = N.get(num2).left; // 给定左右小孩，连成树
			child.right = N.get(num2).right;
		}
		if(num1 != -1){
			R.remove(num1);
		}
		child.name = minName;
		child.val = min;
		return child;
	}
}
class Node{
	int val;
	String name;
	boolean flag;
	int index;
	String code="";
	Node left;
	Node right;
	Node(){
		this.flag = false;
		this.index = -1;
	}
}