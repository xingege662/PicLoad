package picload.cx.com.picload.cache;

import android.graphics.Bitmap;
import android.util.LruCache;

import picload.cx.com.picload.request.BitmapRequest;

/**
 * Created by cx on 2017/2/8.
 */

public class MemoryCache  implements BitmapCache{
    private LruCache<String,Bitmap> mLruCache;
    public MemoryCache()
    {
        int maxSize= (int) (Runtime.getRuntime().freeMemory()/1024/8);
        mLruCache=new LruCache<String,Bitmap>(maxSize)
        {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes()*value.getHeight();
            }
        };
    }


    @Override
    public void put(BitmapRequest bitmapRequest, Bitmap bitmap) {
        mLruCache.put(bitmapRequest.getImageUriMD5(),bitmap);
    }

    @Override
    public Bitmap get(BitmapRequest request) {
        String url = request.getImageUriMD5();
        return  mLruCache.get(url);
    }

    @Override
    public void remove(BitmapRequest request) {
        mLruCache.remove(request.getImageUriMD5());
    }
}
