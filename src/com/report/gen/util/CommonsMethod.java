package com.report.gen.util;

import java.io.IOException;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author tiaobug 公共方法的调用
 * 
 */
public class CommonsMethod extends ActionSupport {

	private static final long serialVersionUID = -2407547567212022750L;
	private static SimpleDateFormat yMd = new SimpleDateFormat("yyyyMMdd");
	private static SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
	private static SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddHHmmssSSS");

	/**
	 * @return 返回现在时间，精确到秒，不带文字 例如：20120524104858
	 */
	public static String getNowCorrect2Second() {
		return sdf1.format(new Date());
	}

	public static Integer getNowYear() {
		String now = getNowCorrect2Second();
		int nowYear = Integer.valueOf(now.substring(0, 4));
		return nowYear;
	}
	
	public static String getCurrentTimeMinute() {
		String now = getNowCorrect2Second();
		String nowMinute = now.substring(0, 12);
		return nowMinute;
	}
	
	public static String getTimeMinute(Date date) {
		String now = sdf1.format(date);
		String nowMinute = now.substring(0, 12);
		return nowMinute;
	}
	
	public static String getTimeDate(Date date) {
		String now = sdf1.format(date);
		String nowDay = now.substring(0, 8);
		return nowDay;
	}

	/**
	 * @return 返回现在时间，精确到毫秒秒，不带文字 例如：20120524104858205
	 */
	public static String getNowCorrect2Millisecond() {
		return sdf2.format(new Date());
	}

	/**
	 * @return 返回现在时间，精确到秒，带文字 例如：2012年05月24日10时48分58秒
	 */
	public static String getNowCorrect2SecondWithWord() {
		String now = getNowCorrect2Second();
		return now.substring(0, 4) + "年" + now.substring(4, 6) + "月" + now.substring(6, 8) + "日" + now.substring(8, 10) + "时" + now.substring(10, 12) + "分" + now.substring(12, 14) + "秒";
	}
	
	/**
	 * @return 返回现在时间，精确到秒，带文字 例如：2012年05月24日10时48分58秒
	 */
	public static String getNowCorrect2SecondWithEnglishWord() {
		String now = getNowCorrect2Second();
		return now.substring(0, 4) + "-" + now.substring(4, 6) + "-" + now.substring(6, 8) + "  " + now.substring(8, 10) + ":" + now.substring(10, 12) + ":" + now.substring(12, 14) + " ";
	}

	/**
	 * @return 返回现在时间，精确到毫秒秒，带文字 例如：2012年05月24日10时48分58秒205毫秒
	 */
	public static String getNowCorrect2MillisecondWithWord() {
		String now = getNowCorrect2Millisecond();
		return now.substring(0, 4) + "年" + now.substring(4, 6) + "月" + now.substring(6, 8) + "日" + now.substring(8, 10) + "时" + now.substring(10, 12) + "分" + now.substring(12, 14) + "秒" + now.substring(14, 17) + "毫秒";
	}

	/**
	 * @return 返回现在日期，精确到天 例如：20120524
	 */
	public static String getToday() {
		return yMd.format(new Date());
	}

	/**
	 * @return 返回现在日期，精确到天,带文字 例如：2012年05月24日
	 */
	public static String getTodayWithWord() {
		String today = getToday();
		return today.substring(0, 4) + "年" + today.substring(4, 6) + "月" + today.substring(6, 8) + "日";
	}

	/**
	 * @return 返回现在日期，精确到天,带文字 例如：2012-05-24
	 */
	public static String getTodayDBWithWord() {
		String today = getToday();
		return today.substring(0, 4) + "-" + today.substring(4, 6) + "-" + today.substring(6, 8);
	}

	/**
	 * 向前台强制返回msg
	 */
	public static String returnSuccess() {
		HttpServletResponse response = ServletActionContext.getResponse();
		String msg = "{success:true}";
		try {
			response.getWriter().print(msg);
			response.setContentType("text/html");
		} catch (IOException e) {

		}
		return NONE;
	}

	/**
	 * 强制向前台返回str字符串
	 */
	public static String returnMsg(String str) {
		HttpServletResponse response = ServletActionContext.getResponse();
		String msg = "{success:true," + str + "}";
		try {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html");
			response.getWriter().print(msg);
		} catch (IOException e) {

		}
		return NONE;
	}

	/**
	 * @return 得到上传文件的路径（如:DOP_CMS/...）
	 */
	public static String getHttpPath(String rootPath, String fileName) throws Exception {
		int index = rootPath.lastIndexOf("\\");
		return rootPath.substring(index) + "/UploadFiles/" + fileName;
	}

	/**
	 * @return 将数据库中的路径拼成(http://ip:8080/DOP_CMS/....)传给前台显示
	 */
	public static String getRealPath(String path) throws Exception {
		return "http://" + getServerIp() + ":" + getPort() + path;
	}

	/**
	 * @return 将路径拆分（如:DOP_CMS/...）传到后台保存
	 */
	public static String getVirtualPath(String path) throws Exception {
		String str = "http://" + getServerIp() + ":" + getPort();
		String virtualPath = path.substring(str.length(), path.length());
		return virtualPath;
	}

	/**
	 * @return 得到服务端的IP地址
	 */
	public static String getServerIp() throws Exception {
		InetAddress addr = InetAddress.getLocalHost();
		String ip = addr.getHostAddress().toString();
		return ip;
	}

	/**
	 * @return 得到运行程序的端口号（如:8080）
	 */
	public static String getPort() throws Exception {
		int port = ServletActionContext.getRequest().getLocalPort();
		return String.valueOf(port);
	}

	/**
	 * @return 得到工程的物理路径(例如:D:/Workspaces/DOP_CMS/WebRoot/)
	 */
	public static String getProjectPath() {
		String pathString = Thread.currentThread().getContextClassLoader().getResource("").toString();
		pathString = pathString.replace("file:/", "");
		pathString = pathString.replace("WEB-INF/classes/", "");
		pathString = pathString.replace("%20", " ");
		return pathString;
	}

	/**
	 * @return 得到工程的物理路径(例如:D:/Workspaces/DOP_CMS/WebRoot/)
	 */
	public static String getWebRootPath() {
		String rootPath = ServletActionContext.getServletContext().getRealPath("");
		return rootPath;
	}

	/**
	 * @return 根据时间产生随即的一个文件名称
	 */
	public static String generateFileByDate() {
		String currentTime = sdf2.format(new Date());
		String randomNumber = String.valueOf((int) (Math.random() * 1000));
		String fileName = currentTime + randomNumber;
		return fileName;
	}

	/**
	 * @return 客户端机器的IP
	 */
	public static String getRemoteIP() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

}
