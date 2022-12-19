package sssdnsy.heartbeat;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;
import org.apache.zookeeper.data.Stat;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: pengzh
 * @date: 2019/10/21
 */
public class ClientHeartBeatHandler extends ChannelHandlerAdapter {
    private InetAddress inetAddress;
    private ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
    private ScheduledFuture<?> heartBeat;
    private static final String SECCESS_KEY = "AUTH_SECCESS";

    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        inetAddress = InetAddress.getLocalHost();
        String ip = inetAddress.getHostAddress();
        String key = "12345";
        ctx.writeAndFlush(ip + "," + key);
    }

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            if (msg instanceof String) {
                String ret = (String) msg;
                if (SECCESS_KEY.equals(ret)) {
//                    握手验证成功，主动去发送心跳消息
                    this.heartBeat = this.scheduledExecutorService.scheduleWithFixedDelay(
                            new HeartbeatTask(ctx), 0, 2, TimeUnit.SECONDS);
                    System.out.println("客户端：" + msg);
                } else {
                    System.out.println("客户端：" + msg);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        if (heartBeat != null) {
            heartBeat.cancel(true);
            heartBeat = null;
        }
    }

    private class HeartbeatTask implements Runnable {
        private final ChannelHandlerContext ctx;

        public HeartbeatTask(final ChannelHandlerContext ctx) {
            this.ctx = ctx;
        }

        @Override
        public void run() {
            try {
                HeartBeatInfo heartBeatInfo = new HeartBeatInfo();
                HashMap cpuMap = new HashMap<String, Object>();
                HashMap memMap = new HashMap<String, Object>();
                Sigar sigar = new Sigar();

                CpuPerc cpuPerc = sigar.getCpuPerc();
                Mem mem = sigar.getMem();
                //CPU和内存
                cpuMap.put("Combined", cpuPerc.getCombined());
                cpuMap.put("Idle", cpuPerc.getIdle());
                cpuMap.put("User", cpuPerc.getUser());
                cpuMap.put("Sys", cpuPerc.getSys());
                cpuMap.put("Nice", cpuPerc.getNice());
                cpuMap.put("Wait", cpuPerc.getWait());
                memMap.put("Total", mem.getTotal() / 1024L);
                memMap.put("Free", mem.getFree() / 1024L);
                memMap.put("Used", mem.getUsed() / 1024L);
                memMap.put("Ram", mem.getRam() / 1024L);
                memMap.put("ActualFree", mem.getActualFree() / 1024L);
                memMap.put("ActualUsed", mem.getActualUsed() / 1024L);
//                System.out.println("客户端");
                heartBeatInfo.setIp(inetAddress.getHostAddress());
                heartBeatInfo.setCpuMap(cpuMap);
                heartBeatInfo.setMemMap(memMap);
                this.ctx.writeAndFlush(heartBeatInfo);
            } catch (SigarException e) {
                e.printStackTrace();
            }
        }
    }


}
