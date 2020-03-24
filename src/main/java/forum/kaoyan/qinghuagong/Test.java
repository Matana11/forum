package forum.kaoyan.qinghuagong;

import java.util.HashSet;
import java.util.Set;

public class Test {
    public boolean checkStraightLine(int[][] coordinates) {
        int num=coordinates.length;
        int x1=coordinates[1][0]-coordinates[0][0];
        int y1=coordinates[1][1]-coordinates[0][1];
        for(int i=2;i<num;i++){
            int x2=coordinates[i][0]-coordinates[0][0];
            int y2=coordinates[i][1]-coordinates[0][1];
            if(x1*y2!=x2*y1){
                return false;
            }
        }
        return true;
    }

    public static void main(String args[]){
        int coordinates[][] = {{1,2},{2,3},{3,4},{4,5},{5,6},{6,7}};
        Test test=new Test();
        boolean result=test.checkStraightLine(coordinates);
        System.out.println(result);
    }
}
