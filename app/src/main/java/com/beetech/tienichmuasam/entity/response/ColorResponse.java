package com.beetech.tienichmuasam.entity.response;

import java.util.ArrayList;
import java.util.List;

public class ColorResponse {
    private String colorId;
    private String name;
    private String imagePath;
    private String sizeIds;

    public String getColorId() {
        return colorId;
    }

    public void setColorId(String colorId) {
        this.colorId = colorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getSizeIds() {
        return sizeIds;
    }

    public void setSizeIds(String sizeIds) {
        this.sizeIds = sizeIds;
    }

    public List<SizeResponse> getListSizes(List<SizeResponse> data) {
        List<SizeResponse> filters = new ArrayList<>();
        for (SizeResponse sizeResponse : data) {
            if(sizeIds.contains(sizeResponse.getName())) {
                filters.add(sizeResponse);
            }
        }
        return filters;
    }
}
