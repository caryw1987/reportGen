package com.report.gen.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.opensymphony.xwork2.ActionContext;
import com.report.gen.DAO.ReportDAO;
import com.report.gen.DAO.UserDAO;
import com.report.gen.domain.Report;
import com.report.gen.domain.User;
import com.report.gen.util.CommonsMethod;
import com.report.gen.util.PageBean;
import com.report.gen.word.generator.WordGeneratorMain;


@SuppressWarnings("unchecked")
@Service("reportService")
public class ReportService 
{
	public void addReport(Report report)
	{
		List<Report> reportList = reportDAO.findByMasterSampleNo(report.getMasterSampleNo());
		if(reportList.isEmpty())
		{
			Map<String, Object> session = ActionContext.getContext().getSession();
			report.setRecorder((User) session.get("user"));
			String currentTime = CommonsMethod.getCurrentTimeMinute();
			report.setOpLogoutTime(currentTime);
			report.setOverAllStatus(1);
			reportDAO.save(report);
		}
	}
	
	public void addReport(Report report, List<String> repeatedList)
	{
		List<Report> reportList = reportDAO.findByMasterSampleNo(report.getMasterSampleNo());
		if(reportList.isEmpty())
		{
			Map<String, Object> session = ActionContext.getContext().getSession();
			report.setRecorder((User) session.get("user"));
			String currentTime = CommonsMethod.getCurrentTimeMinute();
			report.setOpLogoutTime(currentTime);
			report.setOverAllStatus(1);
			reportDAO.save(report);
		}
		else
		{
			repeatedList.add(report.getMasterSampleNo());
		}
		
	}
	
	public void importReports(File excel, String excelFileName, List<String> repeatedList) throws IOException
	{
//		    String fileType = excelFileName.substring(excelFileName.length()-3, excelFileName.length());
		    String[] excelFileNameArray = excelFileName.split("[.]");
		    String fileType = excelFileNameArray[excelFileNameArray.length-1];
		    InputStream is = new FileInputStream(excel);
		    if("xls".equalsIgnoreCase(fileType))
		    {
		    	POIFSFileSystem fs = new POIFSFileSystem(is);
	            HSSFWorkbook wb = new HSSFWorkbook(fs);
	            HSSFSheet sheet = wb.getSheetAt(0);
	            int rowNum =sheet.getLastRowNum();
	            
	            HSSFRow headerRow = sheet.getRow(8);
	            HashMap<String, Integer> headerMap = new HashMap<String, Integer>();
	            if(headerRow != null){
	            	int columnCount = headerRow.getLastCellNum();
	            	for(int i=0; i<columnCount; i++)
	            	{
	            		String header = headerRow.getCell(i).getStringCellValue();
	            		headerMap.put(header, i);
	            	}
	            }
	            
	            for(int i=9; i< rowNum; i++)
	            {
	            	HSSFRow row = sheet.getRow(i);
	            	if(row == null){
	            		break;
	            	}
	            	
	            	Integer masterSimpleNoIndex = headerMap.get("MASTER SAMPLE NO");
	            	List<Report> reportList = new ArrayList<Report>();
	            	if(masterSimpleNoIndex != null)
	            	{
	            		long masterSimpleNo = 0l;
	            		try
	        			{
	            			if(row.getCell(masterSimpleNoIndex).getCellType() == Cell.CELL_TYPE_NUMERIC){
	            				masterSimpleNo = (long) row.getCell(masterSimpleNoIndex).getNumericCellValue();
	            			}else if(row.getCell(masterSimpleNoIndex).getCellType() == Cell.CELL_TYPE_STRING){
	            				String masterSimpleNoStr = row.getCell(masterSimpleNoIndex).getStringCellValue();
	            				if( !"".equals(masterSimpleNoStr)){
	            					masterSimpleNo = Long.parseLong(masterSimpleNoStr.trim());
	            				}
	            			}
	        			}
	            		catch(IllegalStateException e)
	        			{
	            			if(e.getMessage().equals("Cannot get a numeric value from a text cell"))
	        				{
	        					String strValue = row.getCell(masterSimpleNoIndex).getStringCellValue();
	        					if(!"".equals(strValue))
	        					{
	        						reportList = reportDAO.getByMasterSampleNo(strValue);
	        					}
	        				}
	        			}
		            	if(reportList.isEmpty())
		            	{
		            		continue;
		            	}
	            	}
	            	
	            	Report report = reportList.get(0);
	            	if(report.getOverAllStatus() >=2)
	            	{
	            		repeatedList.add(report.getMasterSampleNo());
	            	}
	        		Map<String, Object> session = ActionContext.getContext().getSession();
	        		report.setRecorder((User) session.get("user"));
	        		String currentTime = CommonsMethod.getCurrentTimeMinute();
	        		report.setOpLogoutTime(currentTime);
	        		report.setOverAllStatus(2);
	        		
	        		
	        		Integer requiredWDIndex= headerMap.get("Required W/D");
	        		if( requiredWDIndex != null && row.getCell(requiredWDIndex)!=null)
	        		{
	        			try
	        			{
	        				long value = (long) row.getCell(requiredWDIndex).getNumericCellValue();
			                report.setRequiredWD(String.valueOf(value));
	        			}
	        			catch(IllegalStateException e)
	        			{
	        				if(e.getMessage().equals("Cannot get a numeric value from a text cell"))
	        				{
	        					String strValue = row.getCell(requiredWDIndex).getStringCellValue();
				                report.setRequiredWD(strValue);
	        				}
	        				else
	        				{
	        					report.setRequiredWD("0");
	        				}
	        			}
	        		}
	        		
//	        		Integer dateInIndex= headerMap.get("Date in");
	        		Integer dateInIndex= headerMap.get("RECEIVED DATE");
	        		if(dateInIndex != null && row.getCell(dateInIndex)!=null)
	        		{
	        			try
	        			{ 
	        				int type = row.getCell(dateInIndex).getCellType();
	        				if(type == Cell.CELL_TYPE_FORMULA)
	        				{
	        					Date dateIn = row.getCell(dateInIndex).getDateCellValue();
	        					if(dateIn != null)
				                {
				                	String dateInStr = CommonsMethod.getTimeDate(dateIn);
				                    report.setDateIn(dateInStr);
				                }
	        				}
	        				else
	        				{
	        					String dateInStr = row.getCell(dateInIndex).getStringCellValue();
	        					SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	        					try {
									Date date=sdf.parse(dateInStr);
									dateInStr = CommonsMethod.getTimeDate(date);
				                    report.setDateIn(dateInStr);
								} catch (ParseException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
	        				}
	        			}
	        			catch(IllegalStateException e)
	        			{
	        				
	        			}
	        		}
	                
	                Integer bvDueDateIndex= headerMap.get("BV Due date");
	                if(bvDueDateIndex!=null && row.getCell(bvDueDateIndex)!=null)
	                {
	                	try
	        			{
	                		int type = row.getCell(bvDueDateIndex).getCellType();
	        				if(type == Cell.CELL_TYPE_FORMULA)
	        				{
	        					Date bvDueDate = row.getCell(bvDueDateIndex).getDateCellValue();
				                if(bvDueDate != null)
				                {
				                	String bvDueDateStr = CommonsMethod.getTimeDate(bvDueDate);
				                    report.setBvDueDate(bvDueDateStr);
				                }
	        				}
	        				else
	        				{
	        					String bvDueDateStr = row.getCell(bvDueDateIndex).getStringCellValue();
	        					SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	        					try {
									Date date=sdf.parse(bvDueDateStr);
									bvDueDateStr = CommonsMethod.getTimeDate(date);
				                    report.setDateIn(bvDueDateStr);
								} catch (ParseException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
	        				}
	                		
	        			}
	                	catch(IllegalStateException e)
	        			{
	        				
	        			}
	                }
	                
	                Integer dueDateIndex= headerMap.get("DUE DATE");
	                if(dueDateIndex!=null && row.getCell(dueDateIndex)!=null)
	                {
	                	try
	        			{
	                		int type = row.getCell(dueDateIndex).getCellType();
	                		if(type == Cell.CELL_TYPE_FORMULA)
	        				{
	                			Date dueDate = row.getCell(dueDateIndex).getDateCellValue();
				                if(dueDate != null)
				                {
				                	String dueDateStr = CommonsMethod.getTimeDate(dueDate);
				                    report.setDueDate(dueDateStr);
				                }
	        				}
	                		else
	                		{
	                			String dueDateStr = row.getCell(dueDateIndex).getStringCellValue();
	        					SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	        					try {
									Date date=sdf.parse(dueDateStr);
									dueDateStr = CommonsMethod.getTimeDate(date);
				                    report.setDateIn(dueDateStr);
								} catch (ParseException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
	                		}
	        			}
	                	catch(IllegalStateException e)
	        			{
	        				
	        			}
	                }
	                
	                Integer dateOutIndex= headerMap.get("DATE OUT");
	                if(dateOutIndex!=null && row.getCell(dateOutIndex)!=null)
	                {
	                	try
	        			{
	                		int type = row.getCell(dateOutIndex).getCellType();
	                		if(type == Cell.CELL_TYPE_FORMULA)
	                		{
	                			Date dateOut = row.getCell(dateOutIndex).getDateCellValue();
	                			if(dateOut != null)
				                {
				                	String dateOutStr = CommonsMethod.getTimeDate(dateOut);
				                    report.setDateOut(dateOutStr);
				                }
	                		}
	                		else
	                		{
	                			String dateOutStr = row.getCell(dateOutIndex).getStringCellValue();
	        					SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	        					try {
									Date date=sdf.parse(dateOutStr);
									dateOutStr = CommonsMethod.getTimeDate(date);
				                    report.setDateIn(dateOutStr);
								} catch (ParseException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
	                		}
			                
	        			}
	                	catch(IllegalStateException e)
	        			{
	        				
	        			}
	                }
	                
	                Integer clientIndex = headerMap.get("CLIENT");
	                if(clientIndex!= null && row.getCell(clientIndex)!=null)
	                {
	                	String client = row.getCell(clientIndex).getStringCellValue();
		                report.setClient(client);
	                }
	                
//	                Integer poNoIndex = headerMap.get("PO NO");
	                Integer poNoIndex = headerMap.get("PO#");
	                if(poNoIndex != null && row.getCell(poNoIndex)!=null)
	                {
	                	try
	                	{
	                		String poNo = row.getCell(poNoIndex).getStringCellValue();
			                report.setPoNo(String.valueOf(poNo));
	                	}
	                	catch(IllegalStateException e)
	                	{
	                		long poNo = (long) row.getCell(poNoIndex).getNumericCellValue();
			                report.setPoNo(String.valueOf(poNo));
//	                		report.setPoNo("/");
	                	}
	                	
	                }
	                
	                Integer billingToIndex = headerMap.get("BILL TO");
	                if(billingToIndex !=null && row.getCell(billingToIndex)!=null)
	                {
	                	String billingTo = row.getCell(billingToIndex).getStringCellValue();
		                report.setBillingTo(billingTo);
	                }
	                
	                Integer invoiceTypeIndex = headerMap.get("INVOICE TYPE");
	                if(invoiceTypeIndex !=null && row.getCell(invoiceTypeIndex)!=null)
	                {
	                	String invoiceTypeStr = row.getCell(invoiceTypeIndex).getStringCellValue();
		                Integer invoiceType;
		                if(invoiceTypeStr.equalsIgnoreCase("DAILY"))
		                {
		                	invoiceType = new Integer(1);
		                }
		                else if(invoiceTypeStr.equalsIgnoreCase("MONTHLY"))
		                {
		                	invoiceType = new Integer(2);
		                }
		                else
		                {
		                	invoiceType = new Integer(3);
		                }
		                report.setInvoiceType(invoiceType);
	                }
	                
//	                report.setMasterSampleNo(String.valueOf(masterSimpleNo));

	                Integer reportNoIndex = headerMap.get("MASTER SAMPLE NO");
	                String reportNo = row.getCell(reportNoIndex).getStringCellValue();
	                report.setReportNo(reportNo);
	                
	                Integer specialItemIndex = headerMap.get("STYLE");
	                if(specialItemIndex!= null && row.getCell(specialItemIndex)!=null)
	                {
	                	String specialItem = row.getCell(specialItemIndex).getStringCellValue();
		                report.setSpecialItem(specialItem);
	                }
	                
	                Integer vendorIndex = headerMap.get("VENDOR");
	                if(vendorIndex!=null && row.getCell(vendorIndex)!=null)
	                {
	                	String vendor = row.getCell(vendorIndex).getStringCellValue();
		                report.setVendor(vendor);
	                }
	                
	                Integer sampleDescIndex = headerMap.get("MAIN DESCRIPTION");
	                if(sampleDescIndex!=null && row.getCell(sampleDescIndex)!=null)
	                {
	                	String sampleDesc = row.getCell(sampleDescIndex).getStringCellValue();
		                report.setSampleDescription(sampleDesc);
	                }
	                
	                Integer priceIndex = headerMap.get("PRICE (LOCAL CURRENCY)");
	                if(priceIndex!=null && row.getCell(priceIndex)!=null)
	                {
	                	try
	                	{
	                		float price = (float)row.getCell(priceIndex).getNumericCellValue();
			                report.setPrice(price);
	                	}
	                	catch(IllegalStateException e)
	                	{
	                		
	                	}
	                	
	                }
	                
	                Integer groupIndex = headerMap.get("Group");
	                if(groupIndex !=null && row.getCell(groupIndex)!=null)
	                {
	                	String group = row.getCell(groupIndex).getStringCellValue();
		                report.setTestGroup(group);
	                }
	                
	                Integer statusIndex = headerMap.get("STATUS");
	                if(statusIndex!=null && row.getCell(statusIndex)!=null)
	                {
	                	String statusStr = row.getCell(statusIndex).getStringCellValue();
		                Integer status;
		                if(statusStr.equalsIgnoreCase("active"))
		                {
		                	status = new Integer(1);
		                }
		                else if(statusStr.equalsIgnoreCase("on-hold"))
		                {
		                	status = new Integer(2);
		                }
		                else if(statusStr.equalsIgnoreCase("cancel"))
		                {
		                	status = new Integer(3);
		                }
		                else
		                {
		                	status = new Integer(4);
		                }
		                report.setStatus(status);
	                }
	                
//	                Integer holdingReasonIndex = headerMap.get("Holding Reason");
//	                if(holdingReasonIndex!=null && row.getCell(holdingReasonIndex)!=null)
//	                {
//	                	String holdingReason = row.getCell(holdingReasonIndex).getStringCellValue();
//		                report.setHoldingReason(holdingReason);
//	                }
//	                
//	                Integer lateReasonIndex = headerMap.get("Late Reason");
//	                if(lateReasonIndex!= null && row.getCell(lateReasonIndex)!=null)
//	                {
//	                	String lateReason = row.getCell(lateReasonIndex).getStringCellValue();
//		                report.setLateReason(lateReason);
//	                }
	                
	                reportDAO.attachDirty(report);
	            }
		    }
		    else if("xlsx".equalsIgnoreCase(fileType))
		    {
		    	XSSFWorkbook xssfWb = new XSSFWorkbook(is);
	            XSSFSheet sheet = xssfWb.getSheetAt(0);
	            int rowNum =sheet.getLastRowNum();
	            XSSFRow headerRow = sheet.getRow(8);
	            HashMap<String, Integer> headerMap = new HashMap<String, Integer>();
	            if(headerRow != null){
	            	int columnCount = headerRow.getLastCellNum();
	            	for(int i=0; i<columnCount; i++)
	            	{
	            		String header = headerRow.getCell(i).getStringCellValue();
	            		headerMap.put(header, i);
	            	}
	            }
	            
	            for(int i=9; i< rowNum; i++)
	            {
	            	XSSFRow row = sheet.getRow(i);
	            	if(row == null){
	            		break;
	            	}
	            	
	            	Integer masterSimpleNoIndex = headerMap.get("MASTER SAMPLE NO");
	            	List<Report> reportList = new ArrayList<Report>();
	            	if(masterSimpleNoIndex != null)
	            	{
	            		try
	        			{
	            			long masterSimpleNo = 0l;
	            			if(row.getCell(masterSimpleNoIndex).getCellType() == Cell.CELL_TYPE_NUMERIC){
	            				masterSimpleNo = (long) row.getCell(masterSimpleNoIndex).getNumericCellValue();
	            			}else if(row.getCell(masterSimpleNoIndex).getCellType() == Cell.CELL_TYPE_STRING){
	            				String masterSimpleNoStr = row.getCell(masterSimpleNoIndex).getStringCellValue();
	            				if( !"".equals(masterSimpleNoStr)){
	            					masterSimpleNo = Long.parseLong(masterSimpleNoStr.trim());
	            				}
	            			}
	        			}
	            		catch(IllegalStateException e)
	        			{
	            			if(e.getMessage().equals("Cannot get a numeric value from a text cell"))
	        				{
	        					String strValue = row.getCell(masterSimpleNoIndex).getStringCellValue();
	        					if(!"".equals(strValue))
	        					{
	        						reportList = reportDAO.getByMasterSampleNo(strValue);
	        					}
	        				}
	        			}
		            	if(reportList.isEmpty())
		            	{
		            		continue;
		            	}
	            	}
	            	
	            	Report report = reportList.get(0);
	            	if(report.getOverAllStatus() >=2)
	            	{
	            		repeatedList.add(report.getMasterSampleNo());
	            	}
	        		Map<String, Object> session = ActionContext.getContext().getSession();
	        		report.setRecorder((User) session.get("user"));
	        		String currentTime = CommonsMethod.getCurrentTimeMinute();
	        		report.setOpLogoutTime(currentTime);
	        		report.setOverAllStatus(2);
	        		
	        		
	        		Integer requiredWDIndex= headerMap.get("Required W/D");
	        		if( requiredWDIndex != null && row.getCell(requiredWDIndex)!=null)
	        		{
	        			try
	        			{
	        				long value = (long) row.getCell(requiredWDIndex).getNumericCellValue();
			                report.setRequiredWD(String.valueOf(value));
	        			}
	        			catch(IllegalStateException e)
	        			{
	        				if(e.getMessage().equals("Cannot get a numeric value from a text cell"))
	        				{
	        					String strValue = row.getCell(requiredWDIndex).getStringCellValue();
				                report.setRequiredWD(strValue);
	        				}
	        				else
	        				{
	        					report.setRequiredWD("0");
	        				}
	        			}
	        		}
	        		
//	        		Integer dateInIndex= headerMap.get("Date in");
	        		Integer dateInIndex= headerMap.get("RECEIVED DATE");
	        		if(dateInIndex != null && row.getCell(dateInIndex)!=null)
	        		{
	        			try
	        			{ 
	        				int type = row.getCell(dateInIndex).getCellType();
	        				if(type == Cell.CELL_TYPE_FORMULA)
	        				{
	        					Date dateIn = row.getCell(dateInIndex).getDateCellValue();
	        					if(dateIn != null)
				                {
				                	String dateInStr = CommonsMethod.getTimeDate(dateIn);
				                    report.setDateIn(dateInStr);
				                }
	        				}
	        				else
	        				{
	        					String dateInStr = row.getCell(dateInIndex).getStringCellValue();
	        					SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	        					try {
									Date date=sdf.parse(dateInStr);
									dateInStr = CommonsMethod.getTimeDate(date);
				                    report.setDateIn(dateInStr);
								} catch (ParseException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
	        				}
	        			}
	        			catch(IllegalStateException e)
	        			{
	        				
	        			}
	        		}
	                
	                Integer bvDueDateIndex= headerMap.get("BV Due date");
	                if(bvDueDateIndex!=null && row.getCell(bvDueDateIndex)!=null)
	                {
	                	try
	        			{
	                		int type = row.getCell(bvDueDateIndex).getCellType();
	        				if(type == Cell.CELL_TYPE_FORMULA)
	        				{
	        					Date bvDueDate = row.getCell(bvDueDateIndex).getDateCellValue();
				                if(bvDueDate != null)
				                {
				                	String bvDueDateStr = CommonsMethod.getTimeDate(bvDueDate);
				                    report.setBvDueDate(bvDueDateStr);
				                }
	        				}
	        				else
	        				{
	        					String bvDueDateStr = row.getCell(bvDueDateIndex).getStringCellValue();
	        					SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	        					try {
									Date date=sdf.parse(bvDueDateStr);
									bvDueDateStr = CommonsMethod.getTimeDate(date);
				                    report.setDateIn(bvDueDateStr);
								} catch (ParseException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
	        				}
	                		
	        			}
	                	catch(IllegalStateException e)
	        			{
	        				
	        			}
	                }
	                
	                Integer dueDateIndex= headerMap.get("DUE DATE");
	                if(dueDateIndex!=null && row.getCell(dueDateIndex)!=null)
	                {
	                	try
	        			{
	                		int type = row.getCell(dueDateIndex).getCellType();
	                		if(type == Cell.CELL_TYPE_FORMULA)
	        				{
	                			Date dueDate = row.getCell(dueDateIndex).getDateCellValue();
				                if(dueDate != null)
				                {
				                	String dueDateStr = CommonsMethod.getTimeDate(dueDate);
				                    report.setDueDate(dueDateStr);
				                }
	        				}
	                		else
	                		{
	                			String dueDateStr = row.getCell(dueDateIndex).getStringCellValue();
	        					SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	        					try {
									Date date=sdf.parse(dueDateStr);
									dueDateStr = CommonsMethod.getTimeDate(date);
				                    report.setDateIn(dueDateStr);
								} catch (ParseException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
	                		}
	        			}
	                	catch(IllegalStateException e)
	        			{
	        				
	        			}
	                }
	                
	                Integer dateOutIndex= headerMap.get("DATE OUT");
	                if(dateOutIndex!=null && row.getCell(dateOutIndex)!=null)
	                {
	                	try
	        			{
	                		int type = row.getCell(dateOutIndex).getCellType();
	                		if(type == Cell.CELL_TYPE_FORMULA)
	                		{
	                			Date dateOut = row.getCell(dateOutIndex).getDateCellValue();
	                			if(dateOut != null)
				                {
				                	String dateOutStr = CommonsMethod.getTimeDate(dateOut);
				                    report.setDateOut(dateOutStr);
				                }
	                		}
	                		else
	                		{
	                			String dateOutStr = row.getCell(dateOutIndex).getStringCellValue();
	        					SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	        					try {
									Date date=sdf.parse(dateOutStr);
									dateOutStr = CommonsMethod.getTimeDate(date);
				                    report.setDateIn(dateOutStr);
								} catch (ParseException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
	                		}
			                
	        			}
	                	catch(IllegalStateException e)
	        			{
	        				
	        			}
	                }
	                
	                Integer clientIndex = headerMap.get("CLIENT");
	                if(clientIndex!= null && row.getCell(clientIndex)!=null)
	                {
	                	String client = row.getCell(clientIndex).getStringCellValue();
		                report.setClient(client);
	                }
	                
//	                Integer poNoIndex = headerMap.get("PO NO");
	                Integer poNoIndex = headerMap.get("PO#");
	                if(poNoIndex != null && row.getCell(poNoIndex)!=null)
	                {
	                	try
	                	{
	                		String poNo = row.getCell(poNoIndex).getStringCellValue();
			                report.setPoNo(String.valueOf(poNo));
	                	}
	                	catch(IllegalStateException e)
	                	{
	                		long poNo = (long) row.getCell(poNoIndex).getNumericCellValue();
			                report.setPoNo(String.valueOf(poNo));
//	                		report.setPoNo("/");
	                	}
	                	
	                }
	                
	                Integer billingToIndex = headerMap.get("BILL TO");
	                if(billingToIndex !=null && row.getCell(billingToIndex)!=null)
	                {
	                	String billingTo = row.getCell(billingToIndex).getStringCellValue();
		                report.setBillingTo(billingTo);
	                }
	                
	                Integer invoiceTypeIndex = headerMap.get("INVOICE TYPE");
	                if(invoiceTypeIndex !=null && row.getCell(invoiceTypeIndex)!=null)
	                {
	                	String invoiceTypeStr = row.getCell(invoiceTypeIndex).getStringCellValue();
		                Integer invoiceType;
		                if(invoiceTypeStr.equalsIgnoreCase("DAILY"))
		                {
		                	invoiceType = new Integer(1);
		                }
		                else if(invoiceTypeStr.equalsIgnoreCase("MONTHLY"))
		                {
		                	invoiceType = new Integer(2);
		                }
		                else
		                {
		                	invoiceType = new Integer(3);
		                }
		                report.setInvoiceType(invoiceType);
	                }
	                
//	                report.setMasterSampleNo(String.valueOf(masterSimpleNo));

	                Integer reportNoIndex = headerMap.get("MASTER SAMPLE NO");
	                String reportNo = row.getCell(reportNoIndex).getStringCellValue();
	                report.setReportNo(reportNo);
	                
	                Integer specialItemIndex = headerMap.get("STYLE");
	                if(specialItemIndex!= null && row.getCell(specialItemIndex)!=null)
	                {
	                	String specialItem = row.getCell(specialItemIndex).getStringCellValue();
		                report.setSpecialItem(specialItem);
	                }
	                
	                Integer vendorIndex = headerMap.get("VENDOR");
	                if(vendorIndex!=null && row.getCell(vendorIndex)!=null)
	                {
	                	String vendor = row.getCell(vendorIndex).getStringCellValue();
		                report.setVendor(vendor);
	                }
	                
	                Integer sampleDescIndex = headerMap.get("MAIN DESCRIPTION");
	                if(sampleDescIndex!=null && row.getCell(sampleDescIndex)!=null)
	                {
	                	String sampleDesc = row.getCell(sampleDescIndex).getStringCellValue();
		                report.setSampleDescription(sampleDesc);
	                }
	                
	                Integer priceIndex = headerMap.get("PRICE (LOCAL CURRENCY)");
	                if(priceIndex!=null && row.getCell(priceIndex)!=null)
	                {
	                	try
	                	{
	                		float price = (float)row.getCell(priceIndex).getNumericCellValue();
			                report.setPrice(price);
	                	}
	                	catch(IllegalStateException e)
	                	{
	                		
	                	}
	                	
	                }
	                
	                Integer groupIndex = headerMap.get("Group");
	                if(groupIndex !=null && row.getCell(groupIndex)!=null)
	                {
	                	String group = row.getCell(groupIndex).getStringCellValue();
		                report.setTestGroup(group);
	                }
	                
	                Integer statusIndex = headerMap.get("STATUS");
	                if(statusIndex!=null && row.getCell(statusIndex)!=null)
	                {
	                	String statusStr = row.getCell(statusIndex).getStringCellValue();
		                Integer status;
		                if(statusStr.equalsIgnoreCase("active"))
		                {
		                	status = new Integer(1);
		                }
		                else if(statusStr.equalsIgnoreCase("on-hold"))
		                {
		                	status = new Integer(2);
		                }
		                else if(statusStr.equalsIgnoreCase("cancel"))
		                {
		                	status = new Integer(3);
		                }
		                else
		                {
		                	status = new Integer(4);
		                }
		                report.setStatus(status);
	                }
	                
//	                Integer holdingReasonIndex = headerMap.get("Holding Reason");
//	                if(holdingReasonIndex!=null && row.getCell(holdingReasonIndex)!=null)
//	                {
//	                	String holdingReason = row.getCell(holdingReasonIndex).getStringCellValue();
//		                report.setHoldingReason(holdingReason);
//	                }
//	                
//	                Integer lateReasonIndex = headerMap.get("Late Reason");
//	                if(lateReasonIndex!= null && row.getCell(lateReasonIndex)!=null)
//	                {
//	                	String lateReason = row.getCell(lateReasonIndex).getStringCellValue();
//		                report.setLateReason(lateReason);
//	                }
	                
	                reportDAO.attachDirty(report);
	            }
		    }
	}
	
	public Report findReportByPuid(String puid)
	{
		return reportDAO.findById(puid);
	}
	
	
	
	public PageBean findAllReports4Recorder(String currentPage, String pageSize)
	{
		int pageSizeInt = Integer.parseInt(pageSize);
		int currentPageInt = Integer.parseInt(currentPage);
		final int offset = PageBean.countOffset(pageSizeInt, currentPageInt);
		int allRows = reportDAO.getRowCount4Recorder();
		int totalPage = PageBean.countTotalPage(pageSizeInt, allRows);
		List<Report> list= reportDAO.findReportsByPage4Recorder(offset, pageSizeInt);
		PageBean pageBean = new PageBean();
		
		pageBean.setPageSize(pageSizeInt);
		pageBean.setCurrentPage(currentPageInt);
		pageBean.setAllRow(allRows);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.init();
		return pageBean;
	}
	
	public PageBean findAllReports4EntryPerson(String currentPage, String pageSize)
	{
		int pageSizeInt = Integer.parseInt(pageSize);
		int currentPageInt = Integer.parseInt(currentPage);
		final int offset = PageBean.countOffset(pageSizeInt, currentPageInt);
		int allRows = reportDAO.getRowCount4EntryPerson();
		int totalPage = PageBean.countTotalPage(pageSizeInt, allRows);
		List<Report> list= reportDAO.findReportsByPage4EntryPerson(offset, pageSizeInt);
		PageBean pageBean = new PageBean();
		
		pageBean.setPageSize(pageSizeInt);
		pageBean.setCurrentPage(currentPageInt);
		pageBean.setAllRow(allRows);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.init();
		return pageBean;
	}
	
	public PageBean findAllReports4ResultLogin(String currentPage, String pageSize)
	{
		int pageSizeInt = Integer.parseInt(pageSize);
		int currentPageInt = Integer.parseInt(currentPage);
		final int offset = PageBean.countOffset(pageSizeInt, currentPageInt);
		int allRows = reportDAO.getRowCount4ResultLogin();
		int totalPage = PageBean.countTotalPage(pageSizeInt, allRows);
		List<Report> list= reportDAO.findReportsByPage4ResultLogin(offset, pageSizeInt);
		PageBean pageBean = new PageBean();
		
		pageBean.setPageSize(pageSizeInt);
		pageBean.setCurrentPage(currentPageInt);
		pageBean.setAllRow(allRows);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.init();
		return pageBean;
	}
	
	public PageBean findAllReports4GenWord(String currentPage, String pageSize)
	{
		int pageSizeInt = Integer.parseInt(pageSize);
		int currentPageInt = Integer.parseInt(currentPage);
		final int offset = PageBean.countOffset(pageSizeInt, currentPageInt);
		int allRows = reportDAO.getRowCount4GenWord();
		int totalPage = PageBean.countTotalPage(pageSizeInt, allRows);
		List<Report> list= reportDAO.findReportsByPage4GenWord(offset, pageSizeInt);
		PageBean pageBean = new PageBean();
		
		pageBean.setPageSize(pageSizeInt);
		pageBean.setCurrentPage(currentPageInt);
		pageBean.setAllRow(allRows);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.init();
		return pageBean;
	}
	
	public PageBean findAllReports4Check(String currentPage, String pageSize)
	{
		int pageSizeInt = Integer.parseInt(pageSize);
		int currentPageInt = Integer.parseInt(currentPage);
		final int offset = PageBean.countOffset(pageSizeInt, currentPageInt);
		int allRows = reportDAO.getRowCount4Check();
		int totalPage = PageBean.countTotalPage(pageSizeInt, allRows);
		List<Report> list= reportDAO.findReportsByPage4Check(offset, pageSizeInt);
		PageBean pageBean = new PageBean();
		
		pageBean.setPageSize(pageSizeInt);
		pageBean.setCurrentPage(currentPageInt);
		pageBean.setAllRow(allRows);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.init();
		return pageBean;
	}
	
	public PageBean findAllReports4Send(String currentPage, String pageSize)
	{
		int pageSizeInt = Integer.parseInt(pageSize);
		int currentPageInt = Integer.parseInt(currentPage);
		final int offset = PageBean.countOffset(pageSizeInt, currentPageInt);
		int allRows = reportDAO.getRowCount4Send();
		int totalPage = PageBean.countTotalPage(pageSizeInt, allRows);
		List<Report> list= reportDAO.findReportsByPage4Send(offset, pageSizeInt);
		PageBean pageBean = new PageBean();
		
		pageBean.setPageSize(pageSizeInt);
		pageBean.setCurrentPage(currentPageInt);
		pageBean.setAllRow(allRows);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.init();
		return pageBean;
	}
	
	public PageBean findAllReports4AddPrice(String currentPage, String pageSize)
	{
		int pageSizeInt = Integer.parseInt(pageSize);
		int currentPageInt = Integer.parseInt(currentPage);
		final int offset = PageBean.countOffset(pageSizeInt, currentPageInt);
		int allRows = reportDAO.getRowCount4AddPrice();
		int totalPage = PageBean.countTotalPage(pageSizeInt, allRows);
		List<Report> list= reportDAO.findReportsByPage4AddPrice(offset, pageSizeInt);
		PageBean pageBean = new PageBean();
		
		pageBean.setPageSize(pageSizeInt);
		pageBean.setCurrentPage(currentPageInt);
		pageBean.setAllRow(allRows);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.init();
		return pageBean;
	}
	
	public PageBean findAllReports4Statistics(String currentPage, String pageSize)
	{
		int pageSizeInt = Integer.parseInt(pageSize);
		int currentPageInt = Integer.parseInt(currentPage);
		final int offset = PageBean.countOffset(pageSizeInt, currentPageInt);
		int allRows = reportDAO.getRowCount4Statistics();
		int totalPage = PageBean.countTotalPage(pageSizeInt, allRows);
		List<Report> list= reportDAO.findReportsByPage4Statistics(offset, pageSizeInt);
		PageBean pageBean = new PageBean();
		
		pageBean.setPageSize(pageSizeInt);
		pageBean.setCurrentPage(currentPageInt);
		pageBean.setAllRow(allRows);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.init();
		return pageBean;
	}
	
	public PageBean findAllReports(String currentPage, String pageSize)
	{
		int pageSizeInt = Integer.parseInt(pageSize);
		int currentPageInt = Integer.parseInt(currentPage);
		final int offset = PageBean.countOffset(pageSizeInt, currentPageInt);
		int allRows = reportDAO.getAllRowCount();
		int totalPage = PageBean.countTotalPage(pageSizeInt, allRows);
		List<Report> list= reportDAO.findReportsByPage(offset, pageSizeInt);
		PageBean pageBean = new PageBean();
		
		pageBean.setPageSize(pageSizeInt);
		pageBean.setCurrentPage(currentPageInt);
		pageBean.setAllRow(allRows);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.init();
		return pageBean;
	}
	
	public void deleteReport(Report report)
	{
		reportDAO.delete(report);
	}
	
	public void editReport4Recorder(Report report)
	{
		Report savedReport = reportDAO.findById(report.getPuid());
		savedReport.setDepartment(report.getDepartment());
		savedReport.setMasterSampleNo(report.getMasterSampleNo());
		savedReport.setOverAllStatus(1);
		reportDAO.attachDirty(savedReport);	
	}
	
	public void editReport4EntryPerson(Report report)
	{
		Report savedReport = reportDAO.findById(report.getPuid());
		savedReport.setRequiredWD(report.getRequiredWD());
		savedReport.setClient(report.getClient());
		savedReport.setPoNo(report.getPoNo());
		savedReport.setBillingTo(report.getBillingTo());
		savedReport.setInvoiceType(report.getInvoiceType());
		savedReport.setReportNo(report.getReportNo());
		savedReport.setSpecialItem(report.getSpecialItem());
		savedReport.setVendor(report.getVendor());
		savedReport.setSampleDescription(report.getSampleDescription());
		savedReport.setTestGroup(report.getTestGroup());
		savedReport.setStatus(report.getStatus());
		savedReport.setHoldingReason(report.getHoldingReason());
		savedReport.setLateReason(report.getLateReason());
		
		String dateIn = report.getDateIn().replace("-", "").trim();
		savedReport.setDateIn(dateIn);
		String bvDueDate = report.getDateIn().replace("-", "").trim();
		savedReport.setBvDueDate(bvDueDate);
		String dueDate = report.getDateIn().replace("-", "").trim();
		savedReport.setDueDate(dueDate);
		savedReport.setOverAllStatus(2);
		reportDAO.attachDirty(savedReport);	
	}
	
	public void setResultLogin(String reportId)
	{
		Report savedReport = reportDAO.findById(reportId);
		savedReport.setResultLoginStatus(1);
		savedReport.setResultLoginTime(CommonsMethod.getToday());
		savedReport.setOverAllStatus(3);
		reportDAO.attachDirty(savedReport);	
	}
	
	public void setResultLoginPending(String reportId)
	{
		Report savedReport = reportDAO.findById(reportId);
		savedReport.setResultLoginStatus(2);
		String preTime = savedReport.getResultLoginPendingTime();
		String newTime;
		if(preTime==null || preTime.equals(""))
		{
			newTime = CommonsMethod.getToday();
		}
		else
		{
			String[] times = preTime.split(":");
			if(times.length < 10)
			{
				newTime = preTime+":" + CommonsMethod.getToday();
			}
			else
			{
				times[0]="";
				newTime = times[1];
				for(int i =2; i<10; i++)
				{
					newTime = newTime+":"+times[i];
				}
				newTime += CommonsMethod.getToday();
			}
		}
		savedReport.setOverAllStatus(3);
		savedReport.setResultLoginPendingTime(newTime);
		reportDAO.attachDirty(savedReport);	
	}
	
	public void setCheckOK(String reportId)
	{
		Report savedReport = reportDAO.findById(reportId);
		savedReport.setCheckStatus(1);
		savedReport.setDateOut(CommonsMethod.getToday());
		savedReport.setOverAllStatus(5);
		reportDAO.attachDirty(savedReport);	
	}
	
	public void setCheckNOK(String reportId)
	{
		Report savedReport = reportDAO.findById(reportId);
		savedReport.setCheckStatus(2);
		String preTime = savedReport.getCheckFailedTime();
		String newTime;
		if(preTime==null || preTime.equals(""))
		{
			newTime = CommonsMethod.getToday();
		}
		else
		{
			String[] times = preTime.split(":");
			if(times.length < 10)
			{
				newTime = preTime+":" + CommonsMethod.getToday();
			}
			else
			{
				times[0]="";
				newTime = times[1];
				for(int i =2; i<10; i++)
				{
					newTime = newTime+":"+times[i];
				}
				newTime += CommonsMethod.getToday();
			}
		}
		savedReport.setOverAllStatus(5);
		savedReport.setCheckFailedTime(newTime);
		reportDAO.attachDirty(savedReport);	
	}
	
	public void setDone(String reportId)
	{
		Report savedReport = reportDAO.findById(reportId);
		savedReport.setGenerateStatus(1);
		reportDAO.attachDirty(savedReport);	
	}
	
	public void setUnDone(String reportId)
	{
		Report savedReport = reportDAO.findById(reportId);
		savedReport.setGenerateStatus(2);
		savedReport.setGenerateEndTime(CommonsMethod.getCurrentTimeMinute());
		reportDAO.attachDirty(savedReport);	
	}
	
	public void changeDeparment4SelectedReports(String reportId, String[] departmentArray)
	{
		String changedDepartment = departmentArray[0] ;
		for(int i=1; i< departmentArray.length; i++)
		{
			changedDepartment = changedDepartment +"-"+ departmentArray[i];
		}
		Report savedReport = reportDAO.findById(reportId);
		savedReport.setDepartment(changedDepartment);
		reportDAO.attachDirty(savedReport);	
	}
	
	public void sendReport(String reportId)
	{
		Report savedReport = reportDAO.findById(reportId);
		String dateOut = savedReport.getDateOut();
		String vendor = savedReport.getVendor();
		String path = savedReport.getReportPath();
		String newPath = sentRepoetLocation + dateOut + File.separator + vendor + File.separator;
		File wordFile = new File(path);
		String wordFileName = path.substring(path.lastIndexOf(File.separator)+1);
		File newPathFile = new File(newPath);
		if(wordFile.exists())
		{
			if(!newPathFile.exists())
			{
				newPathFile.mkdirs();
			}
			wordFile.renameTo(new File(newPath+wordFileName));
		}
		savedReport.setReportPath(newPath+wordFileName);
		savedReport.setReportRelativePath("/reportView/"+dateOut+"/"+ vendor +"/"+wordFileName);
		savedReport.setSendStatus(1);
		savedReport.setOverAllStatus(6);
		reportDAO.attachDirty(savedReport);	
	}
	
	public void addPrice(Report report)
	{
		Report savedReport = reportDAO.findById(report.getPuid());
		savedReport.setMoneyType(report.getMoneyType());
		if(report.getMoneyType()==3)
		{
			savedReport.setOtherMoneyType(report.getOtherMoneyType());
		}
		savedReport.setOverAllStatus(7);
		savedReport.setPrice(report.getPrice());
		reportDAO.attachDirty(savedReport);	
	}
	
	public void setPrice(String reportId, float price)
	{
		Report savedReport = reportDAO.findById(reportId);
		savedReport.setOverAllStatus(7);
		savedReport.setPrice(price);
		reportDAO.attachDirty(savedReport);	
	}
	
	public void changeMoneyType4SelectedReports(String reportId, int changedMoneyType, String changedOtherMoneyType)
	{
		Report savedReport = reportDAO.findById(reportId);
		savedReport.setMoneyType(changedMoneyType);
		if(changedMoneyType==3)
		{
			savedReport.setOtherMoneyType(changedOtherMoneyType);
		}
		savedReport.setOverAllStatus(7);
		reportDAO.attachDirty(savedReport);	
	}
	
	public void generateReport(Report report, HashMap descrptionTblmap, String[] wordAttrComponent, 
			String[] wordAttrLead, String[] wordAttrCadmium, File image, String imageFileName)
	{
		Report savedReport = reportDAO.findById(report.getPuid());

		if(report.getSpecialItem()!=null)
		{
			descrptionTblmap.put("Style No.:", report.getSpecialItem());
		}
		else
		{
			if(savedReport.getSpecialItem()!=null)
			{
				descrptionTblmap.put("Style No.:", savedReport.getSpecialItem());
			}	
		}
		
		if(report.getPoNo()!=null)
		{
			descrptionTblmap.put("P.O. No.:", report.getPoNo());
		}
		else
		{
			if(savedReport.getPoNo()!=null)
			{
				descrptionTblmap.put("P.O. No.:", savedReport.getPoNo());
			}
		}
		
		if(report.getDateIn()!=null)
		{
			descrptionTblmap.put("dateIn", report.getDateIn());
		}
		else
		{
			if(savedReport.getDateIn()!=null)
			{
				descrptionTblmap.put("dateIn", savedReport.getDateIn());
			}
		}
		
		if(report.getVendor()!=null)
		{
			descrptionTblmap.put("Vendor:",report.getVendor());
		}
		else
		{
			if(savedReport.getVendor()!=null)
			{
				descrptionTblmap.put("Vendor:",savedReport.getVendor());
			}
		}
		
		
		Map<String, Object> session = ActionContext.getContext().getSession();
		User user = (User) session.get("user");
		WordGeneratorMain generator = new WordGeneratorMain(descrptionTblmap, wordAttrComponent, 
				wordAttrLead, wordAttrCadmium, image, imageFileName, user.getName(), savedReport.getMasterSampleNo(), generatedLocation);
		
		String wordFileName = generator.process();
		String storedPath = generatedLocation+wordFileName;
		savedReport.setGenerateStartTime(CommonsMethod.getCurrentTimeMinute());
		savedReport.setGenerateEndTime(CommonsMethod.getCurrentTimeMinute());
		savedReport.setEngineer(user);
		savedReport.setGenerateStatus(1);
		savedReport.setReportPath(storedPath);
		savedReport.setOverAllStatus(4);
		savedReport.setReportRelativePath("/reportView4Ck/"+wordFileName);
		reportDAO.attachDirty(savedReport);	
	}
	
	public void generateWordOnly(String wordAttrMasterSampleNo, HashMap descrptionTblmap, String[] wordAttrComponent, 
			String[] wordAttrLead, String[] wordAttrCadmium, File image, String imageFileName)
	{		
		Map<String, Object> session = ActionContext.getContext().getSession();
		User user = (User) session.get("user");
		WordGeneratorMain generator = new WordGeneratorMain(descrptionTblmap, wordAttrComponent, 
				wordAttrLead, wordAttrCadmium, image, imageFileName, user.getName(), wordAttrMasterSampleNo, generatedLocation);
		generator.process();
	}
	
	public PageBean searchByVendorAndCheckDate(String vendorKeyWords, String checkDateKeyWords, String currentPage, String pageSize, int sortType)
	{
		int pageSizeInt = Integer.parseInt(pageSize);
		int currentPageInt = Integer.parseInt(currentPage);
		final int offset = PageBean.countOffset(pageSizeInt, currentPageInt);
		int allRows = reportDAO.getRowCountByVendorAndCheckDate(vendorKeyWords, checkDateKeyWords);
		int totalPage = PageBean.countTotalPage(pageSizeInt, allRows);
		List<User> list= reportDAO.getReportByVendorAndCheckDate(vendorKeyWords, checkDateKeyWords, offset, pageSizeInt, sortType);
		PageBean pageBean = new PageBean();
		
		pageBean.setPageSize(pageSizeInt);
		pageBean.setCurrentPage(currentPageInt);
		pageBean.setAllRow(allRows);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.init();
		return pageBean;
	}
	
	public PageBean searchByLoginAndCheckDate(String login, String fromCheckDateKeyWords, String endCheckDateKeyWords, String currentPage, String pageSize)
	{
		int pageSizeInt = Integer.parseInt(pageSize);
		int currentPageInt = Integer.parseInt(currentPage);
		final int offset = PageBean.countOffset(pageSizeInt, currentPageInt);
		List<User> userList = userDAO.findByLogin(login);
		User user = null;
		if(userList != null && !userList.isEmpty())
		{
			user = userList.get(0);
		}
		int allRows = reportDAO.getRowCountByLoginAndCheckDate(user, fromCheckDateKeyWords, endCheckDateKeyWords);
		int totalPage = PageBean.countTotalPage(pageSizeInt, allRows);
		List<User> list= reportDAO.getReportByVendorAndCheckDate(user, fromCheckDateKeyWords, endCheckDateKeyWords, offset, pageSizeInt);
		PageBean pageBean = new PageBean();
		
		pageBean.setPageSize(pageSizeInt);
		pageBean.setCurrentPage(currentPageInt);
		pageBean.setAllRow(allRows);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.init();
		return pageBean;
	}
	
	public PageBean searchReportByMasterSampleNo(String masterSampleNoKeyWords, String currentPage, String pageSize)
	{
		int pageSizeInt = Integer.parseInt(pageSize);
		int currentPageInt = Integer.parseInt(currentPage);
		final int offset = PageBean.countOffset(pageSizeInt, currentPageInt);
		int allRows = reportDAO.getRowCountByMasterSampleNo(masterSampleNoKeyWords);
		int totalPage = PageBean.countTotalPage(pageSizeInt, allRows);
		List<User> list= reportDAO.getReportByMasterSampleNo(masterSampleNoKeyWords, offset, pageSizeInt);
		PageBean pageBean = new PageBean();
		
		pageBean.setPageSize(pageSizeInt);
		pageBean.setCurrentPage(currentPageInt);
		pageBean.setAllRow(allRows);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.init();
		return pageBean;
	}
	
	public PageBean searchByCheckDate4Price(String checkDateKeyWords, String currentPage, String pageSize)
	{
		int pageSizeInt = Integer.parseInt(pageSize);
		int currentPageInt = Integer.parseInt(currentPage);
		final int offset = PageBean.countOffset(pageSizeInt, currentPageInt);
		int allRows = reportDAO.getRowCountByCheckDate(checkDateKeyWords);
		int totalPage = PageBean.countTotalPage(pageSizeInt, allRows);
		List<User> list= reportDAO.getReportByCheckDate(checkDateKeyWords, offset, pageSizeInt);
		PageBean pageBean = new PageBean();
		
		pageBean.setPageSize(pageSizeInt);
		pageBean.setCurrentPage(currentPageInt);
		pageBean.setAllRow(allRows);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.init();
		return pageBean;
	}
	
	@Resource
	private ReportDAO reportDAO;
	
	@Resource
	private UserDAO userDAO;
	
	@Value("${report.location.path}")
	public String generatedLocation;
	
	@Value("${report.send.path}")
	public String sentRepoetLocation;
}
