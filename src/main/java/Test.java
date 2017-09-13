
public class Test {
	public static void main(String[] args) {
//		double a=(double)(4.0/20.0)*(double)(7.0/10.0)*(double)(2.0/5.0);
//		double b=(double)(6.0/20.0)*(double)(2.0/10.0)*(double)(1.0/5.0);
//		double c=(double)(10.0/20.0)*(double)(1.0/10.0)*(double)(2.0/5.0);
//		System.out.println(a);
//		System.out.println(b);
//		System.out.println(c);
//		String line="0,abcde";
//		System.out.println(line.substring(0, 1));
//		System.out.println(line.substring(2));
		String s="我的";
		 String regex = "^[\u4e00-\u9fa5]+$";
		 System.out.println(s.matches(regex));
	}
}
