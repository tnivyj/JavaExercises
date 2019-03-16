/**
	https://e-tutor.itsa.org.tw/e-Tutor/mod/programming/view.php?id=29274
	先将输入的中序表达式转成后序，在进行计算，每一次计算时都将数字平方，输出数字无条件舍去小数点
**/
import java.math.BigDecimal;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
        	String[] input = sc.nextLine().trim().split(" +");
        	Stack<String> houxu = new Stack<String>();
        	Stack<String> fuhao = new Stack<String>();
        	HashMap<String,Integer> sign = new HashMap<String,Integer>();
        	sign.put("+", 1);
        	sign.put("-", 1);
        	sign.put("*", 2);
        	sign.put("/", 2);
        	for(int i = 0; i < input.length; i++){
        		if(!sign.containsKey(input[i]) && !input[i].equals("(") && !input[i].equals(")")){
        			houxu.push(input[i]);
        		}else if(input[i].equals("(")){
    				fuhao.push(input[i]);
    			}else if(input[i].equals(")")){
					while(true){
						String pop = fuhao.pop();
						if(pop.equals("(")){
							break;
						}else{
							houxu.push(pop);
						}
					}
				}else{
					while(true){
						if(fuhao.isEmpty()){
							fuhao.push(input[i]);
							break;
						}
						String pop = fuhao.peek();
						if(pop.equals("(") || sign.get(pop) < sign.get(input[i])){
							fuhao.push(input[i]);
							break;
						}
						houxu.push(fuhao.pop());
//						fuhao.push(input[i]);
					}
				}
        	}
        	while(fuhao.isEmpty() == false){
        		houxu.push(fuhao.pop());
        	}
//        	for(int i = 0; i < houxu.size(); i++){
//        		System.out.print(houxu.get(i) + " ");
//        	}
//        	System.out.println();
        	Stack<BigDecimal> compute = new Stack<BigDecimal>();
        	for(int i = 0; i < houxu.size(); i++){
        		if(!sign.containsKey(houxu.get(i))){
        			BigDecimal d = new BigDecimal(houxu.get(i));
        			compute.push(d);
        		}else{
        			BigDecimal x = compute.pop();
        			BigDecimal y = compute.pop();
        			BigDecimal xx = x.multiply(x);
        			BigDecimal yy = y.multiply(y);
//        			BigDecimal result;
        			switch(houxu.get(i)){
        			case "+":
        				compute.push(yy.add(xx));
        				break;
        			case "-":
        				compute.push(yy.subtract(xx));
        				break;
        			case "*":
        				compute.push(yy.multiply(xx));
        				break;
        			case "/":
        				compute.push(yy.divide(xx));
        				break;
        			default:
        				break;
        			}
        		}
        	}
        	System.out.println(compute.get(0).setScale(0, BigDecimal.ROUND_DOWN));
        }
    }
}