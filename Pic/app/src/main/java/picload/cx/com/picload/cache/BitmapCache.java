package picload.cx.com.picload.cache;

import android.graphics.Bitmap;

import picload.cx.com.picload.request.BitmapRequest;

/**
 * Created by cx on 2017/2/8.
 */

public interface BitmapCache {
    /**缓存bitmap
     *
     * @param bitmapRequest
     * @param bitmap
     */
    void put(BitmapRequest bitmapRequest, Bitmap bitmap);
    /**
     *通过请求去itmap
    */
    Bitmap  get(BitmapRequest request);

    /**
     * 移除缓存
     * @param request
     */
    void remove(BitmapRequest request);

}
