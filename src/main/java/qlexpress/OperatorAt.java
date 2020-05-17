package qlexpress;

import com.ql.util.express.ArraySwap;
import com.ql.util.express.InstructionSetContext;
import com.ql.util.express.OperateData;
import com.ql.util.express.instruction.OperateDataCacheManager;
import com.ql.util.express.instruction.op.OperatorBase;

import java.util.List;
import java.util.Map;

/**
 * @author linckye
 */
public class OperatorAt extends OperatorBase  {
    public OperatorAt(String aName) {
        this.name = aName;
    }
    public OperatorAt(String aAliasName, String aName, String aErrorInfo) {
        this.name = aName;
        this.aliasName = aAliasName;
        this.errorInfo = aErrorInfo;
    }

    public OperateData executeInner(InstructionSetContext context, ArraySwap list) throws Exception {
        OperateData p0 = list.get(0);
        if(p0 == null || p0.getObject(context) == null){
            throw new Exception("对象为null,不能执行相关操作");
        }
        Object tmpObject = p0.getObject(context);
        if (tmpObject.getClass().isArray()) {
            int index = ((Number)list.get(1).getObject(context)).intValue();
            OperateData result  = OperateDataCacheManager.fetchOperateDataArrayItem((OperateData)p0,index);
            return result;
        } else if (tmpObject instanceof List) {
            int index = ((Number)list.get(1).getObject(context)).intValue();
            OperateData result  = OperateDataCacheManager.fetchOperateDataArrayItem((OperateData)p0,index);
            return result;
        } else if (tmpObject instanceof Map) {
            Object key = list.get(1).getObject(context);
            Object value = ((Map) tmpObject).get(key);
            return new OperateData(value, value.getClass());
        }
        throw new Exception("对象:"+ tmpObject.getClass() +"不是数组,不能执行相关操作" );
    }
}
