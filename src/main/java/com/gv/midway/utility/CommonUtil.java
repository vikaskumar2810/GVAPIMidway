package com.gv.midway.utility;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.gv.midway.constant.IEndPoints;

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

	public static String getDerivedCarrierName(String carrierName) {

		String sourceDirived = null;

		if (carrierName.startsWith("V")) {

			return sourceDirived = "VERIZON";

		} else if (carrierName.startsWith("K")) {

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
}
