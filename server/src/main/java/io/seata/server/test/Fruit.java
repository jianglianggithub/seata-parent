package io.seata.server.test;

import java.util.function.Function;

class Su { }

public class Fruit extends Su {

    public static void main(String[] args) {
        Wrapper<Fruit> wrapper  = new Wrapper<>();
        wrapper.set(new Apple());

        /**
         *  这里这个地方会报错 我猜想是因为
         *  这里指定了<? extends Fruit> 那么我只是知道你放的东西是 继承于 Fruit 的 但是为什么 set失败了呢
         *  因为 你放任何类型 可能你真实的类型是 你放的类型的子类  子类无法接收父类的。
         */
        Wrapper<? extends Fruit> wrapper1  = new Wrapper<Apple>();
//        wrapper1.set(new Fruit());
//        wrapper1.set(new Apple());
        wrapper.func(new Function<Su, Object>() {
            @Override
            public Object apply(Su fruit) {
                return null;
            }
        });
    }
}

class Wrapper <T> {
    T item;

    public void set(T t) {
        this.item = t;
    }
    public T get() {
        return item;
    }

    public void func(Function<? super T, ?> f) {
        f.apply(item);
    }
}

class Apple extends Fruit {

}