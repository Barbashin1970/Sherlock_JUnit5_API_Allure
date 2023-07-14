package pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Detective {
    @SerializedName(value = "MainId")
    private int mainId;
    private String firstName;
    private String lastName;
    private boolean violinPlayer;
    private List<Category> categories;

    public Detective(int mainId, String firstName, String lastName, boolean violinPlayer, List<Category> categories) {
        this.mainId = mainId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.violinPlayer = violinPlayer;
        this.categories = categories;
    }

    public int getMainId() {
        return mainId;
    }

    public void setMainId(int mainId) {
        this.mainId = mainId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isViolinPlayer() {
        return violinPlayer;
    }

    public void setViolinPlayer(boolean violinPlayer) {
        this.violinPlayer = violinPlayer;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
