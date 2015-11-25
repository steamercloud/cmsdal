package com.oneops.cms.dj.dal;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.oneops.cms.dj.domain.CmsDeployment;
import com.oneops.cms.dj.domain.CmsDpmtApproval;
import com.oneops.cms.dj.domain.CmsDpmtRecord;
import com.oneops.cms.dj.domain.CmsDpmtStateChangeEvent;
import com.oneops.cms.dj.domain.CmsRfcCI;
import com.oneops.cms.dj.domain.CmsWorkOrder;

/**
 * The Interface DJDpmtMapper.
 */
public interface DJDpmtMapper {
	
	void createDeployment(CmsDeployment cmsDeployment);
	void createDpmtApproval(CmsDpmtApproval cmsDpmtApproval);
	void dpmtApprove(CmsDpmtApproval cmsDpmtApproval);
	void updDpmtApproval(CmsDpmtApproval cmsDpmtApproval);
	void retryDeployment(CmsDeployment cmsDeployment);
	void cancelDeployment(CmsDeployment cmsDeployment);
	void completeDeployment(CmsDeployment cmsDeployment);
	void updDeployment(CmsDeployment cmsDeployment);
	void updDpmtRecordState(CmsDpmtRecord cmsDpmtRecord);
	void resetFailedRecords(CmsDeployment cmsDeployment);

	CmsDeployment getDeployment(long deploymentId);
	List<CmsDeployment> findDeployment(@Param("nsPath") String nsPath,@Param("state") String state);
	List<CmsDeployment> findDeploymentRecursive(@Param("ns") String ns, @Param("nsLike") String nsLike, @Param("state") String state);
	List<CmsDeployment> findLatestDeployment(@Param("nsPath") String nsPath,@Param("state") String state);
	List<CmsDeployment> findLatestDeploymentRecursive(@Param("ns") String ns, @Param("nsLike") String nsLike, @Param("state") String state);
	List<CmsDeployment> findDeploymentByReleaseId(@Param("releaseId") long releaseId, @Param("state") String state);
	List<CmsDeployment> findLatestDeploymentByReleaseId(@Param("releaseId") long releaseId, @Param("state") String state);
	long countDeployment(@Param("nsPath") String nsPath,@Param("state") String state);
	long countDeploymentRecursive(@Param("ns") String ns, @Param("nsLike") String nsLike, @Param("state") String state);
	List<Map<String,Object>> countDeploymentGroupByNs(@Param("ns") String ns, @Param("nsLike") String nsLike, @Param("state") String state);
	
	List<CmsDpmtRecord> getDeploymentRecords(long deploymentId);	
	CmsDpmtRecord getDeploymentRecord(long dpmtRfcId);
	List<CmsDpmtRecord> getDeploymentRecordCis(long deploymentId);	
	List<CmsDpmtRecord> getDeploymentRecordRelations(long deploymentId);
	List<CmsDpmtRecord> getDeploymentRecordsByState(@Param("deploymentId") long deploymentId, @Param("state") String state, @Param("execOrder") Integer execOrder);
	long getDeploymentRecordsCountByState(@Param("deploymentId") long deploymentId, @Param("state") String state, @Param("execOrder") Integer execOrder);
	List<CmsDpmtRecord> getDeploymentRecordsByCiId(@Param("ciId") long ciId, @Param("state") String state);
	List<CmsWorkOrder> getWorkOrders(@Param("deploymentId") long deploymentId, @Param("state") String state, @Param("execOrder") Integer execOrder);
	List<CmsWorkOrder> getWorkOrdersLimited(@Param("deploymentId") long deploymentId, @Param("state") String state, @Param("execOrder") Integer execOrder, @Param("limit") Integer limit);
	CmsWorkOrder getWorkOrder(@Param("dpmtRecordId") long dpmtRecordId, @Param("state") String state, @Param("execOrder") Integer execOrder);
	
	List<CmsRfcCI> getDeploymentRfcCIs(@Param("deploymentId") long deploymentId, @Param("state") String state, @Param("execOrder") Integer execOrder);
	
	List<CmsDpmtStateChangeEvent> getDeploymentStateHist(long deploymentId);
	
	List<Long> getToCiIdsForDeployment(@Param("deploymentId") long deploymentId, @Param("state") String state, @Param("relName") String relName);
	List<Long> getToCiIdsForRelease(@Param("releaseId") long deploymentId, @Param("relName") String relName);
	List<Long> getToCiIdsForReleasePending(@Param("releaseId") long deploymentId, @Param("relName") String relName);
	
	List<CmsDpmtApproval> getDpmtApprovals(long deploymentId);
	CmsDpmtApproval getDpmtApproval(long approvalId);
}