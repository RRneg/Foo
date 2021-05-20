package Foo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;


public class Foo {

    public void first(Runnable r) {
        System.out.println("first");
    }

    public void second(Runnable r) {
        System.out.println("second");
    }

    public void third(Runnable r) {
        System.out.println("third");
    }


}

public class FooDemo {
    public void fooDemo() {
        CompletableFuture<Void> cf = CompletableFuture.runAsync(() -> Foo::first)
                .thenRunAsync(() -> Foo::second)
                .thenRunAsync(() -> Foo::third);
        try {
            cf.join();
        } catch (CompletionException e) {
            System.out.println("Возникли трудности: " + e);
        }
    }
}


class FooMain {
    public static void main(String[] args) {
        FooDemo fD = new FooDemo();
        fD.fooDemo();
    }
}
