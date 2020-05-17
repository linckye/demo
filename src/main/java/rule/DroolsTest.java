package rule;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * @author linckye
 */
public class DroolsTest {

    public static void main(String[] args) throws Exception {
        KieServices services = KieServices.Factory.get();
        KieContainer container = services.getKieClasspathContainer();

        KieSession session = container.newKieSession("demo-ksession-rules");

        Context context = new Context();
        context.getInputs().put("age", 11);

        long start = System.currentTimeMillis();

        session.insert(context);

        session.fireAllRules();

        System.out.println(System.currentTimeMillis() - start);

        System.out.println(context);
    }

}
