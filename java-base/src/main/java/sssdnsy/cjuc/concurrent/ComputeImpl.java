package sssdnsy.cjuc.concurrent;

/**
 * @Auther: imi
 * @Date: 2019/4/3 11:05
 * @Description:
 */
public class ComputeImpl implements Compute<String,Integer> {

//    public Integer compute(String id) throws Exception {
//        Integer  val = cache.get(id);
//        if(val == null){
//            val =doCompute(id);
//            cache.put(id,val);
//        }
//        return val;
//    }
    public Integer compute(String id){
        return  new Integer(id);
    }

    public static void main(String[] args) throws Exception {
        CoumputeMemory<String,Integer> cm = new CoumputeMemory(new ComputeImpl());
        cm.compute("1");
    }
}
