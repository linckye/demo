package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author linckye
 */
public class JdkProxy {

    public interface Iface {
        void test();
    }

    public static class IfaceImpl implements Iface {

        @Override
        public void test() {
            System.out.println("test");
        }

    }

    public static class CostProxyHandler implements InvocationHandler {

        private Iface iface;

        public CostProxyHandler(Iface iface) {
            this.iface = iface;
        }

        public static Iface getProxy(Iface iface) {
            return (Iface) Proxy.newProxyInstance(iface.getClass().getClassLoader(), iface.getClass().getInterfaces(), new CostProxyHandler(iface));
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            long start = System.currentTimeMillis();
            Object result = method.invoke(iface, args);
            System.out.println(System.currentTimeMillis() - start);
            return result;
        }

    }

    public static void main(String[] args) {
        Iface proxy = CostProxyHandler.getProxy(new IfaceImpl());
        proxy.test();
    }

}
