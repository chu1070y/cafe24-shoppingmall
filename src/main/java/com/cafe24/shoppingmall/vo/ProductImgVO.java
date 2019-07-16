package com.cafe24.shoppingmall.vo;

public class ProductImgVO {

	private Integer product_img_no;
	private Integer product_no;
	private String filename;
	private String extension;
	private String imgType;
	
	@Override
	public String toString() {
		return "ProductImgVO [product_img_no=" + product_img_no + ", product_no=" + product_no + ", filename="
				+ filename + ", extension=" + extension + ", imgType=" + imgType + "]";
	}
	public Integer getProduct_img_no() {
		return product_img_no;
	}
	public void setProduct_img_no(Integer product_img_no) {
		this.product_img_no = product_img_no;
	}
	public Integer getProduct_no() {
		return product_no;
	}
	public void setProduct_no(Integer product_no) {
		this.product_no = product_no;
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
