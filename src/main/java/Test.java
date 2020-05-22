import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Test {
    public int greatnum(int a[][]){
        int n=a.length;
        int m=a[0].length;
        int max[]=new int[m];
        for(int j=0;j<m;j++){
            max[j]=a[0][j];
            for(int i=0;i<n;i++){
                if(a[i][j]>a[0][j]) max[j]=a[0][j];

            }
        }
        Set<Integer> set=new HashSet<>();

        for(int i=0;i<m;i++){
            set.add(max[i]);

        }
        int num=set.size();
        return  num;
    }

    public static void main(String args[]){
        Scanner scanner1=new Scanner(System.in);
        int n=scanner1.nextInt();
        Scanner scanner2=new Scanner(System.in);
        int m=scanner2.nextInt();
        int[][] a=new int[n-1][m-1];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                Scanner sc=new Scanner(System.in);
                a[i][j]=sc.nextInt();
            }
        }
        Test test=new Test();
        int result= test.greatnum(a);
        System.out.println(result);
    }
}
