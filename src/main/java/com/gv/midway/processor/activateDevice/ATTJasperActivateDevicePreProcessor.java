package com.gv.midway.processor.activateDevice;

import com.gv.midway.attjasper.EditTerminalRequest;
import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.activateDevice.request.ActivateDeviceRequest;
import com.gv.midway.pojo.transaction.Transaction;
import com.gv.midway.utility.CommonUtil;
import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Processor;
import org.apache.camel.component.cxf.common.message.CxfConstants;
import org.apache.cxf.binding.soap.SoapHeader;
import org.apache.cxf.headers.Header;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * Created by ryan.tracy on 9/6/2016.
 */
public class ATTJasperActivateDevicePreProcessor implements Processor {

    private static final Logger LOGGER = Logger.getLogger(ATTJasperActivateDevicePreProcessor.class.getName());

    Environment newEnv;

    public ATTJasperActivateDevicePreProcessor(Environment env) {
        super();
        this.newEnv = env;
    }

    @Override
    public void process(Exchange exchange) throws Exception {

        LOGGER.info("Begin:ATTJasperActivateDevicePreProcessor");

        Transaction transaction = exchange.getIn().getBody(Transaction.class);

        ActivateDeviceRequest activateDeviceRequest = (ActivateDeviceRequest)transaction.getDevicePayload();

        String deviceId = activateDeviceRequest.getDataArea().getDevices().getDeviceIds()[0].getId();

        //String EAPCode = activateDeviceRequest.getDataArea().getDevices().getServicePlan();

        //deviceId = "89011702272013902603";

        EditTerminalRequest editTerminalRequest = new EditTerminalRequest();

        editTerminalRequest.setIccid(deviceId);
        LocalDateTime currentUTCTime = LocalDateTime.now(); // using system timezone
        XMLGregorianCalendar xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(currentUTCTime.toString());
        editTerminalRequest.setEffectiveDate(xmlDate);
        editTerminalRequest.setChangeType(IConstant.ATTJASPER_SIM_CHANGETYPE);
        editTerminalRequest.setTargetValue(IConstant.ATTJASPER_ACTIVATED);

        String version = newEnv.getProperty("attJapser.version");

        String licenseKey = newEnv.getProperty("attJapser.licenseKey");

        editTerminalRequest.setLicenseKey(licenseKey);
        editTerminalRequest.setMessageId("" + new Date().getTime());
        editTerminalRequest.setVersion(version);

        LOGGER.info("activate of iccId..............." + deviceId);

        exchange.getIn().setBody(editTerminalRequest);

        exchange.getIn().setHeader(CxfConstants.OPERATION_NAME, "EditTerminal");
        exchange.getIn().setHeader(CxfConstants.OPERATION_NAMESPACE, "http://api.jasperwireless.com/ws/schema");
        exchange.getIn().setHeader("soapAction", "http://api.jasperwireless.com/ws/service/terminal/EditTerminal");

        exchange.setProperty(IConstant.MIDWAY_NETSUITE_ID, transaction.getNetSuiteId());

        String username = newEnv.getProperty("attJapser.userName");

        String password = newEnv.getProperty("attJapser.password");

        List<SoapHeader> soapHeaders = CommonUtil.getSOAPHeaders(username, password);

        exchange.getIn().setHeader(Header.HEADER_LIST, soapHeaders);
        exchange.setPattern(ExchangePattern.InOut);

        LOGGER.info("End:ATTJasperActivateDevicePreProcessor");
    }
}
