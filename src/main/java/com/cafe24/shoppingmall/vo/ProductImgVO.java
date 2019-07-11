package com.cafe24.shoppingmall.vo;

public class ProductImgVO {

	private Integer imgNo;
	private Integer productNo;
	private String filename;
	private String extension;
	private String imgType;
	public Integer getImgNo() {
		return imgNo;
	}
	public void setImgNo(Integer imgNo) {
		this.imgNo = imgNo;
	}
	public Integer getProductNo() {
		return productNo;
	}
	public void setProductNo(Integer productNo) {
		this.productNo = productNo;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	public String getImgType() {
		return imgType;
	}
	public void setImgType(String imgType) {
		this.imgType = imgType;
	}
	@Override
	public String toString() {
		return "ProductImgVO [imgNo=" + imgNo + ", productNo=" + productNo + ", filename=" + filename + ", extension="
				+ extension + ", imgType=" + imgType + "]";
	}
	
	
}
