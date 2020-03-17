package com.smaple.realm;

import com.smaple.realm.Model.Cat;
import com.smaple.realm.Model.Dog;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        //assertEquals(4, 2 + 2);
        Dog dog = new Dog("DOG",15,null);
        Cat cat = new Cat("CAT",7,null);

        System.out.println(dog.getName()+""+dog.getAge());
        System.out.println(cat.getName()+""+cat.getAge());
    }
}