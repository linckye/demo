package rule;

import com.google.common.collect.Maps;
import com.google.common.io.Files;
import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.AviatorEvaluatorInstance;
import com.googlecode.aviator.Expression;
import com.googlecode.aviator.lexer.token.OperatorType;
import org.apache.log4j.lf5.util.StreamUtils;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;

/**
 * @author linckye
 */
public class AviatorTest {

    public static void main(String[] args) throws Exception {
        InputStream stream = AviatorTest.class.getClassLoader().getResourceAsStream("dsl");
        String content = new String(StreamUtils.getBytes(stream));
        AviatorEvaluatorInstance instance = AviatorEvaluator.getInstance();
        Expression expression = instance.compile(content);
        HashMap<String, Object> context = Maps.newHashMap();
        context.put("a", 1);
        context.put("b", 1);
        context.put("c", 1);

        long start = System.currentTimeMillis();
        Object result = expression.execute(context);
        long cost = System.currentTimeMillis() - start;
        System.out.println("result: " + result);
        System.out.println("cost: " + cost);
    }

}
