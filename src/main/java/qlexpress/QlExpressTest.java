package qlexpress;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;
import com.ql.util.express.Operator;
import org.apache.log4j.lf5.util.StreamUtils;
import rule.AviatorTest;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author linckye
 */
public class QlExpressTest {

    /*
策略未配置时是否推送 = false

推送配置表 =
{
    "idleSupplyCreateTaskStrategy": {
        "城市未配置时是否推送": false,
        "城市配置表": {
            "上海": {
                "站点是否全推": true,
                "站点配置列表": [
                    "站点-A",
                    "站点-b"
                ]
            }
        }
    }
}
     */

    public static void main(String[] args) throws Exception {
        InputStream stream = AviatorTest.class.getClassLoader().getResourceAsStream("a");
        String content = new String(StreamUtils.getBytes(stream));
        ExpressRunner runner = new ExpressRunner();

        runner.addFunction("isEmpty", new OperatorIsEmpty("isEmpty"));

//        runner.replaceOperator("ARRAY_CALL", new OperatorArrayAndMap("ARRAY_CALL"));
//        runner.replaceOperator("@", new OperatorAt("@"));

        DefaultContext<String, Object> context = new DefaultContext<String, Object>();

        Map<String, Object> map = Maps.newHashMap();
        map.put("策略未配置时是否推送", false);
        map.put("推送配置表", JSON.parseObject("{\n" +
                "    \"idleSupplyCreateTaskStrategy\": {\n" +
                "        \"城市未配置时是否推送\": false,\n" +
                "        \"城市配置表\": {\n" +
                "            \"上海\": {\n" +
                "                \"站点是否全推\": true,\n" +
                "                \"站点配置列表\": [\n" +
                "                    \"站点-A\",\n" +
                "                    \"站点-b\"\n" +
                "                ]\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}"));

        context.put("流程", "推送bos");
        context.put("策略", "idleSupplyCreateTaskStrategy");
        context.put("城市", "上海");
        context.put("站点", "站点-A");
        context.put("配置", map);

        //配置.get("推送配置表").get(策略)
        System.out.println();




        for (int i = 0; i < 5; i++) {
            Object r = runner.execute(content, context, null, true, true);
        }

        int size = 100;
        long start = System.currentTimeMillis();
        Object r = null;
        for (int i = 0; i < size; i++) {
            r = runner.execute(content, context, null, true, true);
        }
        long cost = System.currentTimeMillis() - start;
        long avgCost = cost / size;

        System.out.println("result: " + r);
        System.out.println("avgCost: " + avgCost);
    }

}
