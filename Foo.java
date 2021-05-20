import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class Foo {
    public void first() {
        System.out.println("first");
    }

    public void second() {
        System.out.println("second");
    }

    public void third() {
        System.out.println("third");
    }


}

class FooDemo {
    public void fooDemo() {
        Foo foo = new Foo();
        CompletableFuture<Void> cf = CompletableFuture.runAsync(() -> foo.first())
                .thenRunAsync(() -> foo.second())
                .thenRunAsync(() -> foo.third());
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
