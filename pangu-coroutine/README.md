# PanGu Coroutine

> combine reactor and coroutine easily

## There are 3 main methods.

* 1. CoroutineKit::elastic(executor: Executor, vararg suppliers: Supplier<T>): Flux<T>
* 2. CoroutineKit::parallelPublisher(executor: Executor, vararg suppliers: Supplier<T>): Flux<T>
* 3. CoroutineKit::elasticPublisher(executor: Executor, supplier: Supplier<T>): Mono<T>

```text
1.run one to many suppliers on target executor with coroutines and call blockLast()
2.run one to many suppliers on target executor with coroutines and return cooldown flux
3.run one supplier on target executor with coroutines and return cooldown mono
```

## usage

1.manage dependencies

```groovy
ext {
    kotlinVersion = '1.5.30'
    kotlinCoroutineVersion = '1.5.2'
    reactorVersion = '3.4.1'
    panguVersion = '0.0.1'
}

dependencyManagement {
    imports {
        mavenBom "org.jetbrains.kotlin:kotlin-bom:${kotlinVersion}"
        mavenBom "org.jetbrains.kotlinx:kotlinx-coroutines-bom:${kotlinCoroutineVersion}"
    }

    dependency "io.projectreactor:reactor-core:${reactorVersion}"
    dependency "com.yuebaix:pangu-coroutine:${panguVersion}"
}
```

2.import dependencies

```groovy
dependencies {
    implementation 'io.projectreactor:reactor-core'
    implementation 'com.yuebaix:pangu-coroutine'
}
```

3.call as coroutines

```java
class Test {
    private void checkCoroutine() {
        int size = 1000;
        List<Supplier<Integer>> list = IntStream.range(0, size).mapToObj(i -> new Supplier<Integer>() {
            @Override
            public Integer get() {
                return i;
            }
        }).collect(Collectors.toList());
        Supplier<Integer>[] suppliers = list.toArray(new Supplier[0]);

        Flux<Integer> flux = CoroutineKit.elastic(recallPool, suppliers);

        flux.map(i -> {
            System.out.println(i);
            return i;
        }).blockLast();
    }
}
```
