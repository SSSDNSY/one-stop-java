import java.util.Date;

public class FIbonacci {
    public static int  feb(int i){
        if(i==0){
            return 0;
        }else if(i==1){
            return 1;
        }else{
            return feb(i-1)+feb(i-2);
        }
    }
    public static void main(String[] args) {
        long t1 = System.currentTimeMillis();
//        for(int i=0;i<40;i++){
//            System.out.print(feb(i)+"\t");
//        }
        System.out.println("".substring(0,3));
        System.out.println("耗时="+(System.currentTimeMillis()-t1)+"ms");
    }
}
