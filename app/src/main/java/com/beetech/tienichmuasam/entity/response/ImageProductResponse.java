package com.beetech.tienichmuasam.entity.response;

import com.google.gson.annotations.SerializedName;

public class ImageProductResponse{

	@SerializedName("deleteFlag")
	private int deleteFlag;

	@SerializedName("imageId")
	private int imageId;

	@SerializedName("size")
	private Object size;

	@SerializedName("imagePath")
	private String imagePath;

	@SerializedName("colorId")
	private String colorId;

	@SerializedName("name")
	private String name;

	@SerializedName("parentId")
	private String parentId;

	public void setDeleteFlag(int deleteFlag){
		this.deleteFlag = deleteFlag;
	}

	public int getDeleteFlag(){
		return deleteFlag;
	}

	public void setImageId(int imageId){
		this.imageId = imageId;
	}

	public int getImageId(){
		return imageId;
	}

	public void setSize(Object size){
		this.size = size;
	}

	public Object getSize(){
		return size;
	}

	public void setImagePath(String imagePath){
		this.imagePath = imagePath;
	}

	public String getImagePath(){
		return imagePath;
	}

	public void setColorId(String colorId){
		this.colorId = colorId;
	}

	public String getColorId(){
		return colorId;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setParentId(String parentId){
		this.parentId = parentId;
	}

	public String getParentId(){
		return parentId;
	}

	@Override
 	public String toString(){
		return 
			"ImageProductResponse{" + 
			"deleteFlag = '" + deleteFlag + '\'' + 
			",imageId = '" + imageId + '\'' + 
			",size = '" + size + '\'' + 
			",imagePath = '" + imagePath + '\'' + 
			",colorId = '" + colorId + '\'' + 
			",name = '" + name + '\'' + 
			",parentId = '" + parentId + '\'' + 
			"}";
		}
}