package picload.cx.com.picload.loader;

import android.graphics.Bitmap;
import android.widget.ImageView;

import picload.cx.com.picload.cache.BitmapCache;
import picload.cx.com.picload.config.DisplayConfig;
import picload.cx.com.picload.request.BitmapRequest;

/**
 * Created by cx on 2017/2/8.
 */

public abstract  class AbstractLoader implements  Loader {
    //拿到用户自定义配置的缓存策略
    private BitmapCache mBitmapCache = SimpleImageLoader.getInstance().getCofig().getBitmapCache();
    //拿到显示配置
    private DisplayConfig mDisplayConfig = SimpleImageLoader.getInstance().getCofig().getDisplayConfig();


    @Override
    public void loadImage(BitmapRequest bitmapRequest) {
        //从缓存中取到Bitmap
        Bitmap bitmap = mBitmapCache.get(bitmapRequest);
        if(bitmap==null){
            //显示默认加载图片
            showLoaingImage(bitmapRequest);
            //开始真正的加载
            bitmap = onLoad(bitmapRequest);
            //缓存图片
            cacheBitmap(bitmapRequest,bitmap);
        }
        deliveryToUIThread(bitmapRequest,bitmap);

    }

    /**
     * 缓存图片
     * @param request
     * @param bitmap
     */
    private void cacheBitmap(BitmapRequest request, Bitmap bitmap) {
        if(request!=null&&bitmap!=null){
            synchronized (AbstractLoader.class){
                mBitmapCache.put(request,bitmap);
            }
        }
    }

    protected  void showLoaingImage(BitmapRequest request){
        if(hasLoadingPlaceHolder()){
            final ImageView imageView = request.getImageView();
            if(imageView!=null){
                imageView.post(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setImageResource(mDisplayConfig.loadingImage);
                    }
                });
            }
        }
    }

    private boolean hasLoadingPlaceHolder() {
        return (mDisplayConfig!=null && mDisplayConfig.loadingImage>0);
    }

    public abstract  Bitmap onLoad(BitmapRequest bitmapRequest);

    /**
     * 交给主线程显示
     * @param request
     * @param bitmap
     */
    protected void deliveryToUIThread(final BitmapRequest request, final Bitmap bitmap) {
        ImageView imageView = request.getImageView();
        if(imageView!=null)
        {
            imageView.post(new Runnable() {
                @Override
                public void run() {
                    updateImageView(request, bitmap);
                }

            });
        }

    }
    private void updateImageView(final BitmapRequest request, final Bitmap bitmap) {
        ImageView imageView = request.getImageView();
        //加载正常  防止图片错位
        if(bitmap != null && imageView.getTag().equals(request.getImageUrl())){
            imageView.setImageBitmap(bitmap);
        }
        //有可能加载失败
        if(bitmap == null && mDisplayConfig!=null&&mDisplayConfig.faildImage!=-1){
            imageView.setImageResource(mDisplayConfig.faildImage);
        }
        //监听
        //回调 给圆角图片  特殊图片进行扩展
        if(request.mImageListener != null){
            request.mImageListener.onComplete(imageView, bitmap, request.getImageUrl());
        }
    }

}
