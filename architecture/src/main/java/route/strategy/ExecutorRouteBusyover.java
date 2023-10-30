package route.strategy;

import cn.hutool.http.HttpUtil;
import org.apache.commons.lang3.StringUtils;
import route.ExecutorRouter;

import java.util.List;

/**
 * Created by xuxueli on 17/3/10.
 */
public class ExecutorRouteBusyover extends ExecutorRouter {

    @Override
    public String route(String triggerParam, List<String> addressList) {
        StringBuffer idleBeatResultSB = new StringBuffer();
        String       idleBeatResult   = "FAIL_CODE";
        for (String address : addressList) {
            // beat
            try {
                idleBeatResult = HttpUtil.post("xxx", address,3);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }

            // beat success
            if (StringUtils.equals(idleBeatResult, "")) {
                return address;
            }
        }

        return idleBeatResult;
    }

}
