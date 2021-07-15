//package net.heartbeat;
//
//import java.io.Serializable;
//import java.util.HashMap;
//
///**
// * @Description: 心跳监控数据包
// * @Author: pengzh
// * @date: 2019/10/21
// * @ERROR 没有实现序列化 搞了几天 你是有多菜！
// */
//public class HeartBeatInfo implements Serializable {
//
//    private String ip;
//    private HashMap<String, Object> cpuMap;
//    private HashMap<String, Object> memMap;
//
//    public String getIp() {
//        return ip;
//    }
//
//    public void setIp(String ip) {
//        this.ip = ip;
//    }
//
//    public HashMap<String, Object> getCpuMap() {
//        return cpuMap;
//    }
//
//    public void setCpuMap(HashMap<String, Object> cpuMap) {
//        this.cpuMap = cpuMap;
//    }
//
//    public HashMap<String, Object> getMemMap() {
//        return memMap;
//    }
//
//    public void setMemMap(HashMap<String, Object> memMap) {
//        this.memMap = memMap;
//    }
//
//    @Override
//    public String toString() {
//        return "客户端心跳包 {" +
//                "ip='" + ip + '\'' +
//                ", cpuMap=" + cpuMap +
//                ", memMap=" + memMap +
//                '}';
//    }
//}
