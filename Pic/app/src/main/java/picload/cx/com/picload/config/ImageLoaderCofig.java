package picload.cx.com.picload.config;

import picload.cx.com.picload.cache.BitmapCache;
import picload.cx.com.picload.policy.LoadPolicy;

/**
 * Created by cx on 2017/2/8.
 */

public class ImageLoaderCofig {
    //缓存策略
    private BitmapCache mBitmapCache;
    //加载策略
    private LoadPolicy mLoadPolicy;
    //默认线程数
    private int threadCount = Runtime.getRuntime().availableProcessors();
    //显示配置
    private DisplayConfig displayConfig=new DisplayConfig();
    private ImageLoaderCofig(){};

    /**
     * 建造者模式
     */
    public static class Builder{
        private ImageLoaderCofig config;
        public Builder(){
            config = new ImageLoaderCofig();
        }

        /**
         * 设置缓存策略
         * @param bitmapCache
         * @return
         */
        public Builder setCachePolicy(BitmapCache bitmapCache){
            config.mBitmapCache = bitmapCache;
            return this;
        }

        /**
         * 设置加载策略
         * @param loadPolicy
         * @return
         */
        public Builder setLoadPolicy(LoadPolicy loadPolicy){
            config.mLoadPolicy = loadPolicy;
            return this;
        }

        /**
         * 设置线程个数
         * @param count
         * @return
         */
        public Builder setThreadCount(int count){
            config.threadCount = count;
            return this;
        }

        /**
         * 设置加载中的图片
         * @param resID
         * @return
         */
        public Builder setLoadingImage(int resID){
            config.displayConfig.loadingImage = resID;
            return this;
        }

        /**
         * 设置加载失败的图片
         * @param resID
         * @return
         */
        public Builder setFailedImage(int resID){
            config.displayConfig.faildImage = resID;
            return this;
        }

        public ImageLoaderCofig build(){
            return config;
        }

    }

    public BitmapCache getBitmapCache() {
        return mBitmapCache;
    }

    public LoadPolicy getLoadPolicy() {
        return mLoadPolicy;
    }

    public int getThreadCount() {
        return threadCount;
    }

    public DisplayConfig getDisplayConfig() {
        return displayConfig;
    }
}
