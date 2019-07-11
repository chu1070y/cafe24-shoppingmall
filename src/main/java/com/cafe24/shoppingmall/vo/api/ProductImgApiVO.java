package com.cafe24.shoppingmall.vo.api;

public class ProductImgApiVO {

	private String filename;
	private String extension;
	private String imgType;
	
	@Override
	public String toString() {
		return "ProductImgApiVO [filename=" + filename + ", extension=" + extension + ", imgType=" + imgType + "]";
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
	

}
