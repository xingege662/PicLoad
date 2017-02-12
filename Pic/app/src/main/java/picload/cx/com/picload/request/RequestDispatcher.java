package picload.cx.com.picload.request;

import android.util.Log;

import java.util.concurrent.BlockingQueue;

import picload.cx.com.picload.loader.LoadManager;
import picload.cx.com.picload.loader.Loader;

import static android.content.ContentValues.TAG;

/**
 * Created by cx on 2017/2/8.
 * 请求转发线程，不断从队列中取请求
 */

public class RequestDispatcher extends Thread {
    //请求队列
    private BlockingQueue<BitmapRequest> mRequests;

    public RequestDispatcher(BlockingQueue<BitmapRequest> requests) {
        mRequests = requests;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                //阻塞式函数
                BitmapRequest request = mRequests.take();
                /**
                 * 处理请求对象
                 */
                String schema = parseSchema(request.getImageUrl());
                //获取加载器
                Loader loder = LoadManager.getInstance().getLoader(schema);
                loder.loadImage(request);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private String parseSchema(String imageUrl) {
        if (imageUrl.contains("://")) {
            return imageUrl.split("://")[0];
        } else {
            Log.i(TAG, "不支持此类型");
        }

        return null;
    }


}
