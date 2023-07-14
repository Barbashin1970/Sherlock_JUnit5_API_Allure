package pojo;

import com.google.gson.annotations.SerializedName;

public class Category {
    @SerializedName(value = "CategoryID")
    private int categoryId;
    @SerializedName(value = "CategoryName")
    private String categoryName;
    private Extra extra;

    public Category(int categoryId, String categoryName, Extra extra) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.extra = extra;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Extra getExtra() {
        return extra;
    }

    public void setExtra(Extra extra) {
        this.extra = extra;
    }
}
