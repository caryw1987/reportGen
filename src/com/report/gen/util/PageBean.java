package com.report.gen.util;

import java.math.BigDecimal;
import java.util.List;

//import com.sun.xml.rpc.processor.modeler.j2ee.xml.string;

public class PageBean {

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public int getAllRow() {
		return allRow;
	}

	public void setAllRow(int allRow) {
		this.allRow = allRow;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public boolean getIsFirstPage() {
		return this.isFirstPage;
	}
	
	public boolean getIsLastPage() {
		return this.isLastPage;
	}
	
	
	private List list;
	private int allRow; // total records
	private int totalPage;// Total page
	private int currentPage;//
	private int pageSize=10;// 每页 records 总数没有

	private boolean isFirstPage;// 是否为第一页
	private boolean isLastPage;// 是否为最后一页
	private boolean hasPreviousPage;// 是否有前一页
	private boolean hasNextPage;// 是否有下一页

	/** */
	/** 　　 * 初始化分页信息 　　 */
	public void init() {
		this.isFirstPage = isFirstPage();
		this.isLastPage = isLastPage();
		this.hasPreviousPage = isHasPreviousPage();
		this.hasNextPage = isHasNextPage();
	}

	/** */
	/**
	 * 　　 * 以下判断页的信息,只需getter方法(is方法)即可 　　 * @return 　　
	 */
	public boolean isFirstPage() {
		return currentPage == 1;// 如是当前页是第1页
	}

	public boolean isLastPage() {
		return currentPage == totalPage;// 如果当前页是最后一页
	}

	public boolean isHasPreviousPage() {
		return currentPage != 1;
    }

	public boolean isHasNextPage() {
		return currentPage != totalPage;// 只要当前页不是最后1页
	}

	/** */
	/**
	 * 　　 * 计算总页数,静态方法,供外部直接通过类名调用 　　 * @param pageSize 每页记录数 　　 * @param allRow
	 * 总记录数 　　 * @return 总页数 　　
	 */
	public static int countTotalPage(final int pageSize, final int allRow) {
		int totalPage = allRow % pageSize == 0 ? allRow / pageSize : allRow
				/ pageSize + 1;
		if(totalPage ==0)
		{
			totalPage=1;
		}
		return totalPage;
	}
	/**
	 * 总页数
	 */
	public int totalPages(){
		int totalPage = allRow % pageSize == 0 ? allRow / pageSize : allRow
				/ pageSize + 1;
		if(totalPage ==0)
		{
			totalPage=1;
		}
		return totalPage;
	}

	/** */
	/**
	 * 　　 * 计算当前页开始记录 　　 * @param pageSize 每页记录数 　　 * @param currentPage 当前第几页
	 * 　　 * @return 当前页开始记录号 　　
	 */
	public static int countOffset(final int pageSize, final int currentPage) {
		final int offset = pageSize * (currentPage - 1);
		return offset;
	}

	/** */
	/**
	 * 　　 * 计算当前页,若为0或者请求的URL中没有"?page=",则用1代替 　　 * @param page
	 * 传入的参数(可能为空,即0,则返回1) 　　 * @return 当前页 　　
	 */
	public static int countCurrentPage(int page) {
		final int curPage = (page == 0 ? 1 : page);
		return curPage;
	}
	/**
	 *商业评价
	 */
	public static String bizEstimate(String num1,String num2,String num3,String pf1,String pf2,String pf3){
		String num="0";
		//第一种策略
		BigDecimal num_n=new BigDecimal(num1);
		num_n= num_n.multiply(new BigDecimal(pf1));
		//第二种策略
		BigDecimal num_t=new BigDecimal(num2);
		num_t= num_t.multiply(new BigDecimal(pf2));
		//第三种策略
		BigDecimal num_s=new BigDecimal(num3);
		num_s= num_s.multiply(new BigDecimal(pf3));
		num_n=num_n.add(num_t);
		num_n=num_n.add(num_s);
		return num_n.toString(); 
	}
	/**
	 * 判断
	 */
	public boolean isValidate(String num1,String num2){
		Double dou=new Double(num1);
		Double dou2=new Double(num2);
		if(dou>dou2){
			return true;
		}
		return false;
	}
}
