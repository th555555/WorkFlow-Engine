package net.risesoft.api;

import java.util.List;

import org.flowable.engine.history.HistoricActivityInstance;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import net.risesoft.api.processadmin.HistoricActivityApi;
import net.risesoft.model.processadmin.HistoricActivityInstanceModel;
import net.risesoft.pojo.Y9Result;
import net.risesoft.service.CustomHistoricActivityService;
import net.risesoft.util.FlowableModelConvertUtil;
import net.risesoft.y9.FlowableTenantInfoHolder;

/**
 * 获取历史节点实例
 *
 * @author qinman
 * @author zhangchongjie
 * @date 2022/12/30
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/services/rest/historicActivity")
public class HistoricActivityApiImpl implements HistoricActivityApi {

    private final CustomHistoricActivityService customHistoricActivityService;

    /**
     * 根据流程实例获取历史节点实例
     *
     * @param tenantId 租户id
     * @param processInstanceId 流程实例id
     * @return {@code Y9Result<List<HistoricActivityInstanceModel>>}
     * @since 9.6.6
     */
    @Override
    public Y9Result<List<HistoricActivityInstanceModel>> getByProcessInstanceId(@RequestParam String tenantId,
        @RequestParam String processInstanceId) {
        FlowableTenantInfoHolder.setTenantId(tenantId);
        List<HistoricActivityInstance> list = customHistoricActivityService.listByProcessInstanceId(processInstanceId);
        return Y9Result.success(FlowableModelConvertUtil.historicActivityInstanceList2Model(list));
    }

    /**
     * 根据年份，流程实例获取历史节点实例
     *
     * @param tenantId 租户id
     * @param processInstanceId 流程实例id
     * @param year 年度
     * @return {@code Y9Result<List<HistoricActivityInstanceModel>>}
     * @since 9.6.6
     */
    @Override
    public Y9Result<List<HistoricActivityInstanceModel>> getByProcessInstanceIdAndYear(@RequestParam String tenantId,
        @RequestParam String processInstanceId, @RequestParam String year) {
        FlowableTenantInfoHolder.setTenantId(tenantId);
        List<HistoricActivityInstance> list =
            customHistoricActivityService.listByProcessInstanceIdAndYear(processInstanceId, year);
        return Y9Result.success(FlowableModelConvertUtil.historicActivityInstanceList2Model(list));
    }

    /**
     * 根据流程实例和执行id获取历史节点实例
     *
     * @param tenantId 租户id
     * @param processInstanceId 流程实例id
     * @param executionId 执行id
     * @return {@code Y9Result<List<HistoricActivityInstanceModel>>}
     * @since 9.6.6
     */
    @Override
    public Y9Result<List<HistoricActivityInstanceModel>> getTaskListByExecutionId(@RequestParam String tenantId,
        @RequestParam String processInstanceId, @RequestParam String executionId, String year) {
        FlowableTenantInfoHolder.setTenantId(tenantId);
        return customHistoricActivityService.getTaskListByExecutionId(processInstanceId, executionId, year);
    }

}
