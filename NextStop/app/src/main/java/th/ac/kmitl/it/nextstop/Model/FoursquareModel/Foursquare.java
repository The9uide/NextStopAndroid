
package th.ac.kmitl.it.nextstop.Model.FoursquareModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Foursquare {

    @SerializedName("meta")
    @Expose
    private Meta meta;
    @SerializedName("response")
    @Expose
    private Response response;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

}
