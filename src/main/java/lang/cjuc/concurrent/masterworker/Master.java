package lang.cjuc.concurrent.masterworker;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Master {

    //任务集合
    private ConcurrentLinkedQueue<Task> workQueue = new ConcurrentLinkedQueue<>();

    //workers对象集合
    private HashMap<String, Thread> workers = new HashMap<String, Thread>();

    //任务结果
    private ConcurrentHashMap<String, Object> resultMap = new ConcurrentHashMap();

    public Master(Worker worker, int workerCount) {
        worker.setWorkQueue(this.workQueue);
        worker.setResultMap(this.resultMap);
        for (int i = 0; i < workerCount; i++) {
            workers.put("子节点" + i , new Thread(worker));
        }
    }

    public void submit(Task task){
        this.workQueue.add(task);
    }
    public void excute(){
        for(Map.Entry<String,Thread> w: workers.entrySet()){
            w.getValue().start();
        }
    }
    public boolean isComplete(){
        for(Map.Entry<String,Thread> w: workers.entrySet()){
            if(w.getValue().getState()!=Thread.State.TERMINATED){
                return false;
            }
        }
        return true;
    }
    public void stop(){

    }

    public float getRusult() {
        float ret = 0;
        for (Map.Entry<String,Object> rlt : resultMap.entrySet()){
                ret += (float)rlt.getValue();
        }
        return ret;
    }
}
