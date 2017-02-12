package picload.cx.com.picload.loader;

import android.graphics.Bitmap;

import picload.cx.com.picload.request.BitmapRequest;

/**
 * Created by cx on 2017/2/12.
 */

public class NullLoader extends AbstractLoader {
    @Override
    public Bitmap onLoad(BitmapRequest bitmapRequest) {
        return null;
    }
}
