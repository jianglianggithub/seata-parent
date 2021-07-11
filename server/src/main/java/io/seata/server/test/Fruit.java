package io.seata.server.test;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

class Su { }

public class Fruit extends Su {

    public static void main(String[] args) {
        Wrapper<Fruit> wrapper  = new Wrapper<>();
        wrapper.aaa(new Apple());

        /**
         *  这里这个地方会报错 我猜想是因为
         *  这里指定了<? extends Fruit> 那么我只是知道你放的东西是 继承于 Fruit 的 但是为什么 set失败了呢
         *  因为 你放任何类型 可能你真实的类型是 你放的类型的子类  子类无法接收父类的。
         */
//        Wrapper<Fruit> wrapper1  = new Wrapper<Apple>();
//        wrapper1.aaa(new Fruit());
//
        Wrapper<? extends Fruit> wrapper2  = new Wrapper<Apple>();

//
//        test(wrapper);
//        test(new Wrapper<Apple>(),new Fruit());
    }

    /**
     * 很显然 test 只是一个变量 真实是指向一个 同一种类型的对象的
     * 很多地方都会调用这个方法 如果编译器解决的话 你觉得 我怎么知道 你哪一次传递的是 T extends Fruit 哪一种子类型呢？ test.aaa 里面的 如果你 new 了一个 是具体的对象
     */
    public static <T> void test( Wrapper<T> a,T b) {
    }

    public void tes(){}
}

class Wrapper <T> {
    T item;

    public void aaa(T t) {
        this.item = t;
    }
    public T get() {
        return item;
    }

    public void func(Function<? super T, ?> f) {
        f.apply(item);
    }
}
class Banana extends Fruit {}
class Apple extends Fruit {

}