package picload.cx.com.picload.loader;

import android.graphics.Bitmap;
import android.widget.ImageView;

import picload.cx.com.picload.config.DisplayConfig;
import picload.cx.com.picload.config.ImageLoaderCofig;
import picload.cx.com.picload.request.BitmapRequest;
import picload.cx.com.picload.request.RequestQueue;

/**
 * Created by cx on 2017/2/8.
 */

public class SimpleImageLoader {
    //配置文件
    private ImageLoaderCofig mCofig;
    //请求队列
    private RequestQueue mRequestQueue;
    //单例对象
    private static volatile  SimpleImageLoader sSimpleImageLoader;
    private SimpleImageLoader(){}

    private SimpleImageLoader(ImageLoaderCofig config){
        this.mCofig = config;
        mRequestQueue = new RequestQueue(config.getThreadCount());
        //开启请求队列
        mRequestQueue.start();
    }

    public  static SimpleImageLoader getInstance(ImageLoaderCofig config) {
        if (sSimpleImageLoader == null) {
            synchronized (SimpleImageLoader.class) {
                if (sSimpleImageLoader == null) {
                    sSimpleImageLoader = new SimpleImageLoader(config);
                }
            }
        }
        return sSimpleImageLoader;
    }

    /**
     * 第二次获取单例
     * @return
     */
    public  static SimpleImageLoader getInstance() {
        if(sSimpleImageLoader==null) {
            throw new UnsupportedOperationException("没有被初始化");
        }
        return sSimpleImageLoader;
    }

    /**
     *
     * @param imageView
     * @param uri http(https) file
     */
    public void displayImage(ImageView imageView,String uri){
        displayImage(imageView,uri,null,null);
    }

    /**
     * 在加载过程中定制化
     * @param imageView
     * @param uri
     * @param config
     * @param imageListener
     */
    public void displayImage(ImageView imageView, String uri, DisplayConfig config,ImageListener imageListener){
        //实例化一个请求
        BitmapRequest request = new BitmapRequest(imageView,uri,imageListener,config);
        //将请求给请求队列
        mRequestQueue.addRequest(request);
    }
    public static interface  ImageListener{
        /**
         * 判断是否下载完成
         * @param imageView
         * @param bitmap
         * @param uri
         */
        void onComplete(ImageView imageView, Bitmap bitmap,String uri);
    }

    /**
     * 拿到全局配置
     * @return
     */
    public ImageLoaderCofig getCofig(){
        return mCofig;
    }


}
