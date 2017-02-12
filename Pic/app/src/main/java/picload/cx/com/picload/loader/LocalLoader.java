package picload.cx.com.picload.loader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import java.io.File;

import picload.cx.com.picload.request.BitmapRequest;
import picload.cx.com.picload.utils.BitmapDemoder;
import picload.cx.com.picload.utils.ImageViewHelper;

/**
 * Created by cx on 2017/2/8.
 */

public class LocalLoader extends AbstractLoader {
    @Override
    public Bitmap onLoad(BitmapRequest bitmapRequest) {
        //得到本地图片的路径
        final String path= Uri.parse(bitmapRequest.getImageUrl()).getPath();
        File file=new File(path);
        if(!file.exists())
        {
            return null;
        }
        BitmapDemoder decoder=new BitmapDemoder() {
            @Override
            public Bitmap decodeBitmapWithOption(BitmapFactory.Options options) {
                return BitmapFactory.decodeFile(path,options);
            }
        };

        return decoder.decodeBitmap(ImageViewHelper.getImageViewWidth(bitmapRequest.getImageView())
                ,ImageViewHelper.getImageViewHeight(bitmapRequest.getImageView()));
    }
}
