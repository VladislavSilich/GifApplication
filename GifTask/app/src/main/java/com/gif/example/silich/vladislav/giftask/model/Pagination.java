
package com.gif.example.silich.vladislav.giftask.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pagination {

    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("offset")
    @Expose
    private Integer offset;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

}