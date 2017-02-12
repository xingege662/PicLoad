package picload.cx.com.picload.request;

import android.util.Log;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import static android.content.ContentValues.TAG;


/**
 * Created by cx on 2017/2/8.
 */

public class RequestQueue {
    /*
     * 阻塞式队列
     * 多线程共享
     * 生产效率和消费效率相差太远
     * disPlayImage()
     * 使用优先级队列
     * 优先级高的队列先被消费
     * 每一个产品都有编号
     */
    private BlockingQueue<BitmapRequest> mRequestQueue = new PriorityBlockingQueue<BitmapRequest>();
    //转发器的数量
    private int threadCount;
    //线程安全的int值
    private AtomicInteger i = new AtomicInteger(0);
    //一组转发器
    private RequestDispatcher[] mRequestDispatchers;

    public RequestQueue(int threadCount) {
        this.threadCount = threadCount;
    }

    /**
     * 添加请求对象
     * @param bitmapRequest
     */
    public void addRequest(BitmapRequest bitmapRequest){
        //判断队列中是否有请求对象
        if(!mRequestQueue.contains(bitmapRequest)){
            //给请求进行编号
            try {
                bitmapRequest.setSerialNumber(i.incrementAndGet());
                mRequestQueue.put(bitmapRequest);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else{
            Log.i(TAG, "请求已经存在 编号： "+bitmapRequest.getSerialNumber());
        }
    }

    /**
     * 开启请求
     */
    public void start(){
        //先停止
        stop();
        startDispatchers();

    }

    /**
     * 开启转发器
     */
    private void startDispatchers() {
        mRequestDispatchers = new RequestDispatcher[threadCount];
        for(int i = 0; i<threadCount;i++){
            RequestDispatcher p = new RequestDispatcher(mRequestQueue);
            mRequestDispatchers[i] = p;
            p.start();
        }
    }

    /**
     * 停止
     */
    public void stop(){

    }
}
