package qlexpress;

import com.ql.util.express.ArraySwap;
import com.ql.util.express.InstructionSetContext;
import com.ql.util.express.OperateData;
import com.ql.util.express.instruction.op.OperatorBase;

import java.util.Collection;
import java.util.Map;

/**
 * @author linckye
 */
public class OperatorIsEmpty extends OperatorBase {

    public OperatorIsEmpty(String name) {
        this.name = name;
    }

    @Override
    public OperateData executeInner(InstructionSetContext parent, ArraySwap list) throws Exception {
        OperateData data = list.get(0);
        Object arg = data.getObject(parent);
        boolean result = false;
        if (arg == null) {
            result = true;
        } else if (arg instanceof String) {
            result = ((String) arg).isEmpty();
        } else if (arg instanceof Collection) {
            result = ((Collection) arg).isEmpty();
        } else if (arg instanceof Map) {
            result = ((Map) arg).isEmpty();
        } else if (arg instanceof Iterable) {
            result = !((Iterable) arg).iterator().hasNext();
        } else if (arg.getClass().isArray()) {
            result = ((Object[]) arg).length == 0;
        } else {
            throw new UnsupportedOperationException();
        }
        return new OperateData(result, Boolean.class);
    }

}
