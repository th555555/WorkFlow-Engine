package net.risesoft.listener;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.NameValuePair;
import org.flowable.engine.TaskService;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.ExecutionListener;
import org.flowable.task.api.Task;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

import net.risesoft.pojo.Y9Result;
import net.risesoft.y9.FlowableTenantInfoHolder;
import net.risesoft.y9.Y9Context;
import net.risesoft.y9.util.RemoteCallUtil;

/**
 * 提取
 *
 * @author qinman
 * @date 2024/03/19
 */
@Slf4j
public class AlgorithmListener implements ExecutionListener {

    /**
     *
     */
    private static final long serialVersionUID = -7305379571931231426L;

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void notify(DelegateExecution execution) {
        try {
            String requestUrl =
                Y9Context.getProperty("y9.app.processAdmin.imageCountUrl") + "/service/rest/imageCount/monoLocate";
            String tenantId = FlowableTenantInfoHolder.getTenantId();
            String processSerialNumber = execution.getVariable("processSerialNumber").toString();
            String processInstanceId = execution.getProcessInstanceId();
            TaskService taskService = Y9Context.getBean(TaskService.class);
            List<Task> tasks = taskService.createTaskQuery().processInstanceId(processInstanceId).list();
            String orgUnitId = tasks.get(0).getAssignee();
            List<NameValuePair> params = new ArrayList<>();
            params.add(new NameValuePair("tenantId", tenantId));
            params.add(new NameValuePair("positionId", orgUnitId));
            params.add(new NameValuePair("processSerialNumber", processSerialNumber));
            Y9Result<Boolean> y9Result = RemoteCallUtil.postCallRemoteService(requestUrl, params, Y9Result.class);
            LOGGER.error("调用计算接口返回信息：" + y9Result.getMsg());
            if (!y9Result.isSuccess()) {
                throw new RuntimeException("调用计算接口失败：" + y9Result.getMsg());
            }
        } catch (Exception e) {
            throw new RuntimeException("调用计算接口异常");
        }
    }
}
