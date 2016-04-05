package com.gv.midway.pojo.deactivateDevice.request;
import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gv.midway.pojo.verizon.CustomFields;
import com.gv.midway.pojo.verizon.DeviceId;
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeactivateDeviceRequestDataArea {
	private String groupName;

	private String accountName;

	private CustomFields[] customFields;

	private String servicePlan;

	private String reasonCode;

	private DeactivateDevices[] devices;

	public DeactivateDevices[] getDevices() {
		return devices;
	}

	public void setDevices(DeactivateDevices[] devices) {
		this.devices = devices;
	}

	private String etfWaiver;

	private Boolean flagScrap;

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public CustomFields[] getCustomFields() {
		return customFields;
	}

	public void setCustomFields(CustomFields[] customFields) {
		this.customFields = customFields;
	}

	public String getServicePlan() {
		return servicePlan;
	}

	public void setServicePlan(String servicePlan) {
		this.servicePlan = servicePlan;
	}

	public String getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}


	public String getEtfWaiver() {
		return etfWaiver;
	}

	public void setEtfWaiver(String etfWaiver) {
		this.etfWaiver = etfWaiver;
	}


	public Boolean getFlagScrap() {
		return flagScrap;
	}

	public void setFlagScrap(Boolean flagScrap) {
		this.flagScrap = flagScrap;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((accountName == null) ? 0 : accountName.hashCode());
		result = prime * result + Arrays.hashCode(customFields);
		result = prime * result + Arrays.hashCode(devices);
		result = prime * result
				+ ((etfWaiver == null) ? 0 : etfWaiver.hashCode());
		result = prime * result
				+ ((flagScrap == null) ? 0 : flagScrap.hashCode());
		result = prime * result
				+ ((groupName == null) ? 0 : groupName.hashCode());
		result = prime * result
				+ ((reasonCode == null) ? 0 : reasonCode.hashCode());
		result = prime * result
				+ ((servicePlan == null) ? 0 : servicePlan.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DeactivateDeviceRequestDataArea other = (DeactivateDeviceRequestDataArea) obj;
		if (accountName == null) {
			if (other.accountName != null)
				return false;
		} else if (!accountName.equals(other.accountName))
			return false;
		if (!Arrays.equals(customFields, other.customFields))
			return false;
		if (!Arrays.equals(devices, other.devices))
			return false;
		if (etfWaiver == null) {
			if (other.etfWaiver != null)
				return false;
		} else if (!etfWaiver.equals(other.etfWaiver))
			return false;
		if (flagScrap == null) {
			if (other.flagScrap != null)
				return false;
		} else if (!flagScrap.equals(other.flagScrap))
			return false;
		if (groupName == null) {
			if (other.groupName != null)
				return false;
		} else if (!groupName.equals(other.groupName))
			return false;
		if (reasonCode == null) {
			if (other.reasonCode != null)
				return false;
		} else if (!reasonCode.equals(other.reasonCode))
			return false;
		if (servicePlan == null) {
			if (other.servicePlan != null)
				return false;
		} else if (!servicePlan.equals(other.servicePlan))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DeactivateDeviceRequestDataArea [groupName=");
		builder.append(groupName);
		builder.append(", accountName=");
		builder.append(accountName);
		builder.append(", customFields=");
		builder.append(Arrays.toString(customFields));
		builder.append(", servicePlan=");
		builder.append(servicePlan);
		builder.append(", reasonCode=");
		builder.append(reasonCode);
		builder.append(", devices=");
		builder.append(Arrays.toString(devices));
		builder.append(", etfWaiver=");
		builder.append(etfWaiver);
		builder.append(", flagScrap=");
		builder.append(flagScrap);
		builder.append("]");
		return builder.toString();
	}

	
	
}
