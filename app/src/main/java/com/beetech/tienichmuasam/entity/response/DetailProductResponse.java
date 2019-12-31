package com.beetech.tienichmuasam.entity.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DetailProductResponse{

	@SerializedName("calculationType")
	private int calculationType;

	@SerializedName("sizeId")
	private String sizeId;

	@SerializedName("colorName")
	private String colorName;

	@SerializedName("priceToStr")
	private String priceToStr;

	@SerializedName("attributeTypes")
	private String attributeTypes;

	@SerializedName("colorId")
	private String colorId;

	@SerializedName("imagePath")
	private String imagePath;

	@SerializedName("productNameSearch")
	private String productNameSearch;

	@SerializedName("priceDiscountStr")
	private String priceDiscountStr;

	@SerializedName("discount")
	private int discount;

	@SerializedName("customRate")
	private double customRate;

	@SerializedName("description")
	private String description;

	@SerializedName("deleteFlag")
	private String deleteFlag;

	@SerializedName("discountName")
	private String discountName;

	@SerializedName("totalBuyCount")
	private int totalBuyCount;

	@SerializedName("sizeName")
	private String sizeName;

	@SerializedName("countColor")
	private String countColor;

	@SerializedName("attributeIds")
	private String attributeIds;

	@SerializedName("sizeDescription")
	private String sizeDescription;

	@SerializedName("productColorName")
	private String productColorName;

	@SerializedName("price")
	private int price;

	@SerializedName("priceFromStr")
	private String priceFromStr;

	@SerializedName("rowspan")
	private String rowspan;

	@SerializedName("stockNum")
	private int stockNum;

	@SerializedName("views")
	private int views;

	@SerializedName("allColor")
	private String allColor;

	@SerializedName("allSize")
	private String allSize;

	@SerializedName("productId")
	private String productId;

	@SerializedName("like")
	private int like;

	@SerializedName("productColors")
	private String productColors;

	@SerializedName("isLiked")
	private boolean isLiked;

	@SerializedName("index")
	private String index;

	@SerializedName("dateFrom")
	private String dateFrom;

	@SerializedName("url")
	private String url;

	@SerializedName("priceDiscount")
	private int priceDiscount;

	@SerializedName("homeInfoId")
	private String homeInfoId;

	@SerializedName("totalSellCount")
	private int totalSellCount;

	@SerializedName("priceStr")
	private String priceStr;

	@SerializedName("categoryIds")
	private String categoryIds;

	@SerializedName("material")
	private String material;

	@SerializedName("questionAnswers")
	private String questionAnswers;

	@SerializedName("name")
	private String name;

	@SerializedName("dateTo")
	private String dateTo;

	@SerializedName("style")
	private String style;

	@SerializedName("userGuideImg")
	private String userGuideImg;

	@SerializedName("categoryId")
	private String categoryId;

	@SerializedName("images")
	private List<ImageProductResponse> images;

	@SerializedName("products")
	private List<ListProductResponse> similarProducts;

	private List<ColorResponse> colors;
	private List<SizeResponse> sizes;

	public List<ColorResponse> getColors() {
		return colors;
	}

	public void setColors(List<ColorResponse> colors) {
		this.colors = colors;
	}

	public List<SizeResponse> getSizes() {
		return sizes;
	}

	public void setSizes(List<SizeResponse> sizes) {
		this.sizes = sizes;
	}

	public List<ImageProductResponse> getImages() {
		return images;
	}

	public void setImages(List<ImageProductResponse> images) {
		this.images = images;
	}

	public List<ListProductResponse> getSimilarProducts() {
		return similarProducts;
	}

	public void setSimilarProducts(List<ListProductResponse> similarProducts) {
		this.similarProducts = similarProducts;
	}

	public void setCalculationType(int calculationType){
		this.calculationType = calculationType;
	}

	public int getCalculationType(){
		return calculationType;
	}

	public void setSizeId(String sizeId){
		this.sizeId = sizeId;
	}

	public String getSizeId(){
		return sizeId;
	}

	public void setColorName(String colorName){
		this.colorName = colorName;
	}

	public String getColorName(){
		return colorName;
	}

	public void setPriceToStr(String priceToStr){
		this.priceToStr = priceToStr;
	}

	public String getPriceToStr(){
		return priceToStr;
	}

	public void setAttributeTypes(String attributeTypes){
		this.attributeTypes = attributeTypes;
	}

	public String getAttributeTypes(){
		return attributeTypes;
	}

	public void setColorId(String colorId){
		this.colorId = colorId;
	}

	public String getColorId(){
		return colorId;
	}

	public void setImagePath(String imagePath){
		this.imagePath = imagePath;
	}

	public String getImagePath(){
		return imagePath;
	}

	public void setProductNameSearch(String productNameSearch){
		this.productNameSearch = productNameSearch;
	}

	public String getProductNameSearch(){
		return productNameSearch;
	}

	public void setPriceDiscountStr(String priceDiscountStr){
		this.priceDiscountStr = priceDiscountStr;
	}

	public String getPriceDiscountStr(){
		return priceDiscountStr;
	}

	public void setDiscount(int discount){
		this.discount = discount;
	}

	public int getDiscount(){
		return discount;
	}

	public void setCustomRate(double customRate){
		this.customRate = customRate;
	}

	public double getCustomRate(){
		return customRate;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setDeleteFlag(String deleteFlag){
		this.deleteFlag = deleteFlag;
	}

	public String getDeleteFlag(){
		return deleteFlag;
	}

	public void setDiscountName(String discountName){
		this.discountName = discountName;
	}

	public String getDiscountName(){
		return discountName;
	}

	public void setTotalBuyCount(int totalBuyCount){
		this.totalBuyCount = totalBuyCount;
	}

	public int getTotalBuyCount(){
		return totalBuyCount;
	}

	public void setSizeName(String sizeName){
		this.sizeName = sizeName;
	}

	public String getSizeName(){
		return sizeName;
	}

	public void setCountColor(String countColor){
		this.countColor = countColor;
	}

	public String getCountColor(){
		return countColor;
	}

	public void setAttributeIds(String attributeIds){
		this.attributeIds = attributeIds;
	}

	public String getAttributeIds(){
		return attributeIds;
	}

	public void setSizeDescription(String sizeDescription){
		this.sizeDescription = sizeDescription;
	}

	public String getSizeDescription(){
		return sizeDescription;
	}

	public void setProductColorName(String productColorName){
		this.productColorName = productColorName;
	}

	public String getProductColorName(){
		return productColorName;
	}

	public void setPrice(int price){
		this.price = price;
	}

	public int getPrice(){
		return price;
	}


	public void setRowspan(String rowspan){
		this.rowspan = rowspan;
	}

	public String getRowspan(){
		return rowspan;
	}

	public void setStockNum(int stockNum){
		this.stockNum = stockNum;
	}

	public int getStockNum(){
		return stockNum;
	}

	public void setViews(int views){
		this.views = views;
	}

	public int getViews(){
		return views;
	}

	public void setAllColor(String allColor){
		this.allColor = allColor;
	}

	public String getAllColor(){
		return allColor;
	}

	public void setAllSize(String allSize){
		this.allSize = allSize;
	}

	public String getAllSize(){
		return allSize;
	}

	public void setProductId(String productId){
		this.productId = productId;
	}

	public String getProductId(){
		return productId;
	}

	public void setLike(int like){
		this.like = like;
	}

	public int getLike(){
		return like;
	}

	public void setProductColors(String productColors){
		this.productColors = productColors;
	}

	public String getProductColors(){
		return productColors;
	}

	public void setIsLiked(boolean isLiked){
		this.isLiked = isLiked;
	}

	public boolean isIsLiked(){
		return isLiked;
	}

	public void setIndex(String index){
		this.index = index;
	}

	public String getIndex(){
		return index;
	}

	public void setDateFrom(String dateFrom){
		this.dateFrom = dateFrom;
	}

	public String getDateFrom(){
		return dateFrom;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	public void setPriceDiscount(int priceDiscount){
		this.priceDiscount = priceDiscount;
	}

	public int getPriceDiscount(){
		return priceDiscount;
	}

	public void setHomeInfoId(String homeInfoId){
		this.homeInfoId = homeInfoId;
	}

	public String getHomeInfoId(){
		return homeInfoId;
	}

	public void setTotalSellCount(int totalSellCount){
		this.totalSellCount = totalSellCount;
	}

	public int getTotalSellCount(){
		return totalSellCount;
	}

	public void setPriceStr(String priceStr){
		this.priceStr = priceStr;
	}

	public String getPriceStr(){
		return priceStr;
	}

	public void setCategoryIds(String categoryIds){
		this.categoryIds = categoryIds;
	}

	public String getCategoryIds(){
		return categoryIds;
	}

	public void setMaterial(String material){
		this.material = material;
	}

	public String getMaterial(){
		return material;
	}

	public void setQuestionAnswers(String questionAnswers){
		this.questionAnswers = questionAnswers;
	}

	public String getQuestionAnswers(){
		return questionAnswers;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setDateTo(String dateTo){
		this.dateTo = dateTo;
	}

	public String getDateTo(){
		return dateTo;
	}

	public void setStyle(String style){
		this.style = style;
	}

	public String getStyle(){
		return style;
	}

	public void setUserGuideImg(String userGuideImg){
		this.userGuideImg = userGuideImg;
	}

	public String getUserGuideImg(){
		return userGuideImg;
	}

	public void setCategoryId(String categoryId){
		this.categoryId = categoryId;
	}

	public String getCategoryId(){
		return categoryId;
	}

	@Override
 	public String toString(){
		return
			"DetailProductResponse{" +
			"calculationType = '" + calculationType + '\'' +
			",sizeId = '" + sizeId + '\'' +
			",colorName = '" + colorName + '\'' +
			",priceToStr = '" + priceToStr + '\'' +
			",attributeTypes = '" + attributeTypes + '\'' +
			",colorId = '" + colorId + '\'' +
			",imagePath = '" + imagePath + '\'' +
			",productNameSearch = '" + productNameSearch + '\'' +
			",priceDiscountStr = '" + priceDiscountStr + '\'' +
			",discount = '" + discount + '\'' +
			",customRate = '" + customRate + '\'' +
			",description = '" + description + '\'' +
			",deleteFlag = '" + deleteFlag + '\'' +
			",discountName = '" + discountName + '\'' +
			",totalBuyCount = '" + totalBuyCount + '\'' +
			",sizeName = '" + sizeName + '\'' +
			",countColor = '" + countColor + '\'' +
			",attributeIds = '" + attributeIds + '\'' +
			",sizeDescription = '" + sizeDescription + '\'' +
			",productColorName = '" + productColorName + '\'' +
			",price = '" + price + '\'' +
			",priceFromStr = '" + priceFromStr + '\'' +
			",rowspan = '" + rowspan + '\'' +
			",stockNum = '" + stockNum + '\'' +
			",views = '" + views + '\'' +
			",allColor = '" + allColor + '\'' +
			",allSize = '" + allSize + '\'' +
			",productId = '" + productId + '\'' +
			",like = '" + like + '\'' +
			",productColors = '" + productColors + '\'' +
			",isLiked = '" + isLiked + '\'' +
			",index = '" + index + '\'' +
			",dateFrom = '" + dateFrom + '\'' +
			",url = '" + url + '\'' +
			",priceDiscount = '" + priceDiscount + '\'' +
			",homeInfoId = '" + homeInfoId + '\'' +
			",totalSellCount = '" + totalSellCount + '\'' +
			",priceStr = '" + priceStr + '\'' +
			",categoryIds = '" + categoryIds + '\'' +
			",material = '" + material + '\'' +
			",questionAnswers = '" + questionAnswers + '\'' +
			",name = '" + name + '\'' +
			",dateTo = '" + dateTo + '\'' +
			",style = '" + style + '\'' +
			",userGuideImg = '" + userGuideImg + '\'' +
			",categoryId = '" + categoryId + '\'' +
			"}";
		}
}