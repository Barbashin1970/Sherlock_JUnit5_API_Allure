package pojo;

import java.util.List;

public class Extra {
    private List<ExtraItem> extraArray;

    public Extra(List<ExtraItem> extraArray) {
        this.extraArray = extraArray;
    }

    public List<ExtraItem> getExtraArray() {
        return extraArray;
    }

    public void setExtraArray(List<ExtraItem> extraArray) {
        this.extraArray = extraArray;
    }
}
