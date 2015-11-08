package com.report.gen.word.generator;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.TreeMap;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xwpf.converter.core.FileImageExtractor;
import org.apache.poi.xwpf.converter.core.FileURIResolver;
import org.apache.poi.xwpf.converter.core.IURIResolver;
import org.apache.poi.xwpf.converter.xhtml.DefaultContentHandlerFactory;
import org.apache.poi.xwpf.converter.xhtml.IContentHandlerFactory;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.Document;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.TextAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableCell.XWPFVertAlign;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.xml.sax.ContentHandler;

import com.report.gen.util.CommonsMethod;
import com.report.gen.word.generator.CustomXWPFDocument;


public class WordGeneratorMain {

	public HashMap map = new HashMap();
	public XWPFDocument document;
	public String userName;
	public String[] wordAttrComponent;
	public String[] wordAttrLead;
	public String[] wordAttrCadmium;
	
	public File image;
	public String imageFileName;
	public String masterSampleNo;
	public ArrayList<String> materialType = new ArrayList<String>();
	public ArrayList<String> result = new ArrayList<String>();
	public String generatedLocation;
	public boolean leadFailed = false;
	public boolean cadmiumFailed = false;
	
	public static HashMap<String, Float> limitLeadMap = new HashMap<String, Float> ()
	{
		
		{
			put("I", (float) 90);
			put("II", (float) 300);
			put("III", (float) 200);
		}
	};
	
	public static HashMap<String, Integer> intLimitLeadMap = new HashMap<String, Integer> ()
			{
				
				{
					put("I",  90);
					put("II", 300);
					put("III", 200);
				}
			};
	
	
	public WordGeneratorMain(HashMap descrptionTblmap, String[] wordAttrComponent, String[] wordAttrLead, String[] wordAttrCadmium, 
			File image, String imageFileName, String userName, String masterSampleNo, String generatedLocation)
	{
	    this.map = descrptionTblmap;
	    this.wordAttrComponent= wordAttrComponent;
	    this.wordAttrLead = wordAttrLead;
	    this.wordAttrCadmium = wordAttrCadmium;
	    this.image = image;
	    this.imageFileName = imageFileName;
	    this.userName= userName;
	    this.masterSampleNo = masterSampleNo;
	    this.generatedLocation= generatedLocation;
	    
	    calculateMaterialType();
	    calculateEachResult();
	}
	
	public void calculateMaterialType()
	{
		for(int i=0; i< wordAttrComponent.length; i++)
		{
			String component = wordAttrComponent[i];
			String lowComponent = "";
			lowComponent = component.toLowerCase();
			int coatingIndex = lowComponent.indexOf("coating");
			int metalIndex = lowComponent.indexOf("metal");
			int plasticIndex = lowComponent.indexOf("plastic");
			TreeMap map = new TreeMap();
			if(coatingIndex != -1)
			{
				map.put("I", coatingIndex);
			}
			if(metalIndex != -1)
			{
				map.put("II", metalIndex);
			}
			if(plasticIndex != -1)
			{
				map.put("III", plasticIndex);
			}
			
			if(map.size() != 0)
			{
				materialType.add((String)map.firstEntry().getKey());
			}
			else
			{
				materialType.add("III");
			}
			
		}
	}
	
	public void calculateEachResult()
	{
		String type;
		String testStr;
		for(int i=0; i< wordAttrLead.length; i++)
		{
			type = materialType.get(i);			
			testStr = wordAttrLead[i];
			float testInt;
			
			if(testStr!=null && !testStr.equals(""))
			{
				if(testStr.contains("<"))
				{
					testInt = Float.parseFloat(testStr.trim().substring(1));				
				}
				else
				{
					if(testStr.equals("-"))
					{
						testInt = 0;
					}
					else
					{
						testInt = Float.parseFloat(testStr.trim());
					}
				}
			}
			else
			{
				testInt = 0;
			}
			
			
			if(testInt<= limitLeadMap.get(type))
			{
				result.add("PASS");
			}
			else
			{
				result.add("FAILED");
				leadFailed = true;
			}
		}
		
		for(int i=0; i< wordAttrCadmium.length; i++)
		{
			type = materialType.get(i);			
			testStr = wordAttrCadmium[i];		
			float testInt2;
			
			if(testStr!=null && !testStr.equals(""))
			{
				if(testStr.contains("<"))
				{
					testInt2 = Float.parseFloat(testStr.trim().substring(1));				
				}
				else
				{
					if(testStr.equals("-"))
					{
						testInt2 = 0;
					}
					else
					{
						testInt2 = Float.parseFloat(testStr.trim());
					}
				}
			}
			else
			{
				testInt2 = 0;
			}
				
			if(testInt2 > 40.0)
			{
				result.set(i, "FAILED");
				cadmiumFailed = true;
			}
				
		}
	}
	
	public String process()
	{
	    try 
	    {
	    	String projectPath = CommonsMethod.getProjectPath();
	        document = new XWPFDocument(POIXMLDocument.openPackage(projectPath+"template.docx"));
	        XWPFTable overAllResultTable = document.getTables().get(0);
	        XWPFTable table = document.getTableArray(1);
	        XWPFTable tstPropTable =  document.getTableArray(2);
	        updateDescriptionTable(table);
            XWPFTable detailTable = document.getTableArray(4);
            updateDetailResultTable(detailTable);     
            if(!materialType.contains("I"))
            {
            	document.getParagraphs().get(6).getRuns().get(0).setText("Remark: No surface coating was found on submitted samples.");
            }
            updateOverAllResult(overAllResultTable, tstPropTable);
            if(image != null)
            {
            	addPicture();
            }
            int pages = document.getProperties().getExtendedProperties().getUnderlyingProperties().getPages();
            overAllResultTable.getRow(0).getCell(2).getParagraphs().get(0).getRuns().get(7).setText(""+pages,0);
            document.getFooterList().get(2).getParagraphArray(0).getRuns().get(2).setText(userName, 0);
            FileOutputStream outStream = null;
            String styleNoStr = (String) map.get("Style No.:");
            String wordFileName;
            String wordFilePath;
            File outFile;
            if(result.contains("FAILED"))
            {
            	wordFileName = styleNoStr.replace(",", ".")+"_"+masterSampleNo+"_lead and cadmium( ";
            	if(leadFailed)
            	{
            		wordFileName += "lead ";
            	}
            	if(cadmiumFailed)
            	{
            		wordFileName += "cadmium ";
            	}
            	wordFileName = wordFileName +")Failed.docx";
            	wordFilePath = generatedLocation+"FAIL REPORT"+File.separator+userName+File.separator+wordFileName;
            	outFile = new File(generatedLocation+File.separator+"FAIL REPORT"+File.separator+userName+File.separator);
            }
            else
            {
            	wordFileName = styleNoStr.replace(",", ".")+"_"+masterSampleNo+"_lead and cadmium.docx";
            	wordFilePath = generatedLocation+wordFileName;
            	outFile = new File(generatedLocation);
            	
            }
                   
            if(!outFile.exists())
            {
            	outFile.mkdirs();
            }
            outStream = new FileOutputStream(wordFilePath);
            document.write(outStream);
            outStream.close();
            return wordFileName;
	    }
	    catch (Exception e) 
	    {
	        e.printStackTrace();
	    }
	    return "";
	}
	
	public void updateOverAllResult(XWPFTable table, XWPFTable tstPropTable) throws ParseException
	{
		boolean overAllResult;
		DateFormat df1 = new SimpleDateFormat("MMM dd, yyyy",Locale.ENGLISH);
		
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		ParsePosition pos = new ParsePosition(0);
		String dateInStr= (String) map.get("dateIn");
		Date strtodate;
		String dateIn;
		String dateModify;
		String dateOut;
		if(dateInStr!=null&&!dateInStr.equals(""))
		{
			strtodate = formatter.parse(dateInStr);
			dateIn = df1.format(strtodate);
			
			XWPFTableRow row3 = table.getRow(1);
			XWPFParagraph p3 = row3.getCell(1).getParagraphs().get(0);
			p3.setAlignment(ParagraphAlignment.LEFT);
			XWPFRun r3 = p3.createRun();
			r3.setFontFamily("Times New Roman");
			r3.setFontSize(10);
			r3.setText(dateIn,0);
			row3.getCell(1).setParagraph(p3);
		}
		else
		{
			dateIn = "/";
			
			XWPFTableRow row3 = table.getRow(1);
			XWPFParagraph p3 = row3.getCell(1).getParagraphs().get(0);
			p3.setAlignment(ParagraphAlignment.LEFT);
			XWPFRun r3 = p3.createRun();
			r3.setFontFamily("Times New Roman");
			r3.setFontSize(10);
			r3.setText(dateIn,0);
			row3.getCell(1).setParagraph(p3);	
		}
		
		String dateModifyStr= (String) map.get("dateModify");
		if(dateModifyStr!=null&&!dateModifyStr.equals(""))
		{
			strtodate = formatter.parse(dateModifyStr, new ParsePosition(0));
			dateModify = df1.format(strtodate);
			
			XWPFTableRow rowM = table.getRow(2);
			XWPFParagraph pM = rowM.getCell(1).getParagraphs().get(0);
			pM.setAlignment(ParagraphAlignment.LEFT);
			XWPFRun rM = pM.createRun();
			rM.setFontFamily("Times New Roman");
			rM.setFontSize(10);
			rM.setText(dateModify,0);
			rowM.getCell(1).setParagraph(pM);
		}
		else
		{
			dateModify = "/";
			
			XWPFTableRow rowM = table.getRow(2);
			XWPFParagraph pM = rowM.getCell(1).getParagraphs().get(0);
			pM.setAlignment(ParagraphAlignment.LEFT);
			XWPFRun rM = pM.createRun();
			rM.setFontFamily("Times New Roman");
			rM.setFontSize(10);
			rM.setText(dateModify,0);
			rowM.getCell(1).setParagraph(pM);	
		}
		
		String dateOutStr= (String) map.get("dateOut");
		if(dateOutStr!=null&&!dateOutStr.equals(""))
		{
			strtodate = formatter.parse(dateOutStr);
			dateOut = df1.format(strtodate);
			
			XWPFTableRow row3 = table.getRow(3);
			XWPFParagraph p3 = row3.getCell(1).getParagraphs().get(0);
			p3.setAlignment(ParagraphAlignment.LEFT);
			XWPFRun r3 = p3.createRun();
			r3.setFontFamily("Times New Roman");
			r3.setFontSize(10);
			r3.setText(dateOut,0);
			row3.getCell(1).setParagraph(p3);
		}
		else
		{
			dateOut = df1.format(new Date());;
			
			XWPFTableRow row3 = table.getRow(3);
			XWPFParagraph p3 = row3.getCell(1).getParagraphs().get(0);
			p3.setAlignment(ParagraphAlignment.LEFT);
			XWPFRun r3 = p3.createRun();
			r3.setFontFamily("Times New Roman");
			r3.setFontSize(10);
			r3.setText(dateOut,0);
			row3.getCell(1).setParagraph(p3);	
		}
		
		
		if(result.contains("FAILED"))
		{
			overAllResult = false;
		}
		else
		{
			overAllResult = true;
		}
		
		XWPFTableRow row = tstPropTable.getRow(1);
		if(overAllResult)
		{
			XWPFParagraph p00 = row.getCell(1).getParagraphs().get(0);
			p00.setAlignment(ParagraphAlignment.CENTER);
			XWPFRun r00 = p00.createRun();
			r00.setFontFamily("Times New Roman");
			r00.setFontSize(9);
			r00.setText("X",0);
			r00.setBold(true);
			row.getCell(1).setParagraph(p00);
			row.getCell(1).setVerticalAlignment(XWPFVertAlign.CENTER);
			table.getRow(3).getCell(4).getParagraphs().get(0).getRuns().get(0).setText("PASS",0);
		}
		else
		{
			XWPFParagraph p01 = row.getCell(2).getParagraphs().get(0);
			p01.setAlignment(ParagraphAlignment.CENTER);
			XWPFRun r01 = p01.createRun();
			r01.setFontFamily("Times New Roman");
			r01.setFontSize(9);
			r01.setText("X",0);
			r01.setBold(true);
			row.getCell(2).setParagraph(p01);
			row.getCell(2).setVerticalAlignment(XWPFVertAlign.CENTER);
			table.getRow(3).getCell(4).getParagraphs().get(0).getRuns().get(0).setText("FAILD");
		}
		XWPFTableRow row1 = table.getRow(0);
		XWPFParagraph p1 = row1.getCell(1).getParagraphs().get(0);
		p1.setAlignment(ParagraphAlignment.LEFT);
		XWPFRun r1 = p1.createRun();
		r1.setFontFamily("Arial");
		r1.setFontSize(11);
//		String technicalReportTxt = "("+masterSampleNo+")" + (String)map.get("wordAttrTechnicalReport");
		String technicalReportTxt = "("+masterSampleNo.substring(0, 4)+")" + masterSampleNo.substring(4, 7)+"-"+masterSampleNo.substring(7, 11);
		String Retest = (String) map.get("Retest:");
		if(Retest != null && Retest.equals("Yes"))
		{
			technicalReportTxt +="R";
		}
		r1.setText(technicalReportTxt,0);
		row1.getCell(1).setParagraph(p1);
		
//		XWPFTableRow row2 = table.getRow(3);
//		XWPFParagraph p2 = row2.getCell(1).getParagraphs().get(0);
//		p2.setAlignment(ParagraphAlignment.LEFT);
//		XWPFRun r2 = p2.createRun();
//		r2.setFontFamily("Times New Roman");
//		r2.setFontSize(10);
//		r2.setText(dateOut);
//		row2.getCell(1).setParagraph(p2);
//		
		
	}
	
	public void updateDescriptionTable(XWPFTable table)
	{
		String attr = "";
		for(int i=0;  i< table.getRows().size() ; i++ )
		{
//			if(i == table.getRows().size()-1)
//		    {
//		        XWPFTableCell cell = table.getRow(i).getCell(1);
//                if(cell!= null)
//                {
//                    List<XWPFParagraph> paragrphs = cell.getParagraphs();
//                    XWPFParagraph paragrph = paragrphs.get(0);
//                    XWPFRun run = paragrph.getRuns().get(0);
//                    String previousReportNo = (String) map.get("Previous Report No.:");
//                    if(previousReportNo!= null && !previousReportNo.equals(""))
//                    {
//                    	run.setText(previousReportNo, 0);
//                    }
//                    else
//                    {
//                    	run.setText("/", 0);
//                    }
//                }
//                continue;
//		    }
			for(int j=0; j<4; j++ )
			{
				if(j%2 ==0)
				{
					attr = table.getRow(i).getCell(j).getText();
					continue;
				}
				XWPFTableCell cell = table.getRow(i).getCell(j);
				if(cell!= null)
				{
					List<XWPFParagraph> paragrphs = cell.getParagraphs();
					XWPFParagraph paragrph = paragrphs.get(0);
					XWPFRun run = paragrph.getRuns().get(0);
					String attrStr = (String) map.get(attr);
					if(attrStr != null && !attrStr.equals(""))
					{
						run.setText(attrStr, 0);
					}
					else
					{
						run.setText("/", 0);
					}
					
				}
				
			}
			
		}
		
	}
	
	public void updateDetailResultTable(XWPFTable table)
	{
		
		for(int i=0; i<wordAttrComponent.length; i++)
		{
			XWPFTableRow row;
			if(wordAttrComponent[i]!=null && !wordAttrComponent[i].equals(""))
			{
				if(i==0)
				{
					row = table.getRow(2);
				}
				else
				{
					row = table.createRow();
				}
				XWPFParagraph p0 = row.getCell(0).getParagraphs().get(0);
				p0.setAlignment(ParagraphAlignment.CENTER);
				XWPFRun r0 = p0.createRun();
				r0.setFontFamily("Times");
				r0.setFontSize(9);
				int index = i+1;
				r0.setText(""+index,0);
				if(result.get(i).equals("FAILED"))
				{
					r0.setBold(true);
				}
				row.getCell(0).setParagraph(p0);
				row.getCell(0).setVerticalAlignment(XWPFVertAlign.CENTER);
				
				XWPFParagraph p1 = row.getCell(1).getParagraphs().get(0);
				p1.setAlignment(ParagraphAlignment.LEFT);
				XWPFRun r1 = p1.createRun();
				r1.setFontFamily("Times");
				r1.setFontSize(10);
				r1.setText(wordAttrComponent[i],0);
				if(result.get(i).equals("FAILED"))
				{
					r1.setBold(true);
				}
				row.getCell(1).setParagraph(p1);
				row.getCell(1).setVerticalAlignment(XWPFVertAlign.CENTER);
				
				XWPFParagraph p2 = row.getCell(2).getParagraphs().get(0);
				p2.setAlignment(ParagraphAlignment.CENTER);
				XWPFRun r2 = p2.createRun();
				r2.setFontFamily("Times");
				r2.setFontSize(10);
				r2.setText(wordAttrLead[i],0);
				if(result.get(i).equals("FAILED"))
				{
					r2.setBold(true);
				}
				row.getCell(2).setParagraph(p2);
				row.getCell(2).setVerticalAlignment(XWPFVertAlign.CENTER);
				
				XWPFParagraph p3 = row.getCell(3).getParagraphs().get(0);
				p3.setAlignment(ParagraphAlignment.CENTER);
				XWPFRun r3 = p3.createRun();
				r3.setFontFamily("Times");
				r3.setFontSize(10);
				r3.setText(wordAttrCadmium[i],0);
				if(result.get(i).equals("FAILED"))
				{
					r3.setBold(true);
				}
				row.getCell(3).setParagraph(p3);
				row.getCell(3).setVerticalAlignment(XWPFVertAlign.CENTER);
				
				
				XWPFParagraph p4 = row.getCell(4).getParagraphs().get(0);
				p4.setAlignment(ParagraphAlignment.CENTER);
				XWPFRun r4 = p4.createRun();
				r4.setFontFamily("Times");
				r4.setFontSize(10);
				if(wordAttrLead[i].equals("-"))
				{
					r4.setText("-",0);
				}
				else
				{
					r4.setText(""+intLimitLeadMap.get(materialType.get(i)),0);
				}
				if(result.get(i).equals("FAILED"))
				{
					r4.setBold(true);
				}
				row.getCell(4).setParagraph(p4);
				row.getCell(4).setVerticalAlignment(XWPFVertAlign.CENTER);
				
				XWPFParagraph p5 = row.getCell(5).getParagraphs().get(0);
				p5.setAlignment(ParagraphAlignment.CENTER);
				XWPFRun r5 = p5.createRun();
				r5.setFontFamily("Times");
				r5.setFontSize(10);
				if(wordAttrCadmium[i].equals("-"))
				{
					r5.setText("-",0);
				}
				else
				{
					r5.setText("40.0",0);
				}
				
				if(result.get(i).equals("FAILED"))
				{
					r5.setBold(true);
				}
				row.getCell(5).setParagraph(p5);
				row.getCell(5).setVerticalAlignment(XWPFVertAlign.CENTER);
				
				if(i!=0)
				{
					row.createCell();
				}
				XWPFParagraph p6 = row.getCell(6).getParagraphs().get(0);
				p6.setAlignment(ParagraphAlignment.CENTER);
				XWPFRun r6 = p6.createRun();
				r6.setFontFamily("Times");
				r6.setFontSize(10);
				r6.setText(materialType.get(i),0);	
				if(result.get(i).equals("FAILED"))
				{
					r6.setBold(true);
				}
				row.getCell(6).setParagraph(p6);
				row.getCell(6).setVerticalAlignment(XWPFVertAlign.CENTER);
				
				if(i!=0)
				{
					row.createCell();
				}
				XWPFParagraph p7 = row.getCell(7).getParagraphs().get(0);
				p7.setAlignment(ParagraphAlignment.CENTER);
				XWPFRun r7 = p7.createRun();
				r7.setFontFamily("Times");
				r7.setFontSize(10);
				if(wordAttrLead[i].equals("-")&&wordAttrCadmium[i].equals("-"))
				{
					r7.setText("exempt",0);
				}
				else
				{
					r7.setText(result.get(i),0);
				}
				if(result.get(i).equals("FAILED"))
				{
					r7.setBold(true);
				}
				row.getCell(7).setParagraph(p7);
				row.getCell(7).setVerticalAlignment(XWPFVertAlign.CENTER);
			}
			
		}
		
	}
	
	public void addPicture() throws InvalidFormatException, IOException
	{
		CustomXWPFDocument customDoc = new CustomXWPFDocument();
		XWPFParagraph picPrgrph = document.getParagraphs().get(11);
		FileInputStream picStream = new FileInputStream(image);
		String ind = document.addPictureData(picStream,CustomXWPFDocument.PICTURE_TYPE_JPEG);
		customDoc.createPicture(ind, document.getNextPicNameNumber(Document.PICTURE_TYPE_JPEG), 500, 400, picPrgrph);
		picPrgrph.setAlignment(ParagraphAlignment.CENTER);
		XWPFRun run = picPrgrph.createRun();
		run.setText("");
//		picStream.close();
	}

}
