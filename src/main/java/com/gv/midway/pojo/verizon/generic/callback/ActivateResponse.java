package com.gv.midway.pojo.verizon.generic.callback;

import java.util.Arrays;

public class ActivateResponse {
	private String[] featureCodes;

    private DeviceIds[] deviceIds;

    private String state;

    private DeviceCredential deviceCredential;

    private String servicePlan;

    private String ipAddress;

    public String[] getFeatureCodes ()
    {
        return featureCodes;
    }

    public void setFeatureCodes (String[] featureCodes)
    {
        this.featureCodes = featureCodes;
    }

    public DeviceIds[] getDeviceIds ()
    {
        return deviceIds;
    }

    public void setDeviceIds (DeviceIds[] deviceIds)
    {
        this.deviceIds = deviceIds;
    }

    public String getState ()
    {
        return state;
    }

    public void setState (String state)
    {
        this.state = state;
    }

    public DeviceCredential getDeviceCredential ()
    {
        return deviceCredential;
    }

    public void setDeviceCredential (DeviceCredential deviceCredential)
    {
        this.deviceCredential = deviceCredential;
    }

    public String getServicePlan ()
    {
        return servicePlan;
    }

    public void setServicePlan (String servicePlan)
    {
        this.servicePlan = servicePlan;
    }

    public String getIpAddress ()
    {
        return ipAddress;
    }

    public void setIpAddress (String ipAddress)
    {
        this.ipAddress = ipAddress;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [featureCodes = "+featureCodes+", deviceIds = "+deviceIds+", state = "+state+", deviceCredential = "+deviceCredential+", servicePlan = "+servicePlan+", ipAddress = "+ipAddress+"]";
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((deviceCredential == null) ? 0 : deviceCredential.hashCode());
		result = prime * result + Arrays.hashCode(deviceIds);
		result = prime * result + Arrays.hashCode(featureCodes);
		result = prime * result
				+ ((ipAddress == null) ? 0 : ipAddress.hashCode());
		result = prime * result
				+ ((servicePlan == null) ? 0 : servicePlan.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof ActivateResponse))
			return false;
		ActivateResponse other = (ActivateResponse) obj;
		if (deviceCredential == null) {
			if (other.deviceCredential != null)
				return false;
		} else if (!deviceCredential.equals(other.deviceCredential))
			return false;
		if (!Arrays.equals(deviceIds, other.deviceIds))
			return false;
		if (!Arrays.equals(featureCodes, other.featureCodes))
			return false;
		if (ipAddress == null) {
			if (other.ipAddress != null)
				return false;
		} else if (!ipAddress.equals(other.ipAddress))
			return false;
		if (servicePlan == null) {
			if (other.servicePlan != null)
				return false;
		} else if (!servicePlan.equals(other.servicePlan))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		return true;
	}
    
    
}
