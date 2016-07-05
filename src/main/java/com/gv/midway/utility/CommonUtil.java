package com.gv.midway.utility;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.gv.midway.constant.CarrierType;
import com.gv.midway.constant.IEndPoints;
import com.gv.midway.pojo.job.JobParameter;
import com.gv.midway.pojo.job.JobinitializedResponse;
import com.gv.midway.pojo.verizon.DeviceId;

public class CommonUtil {

	private static Logger log = Logger.getLogger(CommonUtil.class);

	public static List<String> endPointList = new ArrayList<String>();

	static {

		endPointList.add(IEndPoints.ACTIVATION_ENDPOINT);
		endPointList.add(IEndPoints.DEACTIVATION_ENDPOINT);
		endPointList.add(IEndPoints.RESTORE_ENDPOINT);
		endPointList.add(IEndPoints.SUSPENSION_ENDPOINT);

		endPointList.add(IEndPoints.ACTIVATION_SEDA_KORE_ENDPOINT);
		endPointList.add(IEndPoints.DEACTIVATION_SEDA_KORE_ENDPOINT);
		endPointList.add(IEndPoints.RESTORE_SEDA_KORE_ENDPOINT);
		endPointList.add(IEndPoints.SUSPENSION_SEDA_KORE_ENDPOINT);
		endPointList.add(IEndPoints.REACTIVATION_SEDA_KORE_ENDPOINT);

		endPointList.add(IEndPoints.CHANGE_CUSTOMFIELD_ENDPOINT);
		endPointList.add(IEndPoints.CHANGE_SERVICEPLAN_ENDPOINT);
		endPointList.add(IEndPoints.CHANGE_SERVICEPLAN_SEDA_KORE_ENDPOINT);
		endPointList.add(IEndPoints.CHANGE_CUSTOMFIELD_SEDA_KORE_ENDPOINT);
	}

	public static String getCurrentTimeStamp() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();

		return dateFormat.format(date);

	}

	public static String getIpAddress()

	{
		InetAddress ip;

		try {

			ip = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			return "Defulat_IP";
		}

		return ip.toString();

	}

	public static String getMidwayTransactionID() {
		long timestamp = System.currentTimeMillis();
		return Long.toString(timestamp);

	}

	public static String getDerivedCarrierName(Object carrierName) {

		String sourceDirived = null;

		if (carrierName == null) {

			return null;
		}

		if (carrierName.toString().startsWith("V")) {

			return sourceDirived = "VERIZON";

		} else if (carrierName.toString().startsWith("K")) {

			return sourceDirived = "KORE";
		}

		return sourceDirived;
	}

	public static boolean isProvisioningMethod(String endPoint) {

		log.info("endpoint is......." + endPoint);

		for (Iterator<String> iterator = endPointList.iterator(); iterator
				.hasNext();) {
			String element = (String) iterator.next();

			if (endPoint.contains(element)) {

				return true;
			}

		}

		return false;

	}

	// Returning the recommended device Identifier such as ESN/MEID/ICCID for
	// Device Connection and Device Usage Batch Jobs

	public static DeviceId getRecommendedDeviceIdentifier(DeviceId[] devices) {

		log.info("getRecommendedDeviceIdentifier........deviceId..is.......");

		for (DeviceId device : devices) {
			if (("ESN".equalsIgnoreCase(device.getKind()))
					|| ("MEID".equalsIgnoreCase(device.getKind()))
					|| ("ICCID".equalsIgnoreCase(device.getKind()))) {

				return device;
			}

		}

		return devices[0];

	}

	// Returning the Sim Number from the DeviceId Array
	// Device Usage Batch Jobs

	public static DeviceId getSimNumber(DeviceId[] devices) {

		log.info("getSimNumber........deviceId..is.......");

		for (DeviceId device : devices) {
			if (("SIM".equalsIgnoreCase(device.getKind()))) {

				return device;
			}

		}

		return null;

	}
	
	public static boolean isValidDateFormat(String date){
		
      
        String exp="^([\\d]{4})[-]?(0?[1-9]|1[012])[-]?(0?[1-9]|[12][0-9]|3[01])";
        
        Pattern pattern = Pattern.compile(exp,Pattern.CASE_INSENSITIVE);   
        
        Matcher matcher = pattern.matcher(date);   
        
        if(matcher.matches())
        {   
           log.info("valid date format for....."+date);  
           
            
            return true;
        } 
        else
        {
            log.info("invalid date format for....."+date);
            
            return false;
        }
	}
	
	
}
