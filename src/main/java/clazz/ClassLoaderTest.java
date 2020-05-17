package clazz;

import com.google.common.collect.Lists;

import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ServiceLoader;

/**
 * @author linckye
 */
public class ClassLoaderTest {

    public static void main(String[] args) throws Exception {
        System.out.println(ClassLoaderTest.class.getClassLoader());
        System.out.println(ClassLoaderTest.class.getClassLoader().getParent());
        System.out.println(ClassLoaderTest.class.getClassLoader().getParent().getParent());

        ArrayList<Integer> list = Lists.newArrayList(1);
        Integer[] a = list.toArray(new Integer[0]);
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(list.stream().mapToInt(k -> k).toArray()));

        A a1 = new A();
        System.out.println(a1.hashCode());
        System.out.println(System.identityHashCode(a1));

//        ClassLoader1 classLoader1 = new ClassLoader1();
//        System.out.println(classLoader1.loadClass(java.lang.Object.class.getName()));
//        System.out.println(classLoader1.findClass(java.lang.Object.class.getName()));
//        System.out.println(classLoader1.loadClass(ClassLoaderTest.class.getName()));
        System.out.println(Thread.currentThread().getContextClassLoader());
    }

    public static class ClassLoader1 extends ClassLoader {
        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            try {
                InputStream stream = ClassLoaderTest.class.getResourceAsStream(name);
                byte[] bytes = new byte[stream.available()];
                Class<?> aClass = defineClass(name, bytes, 0, bytes.length);
                return aClass;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

}
