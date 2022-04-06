package bg.bms.contractsapiclient.models;


import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class ContractModel implements Serializable {
    @Expose
    private String comment;

    public ContractModel() {
    }

    public ContractModel(String comments) {
        this.comment = comments;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
