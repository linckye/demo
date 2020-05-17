package akka;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author linckye
 */
@Data
@Accessors(chain = true)
public class TaskMessage {
    int from;
    int to;
}
