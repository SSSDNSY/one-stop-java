package sssdnsy.cjuc.concurrent;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Auther: imi
 * @Date: 2019/4/3 11:28
 * @Description:
 */
public class CoumputeMemory<A,V> implements Compute<A,V> {

    private final ConcurrentHashMap<A,V> cache = new ConcurrentHashMap<A, V>();
    private final Compute<A,V> computeImpl;

    public CoumputeMemory(Compute<A,V> computeImpl){
        this.computeImpl = computeImpl;
    }

    public V compute(A id) throws Exception {
        V result = cache.get(id);
        if(result ==null){
            result = computeImpl.compute(id);
            cache.put(id,result);
        }
        return  result;
    }
}
