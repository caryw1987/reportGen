package com.report.gen.util;
/**
 * 得到工程的物理路径
 * @author jia
 *
 */
public class ProjectPath {
	public static String getProjectPath(){
		String pathString = Thread.currentThread().getContextClassLoader().getResource("").toString();
		pathString = pathString.replace("file:/", "");
		pathString = pathString.replace("WEB-INF/classes/", "");
		pathString = pathString.replace("%20", " ");
		return pathString;
	}
}
