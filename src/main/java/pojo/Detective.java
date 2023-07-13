package pojo;

import java.util.List;

public class Detective {
    private int MainId;
    private String firstName;
    private String lastName;
    private boolean violinPlayer;
    private List<Category> categories;

    public int getMainId() {
        return MainId;
    }

    public void setMainId(int MainId) {
        this.MainId = MainId;
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
