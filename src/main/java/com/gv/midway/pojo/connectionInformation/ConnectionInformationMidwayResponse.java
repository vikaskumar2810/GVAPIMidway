package com.gv.midway.pojo.connectionInformation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gv.midway.pojo.BaseResponse;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.Response;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ConnectionInformationMidwayResponse extends BaseResponse {

    public ConnectionInformationMidwayResponse() { }

    public ConnectionInformationMidwayResponse(Header header, Response response) {
        super(header, response);
    }

    public ConnectionInformationMidwayResponse(Header header, Response response, ConnectionInformationResponseMidwayDataArea dataArea) {
        super(header, response);
        this.dataArea = dataArea;
    }

    private ConnectionInformationResponseMidwayDataArea dataArea;

    @Override
    public String toString() {
        return "ConnectionInformationMidwayResponse [dataArea=" + dataArea + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((dataArea == null) ? 0 : dataArea.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        ConnectionInformationMidwayResponse other = (ConnectionInformationMidwayResponse) obj;
        if (dataArea == null) {
            if (other.dataArea != null)
                return false;
        } else if (!dataArea.equals(other.dataArea))
            return false;
        return true;
    }

    public ConnectionInformationResponseMidwayDataArea getDataArea() {
        return dataArea;
    }

    public void setDataArea(ConnectionInformationResponseMidwayDataArea dataArea) {
        this.dataArea = dataArea;
    }
}