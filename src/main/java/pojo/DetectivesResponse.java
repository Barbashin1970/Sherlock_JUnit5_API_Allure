package pojo;

import java.util.List;


public class DetectivesResponse {
    private List<Detective> detectives;
    private boolean success;

    public DetectivesResponse(List<Detective> detectives, boolean success) {
        this.detectives = detectives;
        this.success = success;
    }

    public DetectivesResponse() {

    }

    public List<Detective> getDetectives() {
        return detectives;
    }

    public void setDetectives(List<Detective> detectives) {
        this.detectives = detectives;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }



}
