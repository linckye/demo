package rule;

import com.google.common.collect.Maps;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Map;

/**
 * @author linckye
 */
@Data
@Accessors(chain = true)
public class Context {
    private Map<String, Object> inputs = Maps.newHashMap();
    private Map<String, Object> outputs = Maps.newHashMap();
}
