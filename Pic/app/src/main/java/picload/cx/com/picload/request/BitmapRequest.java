package picload.cx.com.picload.request;

import android.widget.ImageView;

import java.lang.ref.SoftReference;

import picload.cx.com.picload.config.DisplayConfig;
import picload.cx.com.picload.loader.SimpleImageLoader;
import picload.cx.com.picload.policy.LoadPolicy;
import picload.cx.com.picload.utils.MD5Utils;

/**
 * Created by cx on 2017/2/8.
 */

public class BitmapRequest implements Comparable<BitmapRequest>  {
    //持有ImageView的软引用
    private SoftReference<ImageView> imageViewSoft;

    //图片路径
    private String imageUrl;

    //图片路径的MD5
    private String imageUrlMD5;

    //下载完成监听
    public SimpleImageLoader.ImageListener mImageListener;

    private DisplayConfig mDisplayConfig;

    private LoadPolicy mLoadPolicy = SimpleImageLoader.getInstance().getCofig().getLoadPolicy();

    private int serialNumber;

    private String imageUriMD5;

    public BitmapRequest(ImageView imageView, String imageUrl, SimpleImageLoader.ImageListener imageListener, DisplayConfig displayConfig) {
        this.imageViewSoft = new SoftReference<ImageView>(imageView);
        //设置客店的Image的tag，要下载的图片路径
        imageView.setTag(imageUrl);
        this.imageUrl = imageUrl;
        this.imageUriMD5= MD5Utils.toMD5(imageUrl);
        this.mImageListener = imageListener;
        if(displayConfig!=null){
            this.mDisplayConfig = displayConfig;
        }
    }



    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BitmapRequest request = (BitmapRequest) o;

        if (serialNumber != request.serialNumber) return false;
        return mLoadPolicy != null ? mLoadPolicy.equals(request.mLoadPolicy) : request.mLoadPolicy == null;

    }

    @Override
    public int hashCode() {
        int result = mLoadPolicy != null ? mLoadPolicy.hashCode() : 0;
        result = 31 * result + serialNumber;
        return result;
    }
    public ImageView getImageView(){
        return imageViewSoft.get();
    }

    public String getImageUrl() {
        return imageUrl;
    }


    public SimpleImageLoader.ImageListener getImageListener() {
        return mImageListener;
    }

    public DisplayConfig getDisplayConfig() {
        return mDisplayConfig;
    }

    public LoadPolicy getLoadPolicy() {
        return mLoadPolicy;
    }

    public String getImageUriMD5() {
        return imageUriMD5;
    }

    @Override
    public int compareTo(BitmapRequest o) {
        return mLoadPolicy.compareTo(o,this);
    }
}
