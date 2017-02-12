package picload.cx.com.picload.loader;

import picload.cx.com.picload.request.BitmapRequest;

/**
 * Created by cx on 2017/2/8.
 */

public interface Loader {
    /**
     * 加载图片
     * @param bitmapRequest
     */
    void loadImage(BitmapRequest bitmapRequest);
}
