package com.gv.midway.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.gv.midway.pojo.activateDevice.request.ActivateDeviceRequest;
import com.gv.midway.pojo.activateDevice.response.ActivateDeviceResponse;
import com.gv.midway.pojo.callback.request.CallBackVerizonRequest;
import com.gv.midway.pojo.changeDeviceServicePlans.request.ChangeDeviceServicePlansRequest;
import com.gv.midway.pojo.changeDeviceServicePlans.response.ChangeDeviceServicePlansResponse;
import com.gv.midway.pojo.connectionInformation.ConnectionInformationMidwayResponse;
import com.gv.midway.pojo.connectionInformation.deviceSessionBeginEndInfo.response.SessionBeginEndResponse;
import com.gv.midway.pojo.connectionInformation.deviceStatus.response.ConnectionStatusResponse;
import com.gv.midway.pojo.connectionInformation.request.ConnectionInformationRequest;
import com.gv.midway.pojo.customFieldsDevice.request.CustomFieldsDeviceRequest;
import com.gv.midway.pojo.customFieldsDevice.response.CustomFieldsDeviceResponse;
import com.gv.midway.pojo.deactivateDevice.request.DeactivateDeviceRequest;
import com.gv.midway.pojo.deactivateDevice.response.DeactivateDeviceResponse;
import com.gv.midway.pojo.device.request.BulkDevices;
import com.gv.midway.pojo.device.request.SingleDevice;
import com.gv.midway.pojo.device.response.BatchDeviceResponse;
import com.gv.midway.pojo.device.response.UpdateDeviceResponse;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformationResponse;
import com.gv.midway.pojo.job.JobParameter;
import com.gv.midway.pojo.job.JobinitializedResponse;
import com.gv.midway.pojo.reActivateDevice.request.ReactivateDeviceRequest;
import com.gv.midway.pojo.reActivateDevice.response.ReactivateDeviceResponse;
import com.gv.midway.pojo.restoreDevice.request.RestoreDeviceRequest;
import com.gv.midway.pojo.restoreDevice.response.RestoreDeviceResponse;
import com.gv.midway.pojo.suspendDevice.request.SuspendDeviceRequest;
import com.gv.midway.pojo.suspendDevice.response.SuspendDeviceResponse;
import com.gv.midway.pojo.usageInformation.request.UsageInformationRequest;
import com.gv.midway.pojo.usageInformation.response.UsageInformationMidwayResponse;
import com.gv.midway.pojo.usageInformation.response.UsageInformationResponse;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@SuppressWarnings("all")
@Path("/v1")
@Api(value = "/v1", description = "MidWay API Integration")
public interface IAdaptaionLayerService {

	@PUT
	@Path("/cell/upload")
	@Produces("application/json")
	@ApiOperation(value = "Insert or Update Device Details")
	UpdateDeviceResponse updateDeviceDetails(SingleDevice device);

	@GET
	@Path("/cell/info/midway")
	@Produces("application/json")
	@ApiOperation(value = "Get Device Details from Midway DB")
	DeviceInformationResponse getDeviceInfoDB(
			@QueryParam("region") final String region,
			@QueryParam("timestamp") final String timestamp,
			@QueryParam("organization") final String organization,
			@QueryParam("transactionId") final String transactionId,
			@QueryParam("sourceName") final String sourceName,
			@QueryParam("applicationName") final String applicationName,
			@QueryParam("bsCarrier") final String bsCarrier,
			@QueryParam("netSuiteId") final Integer netSuiteId);

	@GET
	@Path("/cell/info/carrier")
	@Produces("application/json")
	@ApiOperation(value = "Get Device Details from Carrier")
	DeviceInformationResponse getDeviceInfoCarrier(
			@QueryParam("region") final String region,
			@QueryParam("timestamp") final String timestamp,
			@QueryParam("organization") final String organization,
			@QueryParam("transactionId") final String transactionId,
			@QueryParam("sourceName") final String sourceName,
			@QueryParam("applicationName") final String applicationName,
			@QueryParam("bsCarrier") final String bsCarrier,
			@QueryParam("netSuiteId") final Integer netSuiteId,
			@QueryParam("deviceId") final String deviceId,
			@QueryParam("kind") final String kind);

	@POST
	@Path("/cells/upload/bulk")
	@Produces("application/json")
	@Consumes("application/json")
	@ApiOperation(value = "Insert or Update Device Details in Bulk")
	BatchDeviceResponse updateDevicesDetailsBulk(BulkDevices device);

	@POST
	@Path("/device/deactivate")
	@Produces("application/json")
	@Consumes("application/json")
	@ApiOperation(value = "DeactivateDeviceService")
	DeactivateDeviceResponse deactivateDevice(
			DeactivateDeviceRequest deactivateDeviceRequest);

	@POST
	@Path("/device/activate")
	@Produces("application/json")
	@Consumes("application/json")
	@ApiOperation(value = "ActivateDeviceService")
	ActivateDeviceResponse activateDevice(
			ActivateDeviceRequest activateDeviceRequest);

	@POST
	@Path("/device/reactivate")
	@Produces("application/json")
	@Consumes("application/json")
	@ApiOperation(value = "ReactivateDeviceService")
	ReactivateDeviceResponse reactivateDevice(
			ReactivateDeviceRequest reActivateDeviceRequest);

	@POST
	@Path("/device/suspend")
	@Produces("application/json")
	@Consumes("application/json")
	@ApiOperation(value = "SuspendDeviceService")
	SuspendDeviceResponse suspendDevice(
			SuspendDeviceRequest suspendDeviceRequest);

	@POST
	@Path("/device/customFields")
	@Produces("application/json")
	@Consumes("application/json")
	@ApiOperation(value = "CustomFieldsUpdateService")
	CustomFieldsDeviceResponse customFieldsUpdateRequest(
			CustomFieldsDeviceRequest customeFieldDeviceRequest);

	@POST
	@Path("/device/changeServicePlan")
	@Produces("application/json")
	@Consumes("application/json")
	@ApiOperation(value = "ChangeDeviceServicePlans")
	ChangeDeviceServicePlansResponse changeDeviceServicePlans(
			ChangeDeviceServicePlansRequest changeDeviceServicePlansRequest);

	@POST
	@Path("/device/callback")
	@Produces("application/json")
	@Consumes("application/json")
	@ApiOperation(value = "Service for Receiving Callback from Verizon")
	void callbacks(CallBackVerizonRequest activateDeviceRequest);

	@GET
	@Path("/devices/connections/getStatus")
	@Produces("application/json")
	@ApiOperation(value = "Service to check Device in Session for Verizon")
	ConnectionStatusResponse deviceConnectionStatusRequest(
			@QueryParam("region") final String region,
			@QueryParam("timestamp") final String timestamp,
			@QueryParam("organization") final String organization,
			@QueryParam("transactionId") final String transactionId,
			@QueryParam("sourceName") final String sourceName,
			@QueryParam("applicationName") final String applicationName,
			@QueryParam("bsCarrier") final String bsCarrier,
			@QueryParam("deviceId") final String deviceId,
			@QueryParam("kind") final String kind,
			@QueryParam("earliest") final String earliest,
			@QueryParam("latest") final String latest);

	@POST
	@Path("/device/restore")
	@Produces("application/json")
	@Consumes("application/json")
	@ApiOperation(value = "RestoreDeviceService")
	RestoreDeviceResponse restoreDevice(
			RestoreDeviceRequest restoreDeviceRequest);

	@GET
	@Path("/device/usage/session")
	@Produces("application/json")
	@ApiOperation(value = "Retrieve Device data Usage by start and end Date from Carrier for Veriozn Devices.")
	UsageInformationResponse retrieveDeviceUsageHistoryCarrier(
			@QueryParam("region") final String region,
			@QueryParam("timestamp") final String timestamp,
			@QueryParam("organization") final String organization,
			@QueryParam("transactionId") final String transactionId,
			@QueryParam("sourceName") final String sourceName,
			@QueryParam("applicationName") final String applicationName,
			@QueryParam("bsCarrier") final String bsCarrier,
			@QueryParam("deviceId") final String deviceId,
			@QueryParam("kind") final String kind,
			@QueryParam("earliest") final String earliest,
			@QueryParam("latest") final String latest);

	@GET
	@Path("/devices/connections/session/info")
	@Produces("application/json")
	@ApiOperation(value = "Service to check Device Session Begin and End information for Verizon")
	SessionBeginEndResponse deviceSessionBeginEndResponse(
			@QueryParam("region") final String region,
			@QueryParam("timestamp") final String timestamp,
			@QueryParam("organization") final String organization,
			@QueryParam("transactionId") final String transactionId,
			@QueryParam("sourceName") final String sourceName,
			@QueryParam("applicationName") final String applicationName,
			@QueryParam("bsCarrier") final String bsCarrier,
			@QueryParam("deviceId") final String deviceId,
			@QueryParam("kind") final String kind,
			@QueryParam("earliest") final String earliest,
			@QueryParam("latest") final String latest);

	@POST
	@Path("/devices/job/usage/transactionFailure")
	@Produces("application/json")
	@Consumes("application/json")
	@ApiOperation(value = "Transactional Failure Device Usage Job")
	JobinitializedResponse transactionFailureDeviceUsageJob(
			JobParameter jobParameter);

	@POST
	@Path("/devices/job/connectionHistory/transactionFailure")
	@Produces("application/json")
	@Consumes("application/json")
	@ApiOperation(value = "Transactional Failure Device Connection History Job")
	JobinitializedResponse transactionFailureConnectionHistoryJob(
			JobParameter jobParameter);

	@POST
	@Path("/devices/job/usage/reRun")
	@Produces("application/json")
	@Consumes("application/json")
	@ApiOperation(value = "Rerun Device Usage Job")
	JobinitializedResponse reRunDeviceUsageJob(JobParameter jobParameter);

	@POST
	@Path("/devices/job/connectionHistory/reRun")
	@Produces("application/json")
	@Consumes("application/json")
	@ApiOperation(value = "Rerun Device Connection History Job")
	JobinitializedResponse reRunConnectionHistoryJob(JobParameter jobParameter);

	@GET
	@Path("/device/usage")
	@Produces("application/json")
	@ApiOperation(value = "Get Device Usage by start and end Date from Midway")
	UsageInformationMidwayResponse getDeviceUsageInfoDB(
			@QueryParam("region") final String region,
			@QueryParam("timestamp") final String timestamp,
			@QueryParam("organization") final String organization,
			@QueryParam("transactionId") final String transactionId,
			@QueryParam("sourceName") final String sourceName,
			@QueryParam("applicationName") final String applicationName,
			@QueryParam("bsCarrier") final String bsCarrier,
			@QueryParam("netSuiteId") final Integer netSuiteId,
			@QueryParam("startDate") final String startDate,
			@QueryParam("endDate") final String endDate);

	@GET
	@Path("/device/connectionHistory")
	@Produces("application/json")
	@ApiOperation(value = "Get Device Connection History by start and end Date from Midway for Veriozn Devices.")
	ConnectionInformationMidwayResponse getDeviceConnectionHistoryInfoDB(
			@QueryParam("region") final String region,
			@QueryParam("timestamp") final String timestamp,
			@QueryParam("organization") final String organization,
			@QueryParam("transactionId") final String transactionId,
			@QueryParam("sourceName") final String sourceName,
			@QueryParam("applicationName") final String applicationName,
			@QueryParam("bsCarrier") final String bsCarrier,
			@QueryParam("netSuiteId") final Integer netSuiteId,
			@QueryParam("startDate") final String startDate,
			@QueryParam("endDate") final String endDate);

}
