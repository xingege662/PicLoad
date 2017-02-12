package picload.cx.com.picload.policy;

import picload.cx.com.picload.request.BitmapRequest;

/**
 * Created by cx on 2017/2/8.
 */

public class ReversePolicy implements  LoadPolicy {
    @Override
    public int compareTo(BitmapRequest requestOne, BitmapRequest two) {
        return two.getSerialNumber() - requestOne.getSerialNumber();
    }
}
