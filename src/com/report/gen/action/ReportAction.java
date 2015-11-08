package com.report.gen.action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.report.gen.domain.Report;
import com.report.gen.service.ReportService;
import com.report.gen.util.CommonsMethod;
import com.report.gen.util.PageBean;


@Controller("reportAction")
@Scope("prototype")
public class ReportAction  extends ActionSupport
{
	public String addReport()
	{
		if(departmentArray!= null && departmentArray.length!=0)
		{
			String changedDepartment = departmentArray[0] ;
			for(int i=1; i< departmentArray.length; i++)
			{
				changedDepartment = changedDepartment +"-"+ departmentArray[i];
			}
			report.setDepartment(changedDepartment);
		}		
		reportService.addReport(report);
		return listReportsByPage4Recorder();
	}
	public String addMultiReport()
	{
		if(multiMasterSampleNo != null)
		{
			multiMasterSampleNo = multiMasterSampleNo.trim();
			String masterSampleNoArray[] = multiMasterSampleNo.split("\r\n");
			repeatedList = new ArrayList<String>();
			for(String masterSampleNo : masterSampleNoArray)
			{
				Report report = new Report();
				report.setMasterSampleNo(masterSampleNo.trim());
				reportService.addReport(report, repeatedList);
			}
		}
		return listReportsByPage4Recorder();
	}
	
	public String importReports() throws IOException
	{
		if(excel != null)
		{
			repeatedList = new ArrayList<String>();
			reportService.importReports(excel, excelFileName, repeatedList);
		}
		return listReportsByPage4EntryPerson();
	}
	
	public String listReportsByPage4Recorder()
	{	
		pageBean = reportService.findAllReports4Recorder(currentPage, pageSize);
		return "LIST_REPORTS_4RECORDER";
	}
	
	public String listReportsByPage4EntryPerson()
	{	
		pageBean = reportService.findAllReports4EntryPerson(currentPage, pageSize);
		return "LIST_REPORTS_4ENTRYPERSON";
	}
	
	public String listReportsByPage4ResultLogin()
	{	
		setListType(LIST_ALL_TYPE);
		pageBean = reportService.findAllReports4ResultLogin(currentPage, pageSize);
		return "LIST_REPORTS_4RESULTLOGIN";
	}
	
	public String listReportsByPage4GenWord()
	{	
		pageBean = reportService.findAllReports4GenWord(currentPage, pageSize);
		return "LIST_REPORTS_4GENWORD";
	}
	
	public String listReportsByPage4Check()
	{	
		pageBean = reportService.findAllReports4Check(currentPage, pageSize);
		return "LIST_REPORTS_4CHECK";
	}
	
	public String listReportsByPage4Send()
	{	
		setListType(LIST_ALL_TYPE);
		pageBean = reportService.findAllReports4Send(currentPage, pageSize);
		return "LIST_REPORTS_4SEND";
	}
	
	public String listReportsByPage4AddPrice()
	{	
		setListType(LIST_ALL_TYPE);
		pageBean = reportService.findAllReports4AddPrice(currentPage, pageSize);
		return "LIST_REPORTS_4ADD_PRICE";
	}
	
	public String listReportsByPage4Statistics()
	{	
		setListType(LIST_ALL_TYPE);
		pageBean = reportService.findAllReports4Statistics(currentPage, pageSize);
		return "LIST_REPORTS_4STATISTICS";
	}
	
	public String listReportsByPage4Progress()
	{	
		setListType(LIST_ALL_TYPE);
		pageBean = reportService.findAllReports(currentPage, pageSize);
		return "LIST_REPORTS_4PROGRESS";
	}
	
	public String redirect2AddReportJSP()
	{
		return "ADD_REPORT_JSP";
	}
	
	public String redirect2ImportReportJSP()
	{
		return "IMPORT_REPORT_JSP";
	}
	
	public String redirect2EditReportJSP()
	{
//		user = userService.findUserByPuid(user.getPuid());
		return "EDIT_REPORT_JSP";
	}
	
	public String redirect2EditReportJSP4Recorder()
	{
		report = reportService.findReportByPuid(report.getPuid());
		return "EDIT_REPORT_JSP_4RECORDER";
	}
	
	public String redirect2EditReportJSP4EntryPerson()
	{
		report = reportService.findReportByPuid(report.getPuid());
		return "EDIT_REPORT_JSP_4ENTRYPERSON";
	}
	
	public String redirect2EditReportJSP4GenWord()
	{
		report = reportService.findReportByPuid(report.getPuid());
		return "EDIT_REPORT_JSP_4GENWORD";
	}
	
	public String redirect2AddPriceJSP()
	{
		report = reportService.findReportByPuid(report.getPuid());
		return "ADD_PRICE_JSP";
	}
	
	public String redirect2AddMultiReportJSP()
	{
		return "ADD_MULTI_REPORT_JSP";
	}
	
	public String editReport4Recorder()
	{
		if(departmentArray!= null && departmentArray.length!=0)
		{
			String changedDepartment = departmentArray[0] ;
			for(int i=1; i< departmentArray.length; i++)
			{
				changedDepartment = changedDepartment +"-"+ departmentArray[i];
			}
			report.setDepartment(changedDepartment);
		}
		reportService.editReport4Recorder(report);
		return listReportsByPage4Recorder();
	}
	
	public String editReport4EntryPerson()
	{
		reportService.editReport4EntryPerson(report);
		return listReportsByPage4EntryPerson();
	}
	
	public String deleteReport4Recorder()
	{
		reportService.deleteReport(report);
		return listReportsByPage4Recorder();
	}
	
	public String deleteReport4Statistics()
	{
		reportService.deleteReport(report);
		return searchReport4Statistics();
	}
	
	public String redirect2GenReportJSP()
	{
		report = reportService.findReportByPuid(report.getPuid());
		return "GEN_REPORT_JSP";
	}
	
	public String resultLogin()
	{
		reportService.setResultLogin(reportId);
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", reportId);
		setJsonReportId(map);
		return "RESULTLOGIN";
	}
	
	public String resultLoginPending()
	{
		reportService.setResultLoginPending(reportId);
		String date= CommonsMethod.getToday();
		String formatDate = date.substring(0, 3)+"-"+date.substring(4, 5)+"-"+date.substring(6, 7);
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", reportId);
		map.put("time", formatDate);
		setJsonReportId(map);
		return "RESULTLOGINPENDING";
	}
	
	public String checkOK()
	{
		reportService.setCheckOK(reportId);
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", reportId);
		setJsonReportId(map);
		return "CHECK_OK";
	}
	
	public String setDone()
	{
		reportService.setDone(reportId);
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", reportId);
		setJsonReportId(map);
		return "SET_DONE";
	}
	
	public String setUnDone()
	{
		reportService.setUnDone(reportId);
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", reportId);
		setJsonReportId(map);
		return "SET_UN_DONE";
	}
	
	public String checkNOK()
	{
		reportService.setCheckNOK(reportId);
		String date= CommonsMethod.getToday();
		String formatDate = date.substring(0, 3)+"-"+date.substring(4, 5)+"-"+date.substring(6, 7);
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", reportId);
		map.put("time", formatDate);
		setJsonReportId(map);
		return "CHECK_NOK";
	}
	
	public String sendReport()
	{
		reportService.sendReport(reportId);
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", reportId);
		setJsonReportId(map);
		return "SEND_OK";
	}
	
	public String generateReport()
	{
		HashMap descrptionTblmap = new HashMap();
		descrptionTblmap.put("Sample Description:", wordAttrSampleDescription);
		descrptionTblmap.put("Country of Origin:" , wordAttrCountryOfOrigin);
		descrptionTblmap.put("Color:", wordAttrColor);
		descrptionTblmap.put("Factory/Manufacturer:", wordAttrFactory);
		descrptionTblmap.put("Retest:", wordAttrRetest);
		descrptionTblmap.put("Country of Destination: ", wordAttrCountryOfDestination);
		descrptionTblmap.put("Brand Name:", wordAttrBrandName);
		descrptionTblmap.put("Previous Report No.:", wordAttrPreviousReportNo);
		descrptionTblmap.put("Actual OO Date: ", wordAttrActualOoDate);
		descrptionTblmap.put("wordAttrTechnicalReport", wordAttrTechnicalReport);
		descrptionTblmap.put("dateModify", wordAttrDateModify);
		descrptionTblmap.put("dateOut", wordAttrDateOut);

		if(image == null)
		{
			File imageFile = new File(imagePath+reportService.findReportByPuid(report.getPuid()).getMasterSampleNo()+".jpg");
			if(imageFile.exists())
			{
				image = imageFile;
			}
		}
		
		try
		{
			reportService.generateReport(report, descrptionTblmap, wordAttrComponent, wordAttrLead, wordAttrCadmium, image, imageFileName);
		}
		catch(NumberFormatException e)
		{
			return "NUMBER_FORMAT_ERROR";
		}
		return listReportsByPage4GenWord();
	}
	
	public String generateWordOnly()
	{
		HashMap descrptionTblmap = new HashMap();
		descrptionTblmap.put("Vendor:",wordAttrVendor);
		descrptionTblmap.put("Sample Description:", wordAttrSampleDescription);
		descrptionTblmap.put("Country of Origin:" , wordAttrCountryOfOrigin);
		descrptionTblmap.put("Color:", wordAttrColor);
		descrptionTblmap.put("Factory/Manufacturer:", wordAttrFactory);
		descrptionTblmap.put("Retest:", wordAttrRetest);
		descrptionTblmap.put("Country of Destination: ", wordAttrCountryOfDestination);
		descrptionTblmap.put("Brand Name: ", wordAttrBrandName);
		descrptionTblmap.put("Previous Report No.:", wordAttrPreviousReportNo);
		descrptionTblmap.put("Style No.:", wordAttrStyleNo);
		descrptionTblmap.put("P.O. No.:", wordAttrPoNo);
		descrptionTblmap.put("Actual OO Date: ", wordAttrActualOoDate);
		descrptionTblmap.put("wordAttrTechnicalReport", wordAttrTechnicalReport);
		descrptionTblmap.put("dateIn", wordAttrDateIn);
		descrptionTblmap.put("dateModify", wordAttrDateModify);
		descrptionTblmap.put("dateOut", wordAttrDateOut);
		
		

		if(image == null)
		{
			File imageFile = new File(imagePath+wordAttrMasterSampleNo+".jpg");
			if(imageFile.exists())
			{
				image = imageFile;
			}
		}
		
		try
		{
			reportService.generateWordOnly(wordAttrMasterSampleNo, descrptionTblmap, wordAttrComponent, wordAttrLead, wordAttrCadmium, image, imageFileName);
		}
		catch(NumberFormatException e)
		{
			return "NUMBER_FORMAT_ERROR";
		}
		return "GENERATE_ONLY_SUCCESS";
	}
	
	public String searchReport()
	{
		pageBean = reportService.searchByVendorAndCheckDate(vendorKeyWords, checkDateKeyWords.replace("-", "").trim(), currentPage, pageSize, sortType);
		setListType(LIST_SEARCH_TYPE);
		return "SEARCH_RESULT";
	}
	
	public String searchReportByMasterSampleNo()
	{
		setListType(LIST_SEARCH_TYPE);
		if(masterSampleNoKeyWords== null|| masterSampleNoKeyWords.trim().equals(""))
		{
			return listReportsByPage4ResultLogin();
		}
		else
		{
			pageBean = reportService.searchReportByMasterSampleNo(masterSampleNoKeyWords.trim(), currentPage, pageSize);
			return "SEARCH_RESULT_4_RESULT_LOGIN";
		}
		
	}
	
	public String searchReport4Price()
	{
		setListType(LIST_SEARCH_TYPE);
		pageBean = reportService.searchByCheckDate4Price(checkDateKeyWords.replace("-", "").trim(), currentPage, pageSize);
		return "SEARCH_RESULT_4_PRICE";
	}
	
	public String searchReport4Statistics()
	{
		pageBean = reportService.searchByLoginAndCheckDate(loginKeyWords, fromCheckDateKeyWords.replace("-", "").trim(), endCheckDateKeyWords.replace("-", "").trim(), currentPage, pageSize);
		setListType(LIST_SEARCH_TYPE);
		return "SEARCH_RESULT_4_STATISTICS";
	}
	
	public String searchReport4Progress()
	{
		setListType(LIST_SEARCH_TYPE);
		if(masterSampleNoKeyWords== null|| masterSampleNoKeyWords.trim().equals(""))
		{
			return listReportsByPage4Progress();
		}
		else
		{
			pageBean = reportService.searchReportByMasterSampleNo(masterSampleNoKeyWords.trim(), currentPage, pageSize);
			return "SEARCH_RESULT_4PROGRESS";
		}
		
	}
	
	public String sendSelectReport()
	{
		if(sendReportArray!= null && sendReportArray.length!= 0)
		{
			for(int i=0; i< sendReportArray.length; i++)
			{
				reportService.sendReport(sendReportArray[i]);
			}
		}
		return listReportsByPage4Send();
	}
	
	public String changeDeparment4SelectedReports()
	{
		if(sendReportArray!= null && sendReportArray.length!= 0 && departmentArray!= null && departmentArray.length!= 0)
		{
			for(int i=0; i< sendReportArray.length; i++)
			{
				reportService.changeDeparment4SelectedReports(sendReportArray[i], departmentArray);
			}
		}
		return listReportsByPage4Recorder();
	}
	
	public String deleteSelectedReports()
	{
		if(sendReportArray!= null && sendReportArray.length!= 0)
		{
			for(int i=0; i< sendReportArray.length; i++)
			{
				Report delReport = reportService.findReportByPuid(sendReportArray[i]);
				reportService.deleteReport(delReport);
			}
		}
		return listReportsByPage4Recorder();
	}
	
	public String addPrice()
	{

		reportService.addPrice(report);
		return listReportsByPage4AddPrice();
	}
	
	public String setPrice()
	{
		reportService.setPrice(reportId,price);
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", reportId);
		map.put("prc", price+"");
		setJsonReportId(map);
		return "SEND_OK";
	}
	
	public String changeMoneyType4SelectedReports()
	{
		if(sendReportArray!= null && sendReportArray.length!= 0)
		{
			for(int i=0; i< sendReportArray.length; i++)
			{
				reportService.changeMoneyType4SelectedReports(sendReportArray[i], changedMoneyType, changedOtherMoneyType);
			}
		}
		return listReportsByPage4AddPrice();
	}
	
	public String findImage()
	{
		File imageFile = new File(imagePath+wordAttrMasterSampleNo+".jpg");
		Map<String, String> map = new HashMap<String, String>();
		if(imageFile.exists())
		{
			imageFile.renameTo(new File(imagePath+wordAttrMasterSampleNo+".jpg"));
			map.put("path", imageVirPath+wordAttrMasterSampleNo+".jpg");
		}
		else
		{
			map.put("path", "");
		}
		
		
		setImagePathMap(map);
		return "FIND_IMAGE_SUCCESS";
	}
	@Resource
	private ReportService reportService;
	
	@Value("${report.image.path}")
	private String imagePath;
	
	@Value("${report.image.virtual}")
	private String imageVirPath;
	
	private Report report;
	private PageBean pageBean;
	private String currentPage = "1";
	private String pageSize = "100";
	private String reportId;
	private Map jsonReportId;
	private Map imagePathMap;

	private String wordAttrTechnicalReport;
	private String wordAttrVendor;
	private String wordAttrFactory;
	private String wordAttrSampleDescription;
	private String wordAttrRetest;
	private String wordAttrCountryOfOrigin;
	private String wordAttrCountryOfDestination;
	private String wordAttrColor;
	private String wordAttrBrandName;
	private String wordAttrPreviousReportNo;
	private String wordAttrMasterSampleNo;
	private String wordAttrDateIn;
	private String wordAttrDateModify;
	private String wordAttrDateOut;
	private String wordAttrStyleNo;
	private String wordAttrPoNo;

	private String[] wordAttrComponent;
	private String[] wordAttrLead;
	private String[] wordAttrCadmium;

	private File image;
	private String imageFileName;
	private String imageContentType;
	private String vendorKeyWords;
	private String checkDateKeyWords;
	private String[] sendReportArray;
	private File excel;
	private String excelFileName;
	private int changedDepartment;
	private int changedMoneyType;
	private String changedOtherMoneyType;
	private String multiMasterSampleNo;
	private float price;
	private String[] priceArray;
	private String[] departmentArray;
	private String masterSampleNoKeyWords;
	private int listType;
	private final int LIST_ALL_TYPE = 0;
	private final int LIST_SEARCH_TYPE = 1;
	private String loginKeyWords;
	private String fromCheckDateKeyWords;
	private String endCheckDateKeyWords;
	private String wordAttrActualOoDate;
	private int sortType;
	private List repeatedList;
	
	
	public String[] getSendReportArray() {
		return sendReportArray;
	}

	public void setSendReportArray(String[] sendReportArray) {
		this.sendReportArray = sendReportArray;
	}

	public String getVendorKeyWords() {
		return vendorKeyWords;
	}

	public void setVendorKeyWords(String vendorKeyWords) {
		this.vendorKeyWords = vendorKeyWords;
	}

	public String getCheckDateKeyWords() {
		return checkDateKeyWords;
	}

	public void setCheckDateKeyWords(String checkDateKeyWords) {
		this.checkDateKeyWords = checkDateKeyWords;
	}

	public String getWordAttrTechnicalReport() {
		return wordAttrTechnicalReport;
	}

	public void setWordAttrTechnicalReport(String wordAttrTechnicalReport) {
		this.wordAttrTechnicalReport = wordAttrTechnicalReport;
	}

	public String getWordAttrVendor() {
		return wordAttrVendor;
	}

	public void setWordAttrVendor(String wordAttrVendor) {
		this.wordAttrVendor = wordAttrVendor;
	}
	
	public String getWordAttrSampleDescription() {
		return wordAttrSampleDescription;
	}

	public void setWordAttrSampleDescription(String wordAttrSampleDescription) {
		this.wordAttrSampleDescription = wordAttrSampleDescription;
	}
	
	public String getWordAttrFactory() {
		return wordAttrFactory;
	}

	public void setWordAttrFactory(String wordAttrFactory) {
		this.wordAttrFactory = wordAttrFactory;
	}

	public String getWordAttrRetest() {
		return wordAttrRetest;
	}

	public void setWordAttrRetest(String wordAttrRetest) {
		this.wordAttrRetest = wordAttrRetest;
	}

	public String getWordAttrCountryOfOrigin() {
		return wordAttrCountryOfOrigin;
	}

	public void setWordAttrCountryOfOrigin(String wordAttrCountryOfOrigin) {
		this.wordAttrCountryOfOrigin = wordAttrCountryOfOrigin;
	}

	public String getWordAttrCountryOfDestination() {
		return wordAttrCountryOfDestination;
	}

	public void setWordAttrCountryOfDestination(String wordAttrCountryOfDestination) {
		this.wordAttrCountryOfDestination = wordAttrCountryOfDestination;
	}

	public String getWordAttrColor() {
		return wordAttrColor;
	}

	public void setWordAttrColor(String wordAttrColor) {
		this.wordAttrColor = wordAttrColor;
	}

	public String getWordAttrBrandName() {
		return wordAttrBrandName;
	}

	public void setWordAttrBrandName(String wordAttrBrandName) {
		this.wordAttrBrandName = wordAttrBrandName;
	}

	public String getWordAttrPreviousReportNo() {
		return wordAttrPreviousReportNo;
	}

	public void setWordAttrPreviousReportNo(String wordAttrPreviousReportNo) {
		this.wordAttrPreviousReportNo = wordAttrPreviousReportNo;
	}

	public String getWordAttrMasterSampleNo() {
		return wordAttrMasterSampleNo;
	}

	public void setWordAttrMasterSampleNo(String wordAttrMasterSampleNo) {
		this.wordAttrMasterSampleNo = wordAttrMasterSampleNo;
	}

	public String getWordAttrDateIn() {
		return wordAttrDateIn;
	}

	public void setWordAttrDateIn(String wordAttrDateIn) {
		this.wordAttrDateIn = wordAttrDateIn;
	}

	public String getWordAttrStyleNo() {
		return wordAttrStyleNo;
	}

	public void setWordAttrStyleNo(String wordAttrStyleNo) {
		this.wordAttrStyleNo = wordAttrStyleNo;
	}

	public String getWordAttrPoNo() {
		return wordAttrPoNo;
	}

	public void setWordAttrPoNo(String wordAttrPoNo) {
		this.wordAttrPoNo = wordAttrPoNo;
	}

	public String[] getWordAttrComponent() {
		return wordAttrComponent;
	}

	public void setWordAttrComponent(String[] wordAttrComponent) {
		this.wordAttrComponent = wordAttrComponent;
	}

	public String[] getWordAttrLead() {
		return wordAttrLead;
	}

	public void setWordAttrLead(String[] wordAttrLead) {
		this.wordAttrLead = wordAttrLead;
	}

	public String[] getWordAttrCadmium() {
		return wordAttrCadmium;
	}

	public void setWordAttrCadmium(String[] wordAttrCadmium) {
		this.wordAttrCadmium = wordAttrCadmium;
	}

	public File getImage() {
		return image;
	}

	public void setImage(File image) {
		this.image = image;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public String getImageContentType() {
		return imageContentType;
	}

	public void setImageContentType(String imageContentType) {
		this.imageContentType = imageContentType;
	}
	
	public Map getJsonReportId() {
		return jsonReportId;
	}

	public void setJsonReportId(Map jsonReportId) {
		this.jsonReportId = jsonReportId;
	}

	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	public String getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public Report getReport() {
		return report;
	}

	public void setReport(Report report) {
		this.report = report;
	}

	public Map getImagePathMap() {
		return imagePathMap;
	}

	public void setImagePathMap(Map imagePathMap) {
		this.imagePathMap = imagePathMap;
	}

	public File getExcel() {
		return excel;
	}

	public void setExcel(File excel) {
		this.excel = excel;
	}

	public String getExcelFileName() {
		return excelFileName;
	}

	public void setExcelFileName(String excelFileName) {
		this.excelFileName = excelFileName;
	}

	public int getChangedDepartment() {
		return changedDepartment;
	}

	public void setChangedDepartment(int changedDepartment) {
		this.changedDepartment = changedDepartment;
	}

	public int getChangedMoneyType() {
		return changedMoneyType;
	}

	public void setChangedMoneyType(int changedMoneyType) {
		this.changedMoneyType = changedMoneyType;
	}

	public String getChangedOtherMoneyType() {
		return changedOtherMoneyType;
	}

	public void setChangedOtherMoneyType(String changedOtherMoneyType) {
		this.changedOtherMoneyType = changedOtherMoneyType;
	}
	
	public String getMultiMasterSampleNo() {
		return multiMasterSampleNo;
	}
	
	public void setMultiMasterSampleNo(String multiMasterSampleNo) {
		this.multiMasterSampleNo = multiMasterSampleNo;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String[] getPriceArray() {
		return priceArray;
	}
	public void setPriceArray(String[] priceArray) {
		this.priceArray = priceArray;
	}
	public String[] getDepartmentArray() {
		return departmentArray;
	}
	public void setDepartmentArray(String[] departmentArray) {
		this.departmentArray = departmentArray;
	}
	public String getMasterSampleNoKeyWords() {
		return masterSampleNoKeyWords;
	}
	public void setMasterSampleNoKeyWords(String masterSampleNoKeyWords) {
		this.masterSampleNoKeyWords = masterSampleNoKeyWords;
	}
	public int getListType() {
		return listType;
	}
	public void setListType(int listType) {
		this.listType = listType;
	}
	public String getLoginKeyWords() {
		return loginKeyWords;
	}
	public void setLoginKeyWords(String loginKeyWords) {
		this.loginKeyWords = loginKeyWords;
	}
	public String getFromCheckDateKeyWords() {
		return fromCheckDateKeyWords;
	}
	public void setFromCheckDateKeyWords(String fromCheckDateKeyWords) {
		this.fromCheckDateKeyWords = fromCheckDateKeyWords;
	}
	public String getEndCheckDateKeyWords() {
		return endCheckDateKeyWords;
	}
	public void setEndCheckDateKeyWords(String endCheckDateKeyWords) {
		this.endCheckDateKeyWords = endCheckDateKeyWords;
	}
	public String getWordAttrActualOoDate() {
		return wordAttrActualOoDate;
	}
	public void setWordAttrActualOoDate(String wordAttrActualOoDate) {
		this.wordAttrActualOoDate = wordAttrActualOoDate;
	}
	public String getWordAttrDateModify() {
		return wordAttrDateModify;
	}
	public void setWordAttrDateModify(String wordAttrDateModify) {
		this.wordAttrDateModify = wordAttrDateModify;
	}
	public String getWordAttrDateOut() {
		return wordAttrDateOut;
	}
	public void setWordAttrDateOut(String wordAttrDateOut) {
		this.wordAttrDateOut = wordAttrDateOut;
	}
	public int getSortType() {
		return sortType;
	}
	public void setSortType(int sortType) {
		this.sortType = sortType;
	}
	public List getRepeatedList() {
		return repeatedList;
	}
	public void setRepeatedList(List repeatedList) {
		this.repeatedList = repeatedList;
	}
	
}
