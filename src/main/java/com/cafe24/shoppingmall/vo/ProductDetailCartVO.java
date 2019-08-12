package com.cafe24.shoppingmall.vo;

public class ProductDetailCartVO {
	private Integer product_detail_no;
	private Integer product_no;
	private String option_code;
	private Integer add_price;
	private Integer stock_num;		// 재고개수
	private Integer stock_avail;	// 판매가능수량
	private String stock_use;		// 재고사용
	private String detail_del;
	
	private ProductVO productVO;
	
	public ProductDetailCartVO() {
		this.add_price = 0;
		this.stock_use = "0";
	}

	@Override
	public String toString() {
		return "ProductDetailVO [product_detail_no=" + product_detail_no + ", product_no=" + product_no
				+ ", option_code=" + option_code + ", add_price=" + add_price + ", stock_num=" + stock_num
				+ ", stock_avail=" + stock_avail + ", stock_use=" + stock_use + ", detail_del=" + detail_del
				+ ", productVO=" + productVO + "]";
	}

	public Integer getProduct_detail_no() {
		return product_detail_no;
	}

	public void setProduct_detail_no(Integer product_detail_no) {
		this.product_detail_no = product_detail_no;
	}

	public Integer getProduct_no() {
		return product_no;
	}

	public void setProduct_no(Integer product_no) {
		this.product_no = product_no;
	}

	public String getOption_code() {
		return option_code;
	}

	public void setOption_code(String option_code) {
		this.option_code = option_code;
	}

	public Integer getAdd_price() {
		return add_price;
	}

	public void setAdd_price(Integer add_price) {
		this.add_price = add_price;
	}

	public Integer getStock_num() {
		return stock_num;
	}

	public void setStock_num(Integer stock_num) {
		this.stock_num = stock_num;
	}

	public Integer getStock_avail() {
		return stock_avail;
	}

	public void setStock_avail(Integer stock_avail) {
		this.stock_avail = stock_avail;
	}

	public String getStock_use() {
		return stock_use;
	}

	public void setStock_use(String stock_use) {
		this.stock_use = stock_use;
	}

	public String getDetail_del() {
		return detail_del;
	}

	public void setDetail_del(String detail_del) {
		this.detail_del = detail_del;
	}

	public ProductVO getProductVO() {
		return productVO;
	}

	public void setProductVO(ProductVO productVO) {
		this.productVO = productVO;
	}


}
