package picload.cx.com.picload.policy;

import picload.cx.com.picload.request.BitmapRequest;

/**
 * Created by cx on 2017/2/8.
 * 加载策略
 */

public interface LoadPolicy {
    /**
     * 两个Bitmap进行优先级比较
     * @param requestOne
     * @param two
     * @return
     */
    public int compareTo(BitmapRequest requestOne,BitmapRequest two);
}
