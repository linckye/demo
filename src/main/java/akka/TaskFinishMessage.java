package akka;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author linckye
 */
@Data
@Accessors(chain = true)
public class TaskFinishMessage {
    int sum;
}
