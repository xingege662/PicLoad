package picload.cx.com.picload.loader;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by cx on 2017/212.
 */

public class LoadManager {
    //缓存所有支持的Loader类型
    private Map<String, Loader> mLoaderMap = new HashMap<>();

    private static LoadManager loaderManager = new LoadManager();

    private LoadManager(){
        register("http",new UrlLoader());
        register("https",new UrlLoader());
        register("file",new LocalLoader());

    }

    public  static LoadManager getInstance(){
        return loaderManager;
    }

    private void register(String schema, Loader loader) {
        mLoaderMap.put(schema,loader);
    }

    public Map<String, Loader> getLoaderMap() {
        return mLoaderMap;
    }

    public Loader getLoader(String schema){
        if(mLoaderMap.containsKey(schema)){
            return mLoaderMap.get(schema);
        }
        return new NullLoader();
    }
}
