package th.ac.kmitl.it.nextstop.Service;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

/**
 * Created by The9uide on 29-Apr-17.
 */

public class LocationReceiver extends ResultReceiver {
    private Receiver receiver;

    // Constructor takes a handler
    public LocationReceiver(Handler handler) {
        super(handler);
    }

    // Setter for assigning the receiver
    public void setReceiver(Receiver receiver) {
        this.receiver = receiver;
    }

    // Defines our event interface for communication
    public interface Receiver {
        void onReceiveResult(int resultCode, Bundle resultData);
    }

    // Delegate method which passes the result to the receiver if the receiver has been assigned
    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        if (receiver != null) {
            receiver.onReceiveResult(resultCode, resultData);
        }
    }
}