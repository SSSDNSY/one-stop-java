package akka;

import akka.actor.*;
import akka.pattern.Patterns;
import com.google.common.collect.Maps;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import lombok.extern.slf4j.Slf4j;

import java.net.URL;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.CompletionStage;

/**
 * @Desc

 * @Since 2023-11-30
 */
@Slf4j
public class AkkaHello {

    private ActorSystem actorSystem;

    public static void main(String[] args) {
        AkkaHello akkaHello = new AkkaHello();
        akkaHello.init();

        akkaHello.ask(new Message("1"), Message.class);
    }

    public void init() {

        // 初始化 ActorSystem（macOS上 new ServerSocket 检测端口占用的方法并不生效，可能是AKKA是Scala写的缘故？没办法...只能靠异常重试了）
        Map<String, Object> overrideConfig = Maps.newHashMap();
        overrideConfig.put("akka.remote.artery.canonical.hostname", "127.0.0.1");
        overrideConfig.put("akka.remote.artery.canonical.port", "10088");

        Config akkaBasicConfig = ConfigFactory.load("akka.conf");
        Config akkaFinalConfig = ConfigFactory.parseMap(overrideConfig).withFallback(akkaBasicConfig);

        log.info("[AKKA] try to start AKKA System.");

        // 启动时绑定当前的 actorSystemName
        this.actorSystem = ActorSystem.create("AKKA_SERVER", akkaFinalConfig);

        // 处理系统中产生的异常情况
        ActorRef troubleshootingActor = actorSystem.actorOf(Props.create(AkkaTroubleshootingActor.class), "troubleshooting");
        actorSystem.eventStream().subscribe(troubleshootingActor, DeadLetter.class);

        log.info("[AKKA] initialize actorSystem[{}] successfully!", actorSystem.name());
    }

    public void tell(URL url, Serializable request) {
        ActorSelection actorSelection = fetchActorSelection();
        actorSelection.tell(request, null);
    }

    @SuppressWarnings("unchecked")
    public <T> CompletionStage<T> ask(Serializable request, Class<T> clz) throws RuntimeException {
        ActorSelection actorSelection = fetchActorSelection();
        return (CompletionStage<T>) Patterns.ask(actorSelection, request, Duration.ofMillis(5000L));
    }

    private ActorSelection fetchActorSelection() {

        return actorSystem.actorSelection(String.format("akka://%s@%s/user/%s", "AKKA_SERVER", "127.0.0.1", "server_actor"));
    }

}
