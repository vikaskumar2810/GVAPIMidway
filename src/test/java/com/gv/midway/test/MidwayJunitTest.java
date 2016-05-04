/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.gv.midway.test;

import static org.junit.Assert.assertEquals;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.activateDevice.request.ActivateDeviceId;
import com.gv.midway.pojo.activateDevice.request.ActivateDeviceRequest;
import com.gv.midway.pojo.activateDevice.request.ActivateDeviceRequestDataArea;
import com.gv.midway.pojo.activateDevice.request.ActivateDevices;
import com.gv.midway.pojo.activateDevice.response.ActivateDeviceResponse;
import com.gv.midway.pojo.activateDevice.response.ActivateDeviceResponseDataArea;
import com.gv.midway.pojo.callback.request.CallBackVerizonRequest;
import com.gv.midway.pojo.deactivateDevice.kore.request.DeactivateDeviceRequestKore;
import com.gv.midway.pojo.deactivateDevice.request.DeactivateDeviceId;
import com.gv.midway.pojo.deactivateDevice.request.DeactivateDeviceRequest;
import com.gv.midway.pojo.deactivateDevice.request.DeactivateDeviceRequestDataArea;
import com.gv.midway.pojo.deactivateDevice.request.DeactivateDevices;
import com.gv.midway.pojo.deactivateDevice.response.DeactivateDeviceResponse;
import com.gv.midway.pojo.deactivateDevice.verizon.request.DeactivateDeviceRequestVerizon;
import com.gv.midway.pojo.device.request.BulkDevices;
import com.gv.midway.pojo.device.request.DeviceDataArea;
import com.gv.midway.pojo.device.request.DevicesDataArea;
import com.gv.midway.pojo.device.request.SingleDevice;
import com.gv.midway.pojo.device.response.BatchDeviceResponse;
import com.gv.midway.pojo.device.response.UpdateDeviceResponse;
import com.gv.midway.pojo.deviceInformation.request.DeviceInformationRequest;
import com.gv.midway.pojo.deviceInformation.request.DeviceInformationRequestDataArea;
import com.gv.midway.pojo.deviceInformation.response.Cell;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformation;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformationResponse;
import com.gv.midway.pojo.restoreDevice.request.RestoreDeviceRequest;
import com.gv.midway.pojo.restoreDevice.request.RestoreDeviceRequestDataArea;
import com.gv.midway.pojo.suspendDevice.request.SuspendDeviceRequest;
import com.gv.midway.pojo.suspendDevice.request.SuspendDeviceRequestDataArea;
import com.gv.midway.pojo.verizon.DeviceId;
import com.gv.midway.pojo.verizon.Devices;

public class MidwayJunitTest extends Assert {
	private AbstractApplicationContext applicationContext;
	private ProducerTemplate template;

	protected DeviceTestData testData = new DeviceTestData();

	@Before
	public void setUp() throws Exception {
		applicationContext = new ClassPathXmlApplicationContext(
				"camel-config.xml");
		CamelContext camelContext = getCamelContext();
		template = camelContext.createProducerTemplate();
	}

	/*..................test cases for Header starts here ..............*/
	
	// test case for header Information-common for all API
	@Test
	public void testValidHeaderParameters() throws Exception {

		Header header = new Header();
		
		header.setApplicationName("WEB");
		header.setTimestamp("2016-03-08T21:49:45");
		header.setOrganization("Grant Victor");
		header.setSourceName("NetSuit");
		header.setTransactionId("gv123666");
		header.setBsCarrier("VERIZON");
		header.setRegion("USA");
		
		assertEquals(header.getApplicationName(),"WEB");
		assertEquals(header.getOrganization(),"Grant Victor");
		assertEquals(header.getBsCarrier(),"VERIZON");
		assertEquals(header.getSourceName(),"NetSuit");
		assertEquals(header.getTransactionId(),"gv123666");
		
	}

	//Test case for invalid Source Name
	@Test
	public void testInvalidHeaderSourceName() throws Exception {

		Header header = new Header();
		header.setSourceName("gfg");  //Invalid Input
		assertEquals(header.getSourceName(),"gfg");
	}
	
	//test case for Invalid Bs Carrier
	@Test
	public void testInvalidHeaderBsCarrier() throws Exception {

		Header header = new Header();
		header.setBsCarrier("test");   //Invalid Input		
		assertEquals(header.getBsCarrier(),"test");
	}
	
	/*..................test cases for Header ends here ..............*/
	
	
	/*..................test cases for Activate Device starts here ..............*/	
	
	// test case for Activate Device Kore with valid data
	@Test
		public void testValidActivateDeviceRequestKore() throws Exception {

			ActivateDeviceRequest req = new ActivateDeviceRequest();
			Header header = new Header();

			ActivateDeviceRequestDataArea dataArea = new ActivateDeviceRequestDataArea();

			ActivateDevices [] decs=new ActivateDevices[1];
			ActivateDevices adevices = new ActivateDevices();
			
			
			ActivateDeviceId[] ActivateDeviceIdArray = new ActivateDeviceId[1];
			 
			ActivateDeviceId deviceId= new ActivateDeviceId();
			
			deviceId.setId("89014103277405947099");
			ActivateDeviceIdArray[0] = deviceId;
			
			adevices.setDeviceIds(ActivateDeviceIdArray);
			decs[0] = adevices;
			dataArea.setDevices(decs);
			
			req.setDataArea(dataArea);
			dataArea.setAccountName("0442090022-00001");
			dataArea.seteAPCode("EAP02500797");
			dataArea.setMdnZipCode("38523");
			dataArea.setServicePlan("M2MPERMB");
			
			req.setDataArea(dataArea);
		 	
	 		header.setSourceName("NetSuit");
			header.setTransactionId("gv123666");
	 		header.setBsCarrier("KORE");
	 		header.setApplicationName("WEB");
	 		header.setOrganization("Grant Victor");
	        header.setRegion("USA");
	        header.setTimestamp("string");
	        req.setHeader(header);
			System.out.println("Request in Junit Test:" + req);
			testData.setExpectedActivateDevice(req);
			ActivateDeviceResponse response = (ActivateDeviceResponse) template.requestBody("direct:activateDevice", req);
			
			System.out.println("Activate Device request is...................:"+response);
			//testData.verifyDeviceActivationData();
			 assertEquals(response.getResponse().getResponseCode().toString(),"2000");
				
		}
		
		//test case  for Activate Device Kore with Invalid Data
	    @Test
		public void testInvalidActivateDeviceRequestKore() throws Exception {

			ActivateDeviceRequest req = new ActivateDeviceRequest();
			Header header = new Header();

			ActivateDeviceRequestDataArea dataArea = new ActivateDeviceRequestDataArea();

			ActivateDevices [] decs=new ActivateDevices[1];
			ActivateDevices adevices = new ActivateDevices();
			
			
			ActivateDeviceId[] ActivateDeviceIdArray = new ActivateDeviceId[1];
			 
			ActivateDeviceId deviceId= new ActivateDeviceId();
			
			deviceId.setId("89014103277405947099");
			ActivateDeviceIdArray[0] = deviceId;
			
			adevices.setDeviceIds(ActivateDeviceIdArray);
			decs[0] = adevices;
			dataArea.setDevices(decs);
			
			req.setDataArea(dataArea);
			dataArea.setAccountName("0442090022-00001");
			dataArea.seteAPCode("EAP02500797");
			dataArea.setMdnZipCode("38523");
			dataArea.setServicePlan("M2MPERMB");
			
			req.setDataArea(dataArea);
		 	
	 		header.setSourceName("NetSuit");
			header.setTransactionId("gv123666");
	 		header.setBsCarrier("");  //Invalid Input
	 		header.setApplicationName("WEB");
	 		header.setOrganization("Grant Victor");
	        header.setRegion("USA");

	        req.setHeader(header);
			assertEquals(req.getHeader().getBsCarrier(),"");	
		}
		
		
		// test case for Activate Device Verizon with valid data
				//@Test
				public void testValidActivateDeviceRequestVerizon() throws Exception {

					ActivateDeviceRequest req = new ActivateDeviceRequest();
					Header header = new Header();

					ActivateDeviceRequestDataArea dataArea = new ActivateDeviceRequestDataArea();

					ActivateDevices [] decs=new ActivateDevices[1];
					ActivateDevices adevices = new ActivateDevices();
					
					ActivateDeviceId[] ActivateDeviceIdArray = new ActivateDeviceId[2];
					 
					ActivateDeviceId deviceId1= new ActivateDeviceId();
					ActivateDeviceId deviceId2= new ActivateDeviceId();
					
					deviceId1.setId("89148000002034203015");
					deviceId1.setKind("ICCID");
					ActivateDeviceIdArray[0] = deviceId1;
					
					deviceId2.setId("353238061040837");
					deviceId2.setKind("IMEI");
					ActivateDeviceIdArray[1] = deviceId2;
					
					adevices.setDeviceIds(ActivateDeviceIdArray);
					decs[0] = adevices;
					dataArea.setDevices(decs);
					
					dataArea.setAccountName("0442090022-00001");
					//dataArea.seteAPCode("EAP02500797");
					dataArea.setMdnZipCode("38523");
					dataArea.setServicePlan("M2MPERMB");
					
					req.setDataArea(dataArea);
				 	
			 		header.setSourceName("NetSuit");
					header.setTransactionId("gv123666");
			 		header.setBsCarrier("VERIZON");
			 		header.setApplicationName("WEB");
			 		header.setOrganization("Grant Victor");
			        header.setRegion("USA");

			        req.setHeader(header);
					ActivateDeviceResponse response = (ActivateDeviceResponse) template.requestBody("direct:activateDevice", req);
					
					System.out.println("Activate Device response is...................:"+response);
					
					 assertEquals(response.getResponse().getResponseCode().toString(),"2000");
						
				}
		/*..................test cases for Activate Device ends here ..............*/
		
		
/*..................test cases for Deactivate Device starts here ..............*/
	// Test Case for Deactivate Device for Kore with Valid data
	@Test
	public void testValidDeactivateDeviceRequestKore() throws Exception {

		DeactivateDeviceRequestKore req = new DeactivateDeviceRequestKore();
		Header header = new Header();
       
		DeactivateDeviceRequestKore deactivateDeviceRequest = new DeactivateDeviceRequestKore();
		DeactivateDeviceRequestDataArea dataArea = new DeactivateDeviceRequestDataArea();
		
		DeactivateDeviceRequest deactivateRequest =new DeactivateDeviceRequest();
		
		DeactivateDevices [] deDevices=new DeactivateDevices[1];
        DeactivateDevices ddevices = new DeactivateDevices();

        DeactivateDeviceId[] DeActivateDeviceIdArray = new DeactivateDeviceId[1];
 		 
 		DeactivateDeviceId deactivateDeviceId= new DeactivateDeviceId();
 		
 		deactivateDeviceId.setId("8901260761246107349");
 		//deactivateDeviceId.setKind("ghgjg");
 		deactivateDeviceId.setFlagScrap(Boolean.FALSE);
 		
 		DeActivateDeviceIdArray[0] = deactivateDeviceId;
 		
 		ddevices.setDeviceIds(DeActivateDeviceIdArray);
 		deDevices[0] = ddevices;
 		dataArea.setDevices(deDevices);
 		dataArea.setAccountName("0442090022-00001");
 	
 		header.setSourceName("NetSuit");
		header.setTransactionId("gv123666");
 		header.setBsCarrier("KORE");
 		header.setApplicationName("WEB");
 		header.setOrganization("Grant Victor");
        header.setRegion("USA");
 		req.setFlagScrap(false);
		
 		deactivateRequest.setHeader(header);
		deactivateRequest.setDataArea(dataArea);
		DeactivateDeviceResponse response1 = (DeactivateDeviceResponse) template.requestBody("direct:deactivateDevice", deactivateRequest);
		response1.getResponse().getResponseCode();
		System.out.println("Response in Junit Test for Deactivate....... :" + response1.getResponse().getResponseCode());
		 assertEquals(response1.getResponse().getResponseCode().toString(),"2000");
		
		}
	
	// Test Case for Deactivate Device for Kore with Invalid Data 
	@Test
		public void testInvalidDeactivateDeviceRequestKore() throws Exception {

			DeactivateDeviceRequestKore req = new DeactivateDeviceRequestKore();
			Header header = new Header();
	       
			DeactivateDeviceRequestKore deactivateDeviceRequest = new DeactivateDeviceRequestKore();
			DeactivateDeviceRequestDataArea dataArea = new DeactivateDeviceRequestDataArea();
			
			DeactivateDeviceRequest deactivateRequest =new DeactivateDeviceRequest();
			
			DeactivateDevices [] deDevices=new DeactivateDevices[1];
	        DeactivateDevices ddevices = new DeactivateDevices();

	        DeactivateDeviceId[] DeActivateDeviceIdArray = new DeactivateDeviceId[1];
	 		 
	 		DeactivateDeviceId deactivateDeviceId= new DeactivateDeviceId();
	 		
	 		deactivateDeviceId.setId("8901260761246107349");
	 		//deactivateDeviceId.setKind("ghgjg");
	 		deactivateDeviceId.setFlagScrap(Boolean.FALSE);
	 		
	 		DeActivateDeviceIdArray[0] = deactivateDeviceId;
	 		
	 		ddevices.setDeviceIds(DeActivateDeviceIdArray);
	 		deDevices[0] = ddevices;
	 		dataArea.setDevices(deDevices);
	 		dataArea.setAccountName("0442090022-00001");
	 	
	 		header.setSourceName("NetSuit");
			header.setTransactionId("gv123666");
	 		header.setBsCarrier("");  //Invalid Input
	 		header.setApplicationName("WEB");
	 		header.setOrganization("Grant Victor");
	        header.setRegion("USA");
	 		req.setFlagScrap(false);
			
	 		System.out.println("Request in Junit Test:" + header);
			deactivateRequest.setHeader(header);
			deactivateRequest.setDataArea(dataArea);
			assertEquals(deactivateRequest.getHeader().getBsCarrier(),"");
			}
		
		//Test Case for Deactivate Device for Verizon with Valid data
		//@Test
		public void testValidDeactivateDeviceRequestVerizon() throws Exception {

			DeactivateDeviceRequestVerizon req = new DeactivateDeviceRequestVerizon();
			Header header = new Header();
	       
			DeactivateDeviceRequestKore deactivateDeviceRequest = new DeactivateDeviceRequestKore();
			DeactivateDeviceRequestDataArea dataArea = new DeactivateDeviceRequestDataArea();
			
			DeactivateDeviceRequest deactivateRequest =new DeactivateDeviceRequest();
			
			DeactivateDevices [] deDevices=new DeactivateDevices[1];
	        DeactivateDevices ddevices = new DeactivateDevices();

	        DeactivateDeviceId[] DeActivateDeviceIdArray = new DeactivateDeviceId[2];
	 		 
	 		DeactivateDeviceId deactivateDeviceId1= new DeactivateDeviceId();
	 		DeactivateDeviceId deactivateDeviceId2= new DeactivateDeviceId();
	 		
	 		deactivateDeviceId1.setId("353238063362759");
	 		deactivateDeviceId1.setKind("IMEI");
	 		deactivateDeviceId1.setFlagScrap(Boolean.FALSE);
	 		
	 		DeActivateDeviceIdArray[0] = deactivateDeviceId1;
	 		
	 		deactivateDeviceId2.setId("89148000002377519373");
	 		deactivateDeviceId2.setKind("ICCID");
	 		deactivateDeviceId2.setFlagScrap(Boolean.FALSE);
	 		
	 		DeActivateDeviceIdArray[1] = deactivateDeviceId2;
	 		
	 		ddevices.setDeviceIds(DeActivateDeviceIdArray);
	 		deDevices[0] = ddevices;
	 		dataArea.setDevices(deDevices);
	 		dataArea.setAccountName("0442090022-00001");
	 	
	 		header.setSourceName("NetSuit");
			header.setTransactionId("gv123666");
	 		header.setBsCarrier("VERIZON");
	 		header.setApplicationName("WEB");
	 		header.setOrganization("Grant Victor");
	        header.setRegion("USA");
	        header.setTimestamp("string");
	 		
	 		System.out.println("Request in Junit Test:" + header);
			deactivateRequest.setHeader(header);
			deactivateRequest.setDataArea(dataArea);
			testData.setExpectedDeactivateDevice(deactivateRequest);
			DeactivateDeviceResponse response1 = (DeactivateDeviceResponse) template.requestBody("direct:deactivateDevice", deactivateRequest);
			response1.getResponse().getResponseCode();
			System.out.println("Response in Junit Test for Deactivate :" + response1.getResponse().getResponseCode());
			 assertEquals(response1.getResponse().getResponseCode().toString(),"2000");
			
			//testData.verifyDeviceDeactivationData();

		}
		/*..................test cases for DeActivate Device ends here ..............*/

	/*..................test cases for Device Upload  starts here ..............*/
		
	//Test case for Insert Device details with valid data .. 
	@Test
	public void testDeviceUploadWithValidData() throws Exception{
		
		SingleDevice singleDevice= new SingleDevice();
		DeviceDataArea deviceDataArea =new DeviceDataArea();	
		Header header = new Header();
		
		header.setSourceName("NetSuit");
		header.setTransactionId("gv123666");
 		header.setBsCarrier("VERIZON");
 		header.setApplicationName("WEB");
 		header.setOrganization("Grant Victor");
        header.setRegion("USA");
       
 		
		DeviceInformation deviceInformation=new DeviceInformation();
		deviceInformation.setAccountName("0442090022-00001");
		deviceInformation.setNetSuiteId("NSV002");
		deviceInformation.setBs_carrier("VERIZON");
		deviceInformation.setSerial_num("1001");
		
		deviceDataArea.setDevices(deviceInformation);
		singleDevice.setDataArea(deviceDataArea);
		singleDevice.setHeader(header);		
		
		UpdateDeviceResponse response = (UpdateDeviceResponse) template.requestBody("direct:updateDeviceDetails", singleDevice);
		System.out.println("Response ifor update device is..............:" + response);
		assertEquals(response.getResponse().getResponseCode().toString(),"2000");
	}

	
	//Test case for  insert device detail with invalid data
	@Test
	public void testDeviceUploadWithInvalidData() throws Exception{
		
		SingleDevice singleDevice= new SingleDevice();
		DeviceDataArea deviceDataArea =new DeviceDataArea();	
		Header header = new Header();
		
		header.setSourceName("NetSuit");
		header.setTransactionId("gv123666");
 		header.setBsCarrier("VERIZON");
 		header.setApplicationName("WEB");
 		header.setOrganization("Grant Victor");
        header.setRegion("USA");
 		
		DeviceInformation deviceInformation=new DeviceInformation();
		deviceInformation.setAccountName("0442090022-00001");
		deviceInformation.setNetSuiteId("");  //Invalid Input
		deviceInformation.setBs_carrier("VERIZON");
		deviceInformation.setSerial_num("1001");
		
		deviceDataArea.setDevices(deviceInformation);
		singleDevice.setDataArea(deviceDataArea);
		singleDevice.setHeader(header);		
		
	UpdateDeviceResponse response = (UpdateDeviceResponse) template.requestBody("direct:updateDeviceDetails", singleDevice);
		System.out.println("Response for update device is..............:" + response.getResponse().getResponseDescription());
		assertEquals(response.getResponse().getResponseCode().toString(),"1905");
	}
	
	//Test case to update device detail with valid netsuit Id
		@Test
		public void testDeviceUpdateWithValidData() throws Exception{
			
			SingleDevice singleDevice= new SingleDevice();
			DeviceDataArea deviceDataArea =new DeviceDataArea();	
			Header header = new Header();
			
			header.setSourceName("NetSuit");
			header.setTransactionId("gv123666");
	 		header.setBsCarrier("VERIZON");
	 		header.setApplicationName("WEB");
	 		header.setOrganization("Grant Victor");
	        header.setRegion("USA");
	 		
			DeviceInformation deviceInformation=new DeviceInformation();
			deviceInformation.setAccountName("0442090022-00001");
			deviceInformation.setNetSuiteId("NSV002");
			deviceInformation.setBs_carrier("VERIZON");
			deviceInformation.setSerial_num("1002");
			
			deviceDataArea.setDevices(deviceInformation);
			singleDevice.setDataArea(deviceDataArea);
			singleDevice.setHeader(header);		
			UpdateDeviceResponse response = (UpdateDeviceResponse) template.requestBody("direct:updateDeviceDetails", singleDevice);
			System.out.println("Response for update device is..............:" + response.getResponse().getResponseDescription());
			assertEquals(response.getResponse().getResponseCode().toString(),"2000");
		}
	
	/*..................test cases for Upload Device ends here ..............*/
		
		/*..................test cases for Bulk Upload Device starts here ..............*/
		
		//Insert multiple/Bulk Device details with valid data .. 
		@Test
		public void testBulkDeviceUploadWithValidData() throws Exception{
			
			BulkDevices bulkDevices= new BulkDevices();
			DevicesDataArea devicesDataArea =new DevicesDataArea();	
			Header header = new Header();
			
			header.setSourceName("NetSuit");
			header.setTransactionId("gv123666");
	 		header.setBsCarrier("VERIZON");
	 		header.setApplicationName("WEB");
	 		header.setOrganization("Grant Victor");
	        header.setRegion("USA");
	        header.setTimestamp("2016-03-08T21:49:45");
	        
			DeviceInformation deviceInformation1=new DeviceInformation();
			DeviceInformation deviceInformation2=new DeviceInformation();
			DeviceInformation[] deviceInformationArray = new DeviceInformation[2];
			
			deviceInformation1.setAccountName("0442090022-00001");
			deviceInformation1.setNetSuiteId("NSV003");
			deviceInformation1.setBs_carrier("VERIZON");
			deviceInformation1.setSerial_num("1003");
			
			deviceInformation2.setAccountName("0442090022-00001");
			deviceInformation2.setNetSuiteId("NSV004");
			deviceInformation2.setBs_carrier("VERIZON");
			deviceInformation2.setSerial_num("1004");
			
			deviceInformationArray[0]= deviceInformation1;
			deviceInformationArray[1]= deviceInformation2;
		
			devicesDataArea.setDevices(deviceInformationArray);
			bulkDevices.setDataArea(devicesDataArea);
			bulkDevices.setHeader(header);		
			
			BatchDeviceResponse response = (BatchDeviceResponse) template.requestBody("direct:updateDevicesDetailsBulkActual", bulkDevices);
			System.out.println("Response for update device is..............:" + response);
			assertEquals(response.getResponse().getResponseCode().toString(),"2000");
			assertEquals(response.getDataArea().getSuccessCount().toString(),"2");
		}
		
		
		 //Insert multiple/Bulk Device details with Invalid data .. 
				@Test
				public void testBulkDeviceUploadWithInvalidData() throws Exception{
					
					BulkDevices bulkDevices= new BulkDevices();
					DevicesDataArea devicesDataArea =new DevicesDataArea();	
					Header header = new Header();
					
					header.setSourceName("NetSuit");
					header.setTransactionId("gv123666");
			 		header.setBsCarrier("VERIZON");
			 		header.setApplicationName("WEB");
			 		header.setOrganization("Grant Victor");
			        header.setRegion("USA");
			        header.setTimestamp("2016-03-08T21:49:45");
			        
					DeviceInformation deviceInformation1=new DeviceInformation();
					DeviceInformation deviceInformation2=new DeviceInformation();
					DeviceInformation[] deviceInformationArray = new DeviceInformation[2];
					
					deviceInformation1.setAccountName("0442090022-00001");
					deviceInformation1.setNetSuiteId("");  //Invalid Input
					deviceInformation1.setBs_carrier("VERIZON");
					deviceInformation1.setSerial_num("1003");
					
					deviceInformation2.setAccountName("0442090022-00001");
					deviceInformation2.setNetSuiteId("NSV005");
					deviceInformation2.setBs_carrier("VERIZON");
					deviceInformation2.setSerial_num("1005");
					
					deviceInformationArray[0]= deviceInformation1;
					deviceInformationArray[1]= deviceInformation2;
				
					devicesDataArea.setDevices(deviceInformationArray);
					bulkDevices.setDataArea(devicesDataArea);
					bulkDevices.setHeader(header);		
					
					BatchDeviceResponse response = (BatchDeviceResponse) template.requestBody("direct:updateDevicesDetailsBulkActual", bulkDevices);
					System.out.println("Response for update device is..............:" + response);
					assertEquals(response.getResponse().getResponseCode().toString(),"2000");
					assertEquals(response.getDataArea().getSuccessCount().toString(),"1");
					assertEquals(response.getDataArea().getFailedCount().toString(),"1");
				}
				
				//Test case to update Multiple/Bulk Device details with valid data .. 
				@Test
				public void testBulkDeviceUpdateWithValidData() throws Exception{
					
					BulkDevices bulkDevices= new BulkDevices();
					DevicesDataArea devicesDataArea =new DevicesDataArea();	
					Header header = new Header();
					
					header.setSourceName("NetSuit");
					header.setTransactionId("gv123666");
			 		header.setBsCarrier("VERIZON");
			 		header.setApplicationName("WEB");
			 		header.setOrganization("Grant Victor");
			        header.setRegion("USA");
			        header.setTimestamp("2016-03-08T21:49:45");
			        
					DeviceInformation deviceInformation1=new DeviceInformation();
					DeviceInformation deviceInformation2=new DeviceInformation();
					DeviceInformation[] deviceInformationArray = new DeviceInformation[2];
					
					deviceInformation1.setAccountName("0442090022-00001");
					deviceInformation1.setNetSuiteId("NSV004");
					deviceInformation1.setBs_carrier("VERIZON");
					deviceInformation1.setSerial_num("1004"); //updated sr.no.
					
					deviceInformation2.setAccountName("0442090022-00001");
					deviceInformation2.setNetSuiteId("NSV005");
					deviceInformation2.setBs_carrier("VERIZON");
					deviceInformation2.setSerial_num("1006");//updated sr.no
					
					deviceInformationArray[0]= deviceInformation1;
					deviceInformationArray[1]= deviceInformation2;
				
					devicesDataArea.setDevices(deviceInformationArray);
					bulkDevices.setDataArea(devicesDataArea);
					bulkDevices.setHeader(header);		
					
					BatchDeviceResponse response = (BatchDeviceResponse) template.requestBody("direct:updateDevicesDetailsBulkActual", bulkDevices);
					System.out.println("Response for update device is..............:" + response);
					assertEquals(response.getResponse().getResponseCode().toString(),"2000");
					assertEquals(response.getDataArea().getSuccessCount().toString(),"2");
					
				}
				

		/*..................test cases for Bulk Upload Device ends here ..............*/
	
	
	// Test Case for Restore Device
		//@Test
		public void testRestoreDevice() throws Exception {

			RestoreDeviceRequest req = new RestoreDeviceRequest();
			Header header = new Header();

			RestoreDeviceRequestDataArea dataArea = new RestoreDeviceRequestDataArea();

			Devices [] deDevices=new Devices[1];
			Devices ddevices = new Devices();

	        DeviceId[] restoreDeviceIdArray = new DeviceId[1];
	 		 
	 		DeviceId restoreDeviceId= new DeviceId();
	 		
	 		restoreDeviceId.setId("89014103277405946190");
	 		restoreDeviceId.setKind("ghgjg");
	 		
	 		restoreDeviceIdArray[0] = restoreDeviceId;
	 		
	 		ddevices.setDeviceIds(restoreDeviceIdArray);
	 		deDevices[0] = ddevices;
	 		dataArea.setDevices(deDevices);
	 		req.setDataArea(dataArea);
	         
	  		 header.setSourceName("NetSuit");
	  		 header.setTransactionId("cde2131ksjd");
	  		 header.setBsCarrier("VERIZON");
	  		 req.setHeader(header);
	  		 

			System.out.println("Request in Junit Test:" + req);
			testData.setExpectedRestoreDevice(req);
			template.sendBody("direct:restoreDevice", req);
			testData.verifyDeviceRestoreData();

		}

		// Test Case for Suspended Device
			//	@Test
				public void testSuspendDevice() throws Exception {

					SuspendDeviceRequest req = new SuspendDeviceRequest();
					Header header = new Header();

					SuspendDeviceRequestDataArea dataArea = new SuspendDeviceRequestDataArea();

					Devices [] deDevices=new Devices[1];
					Devices ddevices = new Devices();

			        DeviceId[] suspendDeviceIdArray = new DeviceId[1];
			 		 
			 		DeviceId suspendDeviceId= new DeviceId();
			 		
			 		suspendDeviceId.setId("89014103277405946190");
			 		suspendDeviceId.setKind("ghgjg");
			 		
			 		suspendDeviceIdArray[0] = suspendDeviceId;
			 		
			 		ddevices.setDeviceIds(suspendDeviceIdArray);
			 		deDevices[0] = ddevices;
			 		dataArea.setDevices(deDevices);
			 		req.setDataArea(dataArea);
			         
			  		 header.setSourceName("NetSuit");
			  		 header.setTransactionId("cde2131ksjd");
			  		 header.setBsCarrier("VERIZON");
			  		 req.setHeader(header);
			  		 

					System.out.println("Request in Junit Test:" + req);
					testData.setExpectedSuspendDevice(req);
					template.sendBody("direct:restoreDevice", req);
					testData.verifyDeviceSuspendData();

				}
				// test case for device information
				//@Test
				public void testDeviceInformation() throws Exception {

					Header header = new Header();
					DeviceInformationRequest req = new DeviceInformationRequest();

					DeviceInformationRequestDataArea dataArea = new DeviceInformationRequestDataArea();
					
					//deviceIds.setId("89014103277405946190");

					DeviceInformation deviceInformation = new DeviceInformation();
			 		deviceInformation.setAccountName("Test");
			 		
					DeviceDataArea deviceDataArea = new DeviceDataArea();
					deviceDataArea.setDevices(deviceInformation);
					
			 		DeviceId deviceId = new DeviceId();
			 		deviceId.setId("01");
			 		deviceId.setKind("k01");
			 		
			 		dataArea.setDeviceId(deviceId);
			 		
			 		
			        req.setDataArea(dataArea);
			     
			        req.setDataArea(dataArea);
			       
			 		header.setSourceName("KORE"); 				
			 		header.setTransactionId("cde2131ksjd");
			 		 header.setBsCarrier("KORE");
			 		 req.setHeader(header);
			 		 

					// System.out.println("Request in Junit Test:"+req);
					testData.setExpectedDeviceInformation(req);
					template.sendBody("direct:deviceInformationCarrier", req);
					testData.verifyDeviceInformationData();
				}


				//Test case for Activate callback 'Request Id'
				//@Test
				public void testActivateCallback() throws Exception {

					CallBackVerizonRequest callbackReq = new CallBackVerizonRequest();
				
					Header header = new Header();
					callbackReq.setRequestId("R001");
					header.setTransactionId("cde2131ksjd");
			 		header.setBsCarrier("VERIZON");
			 		
					System.out.println("Request in Junit Test:" + callbackReq);
					testData.setExpectedActivateCallback(callbackReq);
					template.sendBody("direct:callbacks", callbackReq);
					testData.verifyActivateCallback();

				}
				

	@After
	public void tearDown() throws Exception {
		if (applicationContext != null) {
			applicationContext.stop();
		}
	}

	protected CamelContext getCamelContext() throws Exception {
		return applicationContext.getBean("camel", CamelContext.class);
	}

}