package reactor.features;

import lombok.SneakyThrows;
import org.reactivestreams.Subscription;
import reactor.core.Exceptions;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;
import reactor.util.retry.Retry;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Description reactor.core.publisher.Flux 入门实例
 * @Since 2024-11-06
 * @Copyright ©OPPEIN HOME GROUP INC.All Rights Reserved
 */
public class FluxTest {

    @SneakyThrows
    void main() {

        //Fixed value
        Flux<String> sequence1 = Flux.just("foo", "bar", "foobar");
        List<String> iterable = Arrays.asList("foo", "bar", "foobar");
        Flux<String> sequence2 = Flux.fromIterable(iterable);

        sequence2.subscribe(System.out::println);
        Mono<String> noData = Mono.empty();
        Mono<String> data = Mono.just("foo");
        //Range value
        Flux<Integer> numbersFromFiveToSeven = Flux.range(5, 3);
        numbersFromFiveToSeven.subscribe(System.out::println);

        //Exceptions
        Flux<Integer> inst1 = Flux.range(1, 4).map(i -> {
            if (i <= 3) return i;
            throw new RuntimeException("Got to 4");
        });
        inst1.subscribe(i -> System.out.print(STR."i=\{i} "), err -> System.err.println(STR."Error \{err}"));

        //Completion events
        Flux<Integer> inst2 = Flux.range(1, 4);
        inst2.subscribe(i -> System.out.println(i), error -> System.err.println(STR."Error\{error}"), () -> System.out.println("Done"));


        SampleSubscriber<Integer> ss = new SampleSubscriber<Integer>();
        Flux.range(100, 3).subscribe(ss);


        Flux.range(200, 10).doOnRequest(r -> System.out.println("request of " + r)).subscribe(new BaseSubscriber<Integer>() {

            @Override
            public void hookOnSubscribe(Subscription subscription) {
                request(1);
            }

            @Override
            public void hookOnNext(Integer integer) {
                System.out.println(STR."Cancelling after having received \{integer}");
                cancel();
            }
        });

        //Example of state-based generate
        Flux<String> flux = Flux.generate(() -> 0, (state, sink) -> {
            sink.next(STR."3 x \{state} = \{3 * state}");
            if (state == 10) sink.complete();
            return state + 1;
        });
        //Mutable state variant
        Flux<String> fluxMult = Flux.generate(AtomicLong::new, (state, sink) -> {
            long i = state.getAndIncrement();
            sink.next(STR."3 x \{i} = \{3 * i}");
            if (i == 10) sink.complete();
            return state;
        }, (state) -> System.out.println(STR."state: \{state}"));

        fluxMult.subscribe(i -> System.out.println(i));

        //You can use create to bridge this into a Flux<T>
       /*
        Flux<String> bridge = Flux.create(sink -> myEventProcessor.register(
                new MyEventListener<String>() {

                    public void onDataChunk(List<String> chunk) {
                        for(String s : chunk) {
                            sink.next(s);
                        }
                    }

                    public void processComplete() {
                        sink.complete();
                    }
                }));

        Flux<String> bridge1 = Flux.push(sink -> {
            myEventProcessor.register(
                    new SingleThreadEventListener<String>() {
                        public void onDataChunk(List<String> chunk) {
                            for(String s : chunk) {
                                sink.next(s);
                            }
                        }

                        public void processComplete() {
                            sink.complete();
                        }

                        public void processError(Throwable e) {
                            sink.error(e);
                        }
                    });
        });
        */

        //Using handle for a "map and eliminate nulls" scenario
        Flux<String> alphabet = Flux.just(-1, 30, 13, 9, 20).handle(
                (i, sink) -> {
                    String letter = alphabet(i);
                    if (letter != null) sink.next(letter);
                });

        alphabet.subscribe(System.out::println);

        // runs a Mono in a new thread:
        final Mono<String> mono = Mono.just("hello ");

        Thread t = new Thread(() -> mono
                .map(msg -> msg + "thread ")
                .subscribe(v ->
                        System.out.println(v + Thread.currentThread().getName())
                )
        );
        t.start();

        System.out.println("The following example uses the publishOn method:");

        Scheduler s = Schedulers.newParallel("parallel-scheduler", 4);

        final Flux<String> fluxs = Flux
                .range(1, 2)
                .map(i -> 10 + i)
                .publishOn(s)
                .map(i -> "value " + i);

        new Thread(() -> fluxs.subscribe(i -> System.out.println(i)));

        System.out.println("The following example uses the subscribeOn method:");
        Scheduler s1 = Schedulers.newParallel("parallel-scheduler", 4);

        final Flux<String> fluxs1 = Flux
                .range(1, 2)
                .map(i -> 10 + i)
                .subscribeOn(s1)
                .map(i -> "value " + i);

        new Thread(() -> fluxs1.subscribe(System.out::println)).start();


        System.out.println("Getting to the same behavior involves a few additional tricks:");

        AtomicInteger errorCount = new AtomicInteger();
        Flux.<String>error(new IllegalArgumentException())
                .doOnError(e -> errorCount.incrementAndGet())
                .retryWhen(Retry.from(companion ->
                        companion.map(rs -> {
                            if (rs.totalRetries() < 3) return rs.totalRetries();
                            else throw Exceptions.propagate(rs.failure());
                        })
                ));

     /*    errorCount = new AtomicInteger();
        Flux<Integer> transientFlux = httpRequest.get()
                .doOnError(e -> errorCount.incrementAndGet());

        transientFlux.retryWhen(Retry.max(2).transientErrors(true))
                .blockLast();
        assertThat(errorCount).hasValue(6);*/


    }

    public static String alphabet(int letterNumber) {
        if (letterNumber < 1 || letterNumber > 26) {
            return null;
        }
        int letterIndexAscii = 'A' + letterNumber - 1;
        return "" + (char) letterIndexAscii;
    }
}


class SampleSubscriber<T> extends BaseSubscriber<T> {

    @Override
    public void hookOnSubscribe(Subscription subscription) {
        System.out.println("Subscribed");
        request(1);
    }

    @Override
    public void hookOnNext(T value) {
        System.out.println(value);
        request(1);
    }
}
