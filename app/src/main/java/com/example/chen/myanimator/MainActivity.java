package com.example.chen.myanimator;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.jakewharton.rxbinding2.view.RxView;
import com.ninetripods.aopermission.permissionlib.annotation.NeedPermission;
import com.ninetripods.aopermission.permissionlib.annotation.PermissionCanceled;
import com.ninetripods.aopermission.permissionlib.annotation.PermissionDenied;
import com.ninetripods.aopermission.permissionlib.bean.CancelBean;
import com.ninetripods.aopermission.permissionlib.bean.DenyBean;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.internal.schedulers.IoScheduler;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.view.MotionEvent.INVALID_POINTER_ID;

public class MainActivity extends AppCompatActivity {
    private Button btn_animator,btn_permission,btn_sendsms;
    private ImageView iv_boot;
    private ListView lv_mylist;
    private View view;
    private AnimationDrawable animationDrawable;
    private NotificationCompat.Builder mBuilder;
    private int mNotificationId = 001;
    private final String TAG = "rxjava";
    private Integer i;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Window window = getWindow();
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR2){
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            ViewGroup content = window.getDecorView().findViewById(Window.ID_ANDROID_CONTENT);
            content.getChildAt(0).setFitsSystemWindows(false);
        }
        lv_mylist = findViewById(R.id.lv_mylist);
        btn_animator = findViewById(R.id.btn_animator);
        btn_permission = findViewById(R.id.btn_permission);
        btn_sendsms = findViewById(R.id.btn_sendsms);
        iv_boot = findViewById(R.id.iv_boot);
        lv_mylist.setOnItemClickListener((adapterView, view, i, l) -> {

        });

        btn_animator.setOnClickListener(view -> {
//            myMethod();

//            doRetrofit();
//            Intent intent = new Intent(this,MyPermissionActivity.class);
//            startActivity(intent);
        });

        //防颤处理
       RxView.clicks(btn_animator)
               .throttleFirst(2,TimeUnit.SECONDS)
               .subscribe(new Observer<Object>() {

                   @Override
                   public void onSubscribe(Disposable d) {

                   }

                   @Override
                   public void onNext(Object value) {
                       doGainData();
                   }

                   @Override
                   public void onError(Throwable e) {

                   }

                   @Override
                   public void onComplete() {

                   }
               });

        /*属性动画*/
//        tv_animator.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startAnimator();
//            }
//        });
//        ValueAnimator animator = ValueAnimator.;
//        animator.setTarget(tv_animator);
//        animator.start();
//        startAnimator();
        /*跳转到新界面进行申请权限的测试*/
        btn_permission.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this,MyPermissionActivity.class);
            startActivity(intent);
        });

        btn_sendsms.setOnClickListener(view -> mySms());

        setFramDrawable();

        judgeNet();


    }

    /*
    * 启动xml动画
    * */
    private void startAnimator() {
//        ObjectAnimator objectAnimator = ObjectAnimator
//                .ofFloat(btn_animator,"RotationY",0,360f);
//        objectAnimator.setDuration(5000);
//        objectAnimator.setRepeatCount(3);
//        objectAnimator.setRepeatMode(ValueAnimator.RESTART);
//        objectAnimator.start();

        AnimationSet animationSet = new AnimationSet(true);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1,0);
        RotateAnimation rotateAnimation = new RotateAnimation(0,360,
               Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        alphaAnimation.setDuration(3000);
        alphaAnimation.setRepeatCount(3);
        rotateAnimation.setDuration(3000);
        rotateAnimation.setRepeatCount(3);
        animationSet.addAnimation(alphaAnimation);
        animationSet.addAnimation(rotateAnimation);
        btn_animator.startAnimation(animationSet);
    }

    /*
    * 帧动画设置
    * */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void setFramDrawable(){
        animationDrawable = (AnimationDrawable) getResources().getDrawable(R.drawable.fram_set);
        iv_boot.setBackground(animationDrawable);
        animationDrawable.start();
    }

    /*
    * 网络连接状态判断
    * */
    public void judgeNet(){
        ConnectivityManager conMgr = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = conMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        boolean isWifiCon = networkInfo.isConnected();
        networkInfo = conMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        boolean isMobileCon = networkInfo.isConnected();
        Log.e("tag","wifi connected :"+isWifiCon);
        Log.e("tag","mobile connected :"+isMobileCon);
        Log.e("tag","isOnline ：" + isOnline());
    }

    /*
    * isactive
    * */
    public boolean isOnline(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        return (info !=null && info.isConnected());
    }

    /*
    * notification
    * */
    public void sendNotify(){
        Intent resultIntent = new Intent(this,ActivityB.class);
        //pendingintent就是延迟跳转的意识即满足条件跳转，满足一定条件后才会执行intent跳转功能
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this,
                0,
                resultIntent,
               0
        );//即将跳转页面，但是还没有跳转页面(当满足一定条件时才会执行pendingintent跳转即点击通知条目时)

        mBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.img00)
                .setWhen(System.currentTimeMillis())
                .setContentTitle("My Notification")
                .setContentText("Hello World !")
                .setSubText("——记住我叫叶良辰")
                .setTicker("收到叶良辰发送过来的信息~")
                .setDefaults(Notification.DEFAULT_ALL)
                .setAutoCancel(true)//设置该属性，点击状态栏后该条信息自动消失
                .setStyle(new NotificationCompat.BigTextStyle().bigText("msg"))//大视图通知
        .setContentIntent(pendingIntent);

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(mNotificationId,mBuilder.build());
    }

    /***
     * 多点触控
     * @param event
     * @return
     */

    private int mActivePointedId ;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        mActivePointedId = event.getPointerId(0);
//        int pointerIndex = event.findPointerIndex(mActivePointedId);
//        float x = event.getX(pointerIndex);
//        float y = event.getY(pointerIndex);
//        Toast.makeText(this,""+x+"    "+y,Toast.LENGTH_SHORT).show();
        int action = MotionEventCompat.getActionMasked(event);
        int index = MotionEventCompat.getActionIndex(event);
        int xPos = -1;
        int yPos = -1;
        Log.e("the action is:", actionToString(action));
        if (event.getPointerCount() >1 ){
            Log.e("Debug_tag","Multitouch Event");
            xPos = (int) MotionEventCompat.getX(event,index);
            yPos = (int) MotionEventCompat.getY(event,index);
        }else {
            Log.e("Debug_tag","Singletouch Event");
            xPos = (int) MotionEventCompat.getX(event,index);
            yPos = (int) MotionEventCompat.getY(event,index);
        }
        return super.onTouchEvent(event);
    }

    public static String actionToString(int action){
        switch (action){
            case MotionEvent.ACTION_DOWN:
                return "Down";
//            case MotionEvent.ACTION_MOVE:
//                return "Move";
            case MotionEvent.ACTION_POINTER_DOWN:
                return "PointerDown";
            case MotionEvent.ACTION_UP:
                return "Up";
            case MotionEvent.ACTION_POINTER_UP:
                return "PointerUp";
            case MotionEvent.ACTION_OUTSIDE:
                return "OutSide";
            case MotionEvent.ACTION_CANCEL:
                return "Cancel";
        }
        return "";
    }

    /*
    * 发送短信
    * */
    public void sendSms(){
        String msg ="你是谁";
        String num = "15733250850";
        SmsManager smsManager = SmsManager.getDefault();
        Intent intent = new Intent(this,SmsActivity.class);
        PendingIntent pendingIntent =PendingIntent.getActivity(this,0,intent,0);
        smsManager.sendTextMessage(num,null,msg,pendingIntent,null);
    }

    public void myMethod(){
//        Toast.makeText(this,"点击了一下",Toast.LENGTH_SHORT).show();
//       Observable<Integer>observable = Observable.create(new ObservableOnSubscribe<Integer>() {
//           @Override
//           public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
//               emitter.onNext(1);
//               emitter.onNext(2);
//               emitter.onNext(3);
//               emitter.onComplete();
//           }
//       });
        //defer只有在订阅时才会创建被观察者
//        i = 10;
//        Observable<Integer> observable = Observable.defer(new Callable<ObservableSource<? extends Integer>>() {
//            @Override
//            public ObservableSource<? extends Integer> call() throws Exception {
//                return Observable.just(i);
//            }
//        });
//
//        i = 15;

//        Observer<Integer>observer = new Observer<Integer>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//                Log.d("rxjava", "开始采用subscribe连接");
//            }
//
//            @Override
//            public void onNext(Integer value) {
//                Log.d("rxjava", "对Next事件"+ value +"作出响应"  );
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Log.d("rxjava", "对Error事件作出响应");
//            }
//
//            @Override
//            public void onComplete() {
//                Log.d("rxjava", "对Complete事件作出响应");
//            }
//        };
//
//        observable.subscribe(observer);

        //rxjava流式操作
//        Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
//                emitter.onNext(1);
//                emitter.onNext(2);
//                emitter.onNext(3);
//                emitter.onComplete();
//            }
//        }).subscribe(new Observer<Integer>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//                Log.d("rxjava", "开始采用subscribe连接");
//            }
//
//            @Override
//            public void onNext(Integer value) {
//                Log.d("rxjava", "对Next事件"+ value +"作出响应"  );
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Log.d("rxjava", "对Error事件作出响应");
//            }
//
//            @Override
//            public void onComplete() {
//                Log.d("rxjava", "对Complete事件作出响应");
//            }
//        });

//        Observable.timer(5, TimeUnit.SECONDS)
//        Observable.interval(5,1,TimeUnit.SECONDS)
//        Observable.intervalRange(3,5,5, 1,TimeUnit.SECONDS)
        Observable.range(3,10)
                // 该例子发送的事件序列特点：从3开始发送，每次发送事件递增1，一共发送10个事件
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "开始采用subscribe连接");
                    }
                    // 默认最先调用复写的 onSubscribe（）

                    @Override
                    public void onNext(Integer value) {
                        Log.d(TAG, "接收到了事件"+ value  );
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "对Complete事件作出响应");
                    }

                });
    }

    // 可重试次数
    private int maxConnectCount = 10;
    // 当前已重试次数
    private int currentRetryCount = 0;
    // 重试等待时间
    private int waitRetryTime = 0;

    private void doGainData(){
//        Observable.interval(5,1,TimeUnit.SECONDS)
                /***
                 * 每次发送数字前发送一次网络请求在next（）事件前调用
                 * 即每次发送一个数字前就进行一次网络请求，从而实现轮询需求
                 */
//                .doOnNext(new Consumer<Long>() {
//                    @Override
//                    public void accept(Long aLong) throws Exception {
                        //1、创建retrofit实例
                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl("http://fy.iciba.com/")
                                .addConverterFactory(GsonConverterFactory.create())//设置Gson解析
                                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//支持rxjava2
                                .build();

                        //2、创建网络请求接口实例
                        GetRequest_Interface getRequest_interface = retrofit.create(GetRequest_Interface.class);

                        //3、采用Observable<...>形式 对 网络请求 进行封装
                        Observable<Translation> observable = getRequest_interface.getCall();

//                        //4、通过线程切换发送网络请求
//                        observable.subscribeOn(new IoScheduler())//在io线程进行网络请求
//                                .observeOn(AndroidSchedulers.mainThread())//在主线程进行数据处理
//                                .subscribe(new Observer<Translation>() {
//                            @Override
//                            public void onSubscribe(Disposable d) {
//
//                            }
//
//                            @Override
//                            public void onNext(Translation value) {
//                                Log.e(TAG,value.getContent().getCiba_use());
//                            }
//
//                            @Override
//                            public void onError(Throwable e) {
//                                Log.d(TAG,"请求失败");
//                            }
//
//                            @Override
//                            public void onComplete() {
//
//                            }
//                        });

                        observable.retryWhen(new Function<Observable<Throwable>, ObservableSource<?>>() {
                            @Override
                            public ObservableSource<?> apply(Observable<Throwable> throwableObservable) throws Exception {
                                return throwableObservable.flatMap(new Function<Throwable, ObservableSource<?>>() {
                                    @Override
                                    public ObservableSource<?> apply(Throwable throwable) throws Exception {
                                        Log.d("retrywhen",throwable.toString());
                                        if (throwable instanceof IOException){
                                            Log.d("retrywhen:","发生的异常为IO异常");
                                            if (currentRetryCount < maxConnectCount) {
                                                currentRetryCount++;//先赋值后加1
                                                Log.d("retrywhen:","重试次数为"+currentRetryCount);
                                                waitRetryTime = 1000 + currentRetryCount * 1000;
                                                return Observable.just(1).delay(waitRetryTime, TimeUnit.SECONDS);
                                            }else {
                                                return Observable.error(new Throwable("重置次数已经超过设置的总次数"));
                                            }
                                        }else {
                                            return Observable.error(new Throwable("发生了非网络异常（非I/O异常）"));
                                        }
                                    }
                                });
                            }
                        })

//                    }
//                })
                .subscribeOn(Schedulers.io())//在I/O线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Translation>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Translation value) {
                        Log.d(TAG,  "发送成功");
                        Log.d("retrywhen:",value.getContent().getOut());
                    }

                    @Override
                    public void onError(Throwable e) {
                        // 获取停止重试的信息
                        Log.d(TAG,  e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void doRetrofit(){
        Info info = new Info("18610696527","a00000");
        Gson gson = new Gson();
        String obj = gson.toJson(info);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.130.73:8086/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        RequestBody body=RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),obj);
        final GetRequest_Interface getRequestInterface = retrofit.create(GetRequest_Interface.class);
        getRequestInterface.getUser(body).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Toast.makeText(MainActivity.this,"请求成功",Toast.LENGTH_SHORT).show();
                Log.d("response", response.body().getReturnData().getDwSessionConf().getLxr());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("failure",t.toString());
            }
        });
    }

    @NeedPermission(value = {Manifest.permission.SEND_SMS},requestCode = 5)
    public void mySms(){
        Toast.makeText(this,"发送短信权限申请成功",Toast.LENGTH_SHORT).show();
        sendSms();
    }

    @PermissionDenied
    public void denySms(DenyBean denyBean){
        Toast.makeText(this,"发送短信权限申请被拒绝",Toast.LENGTH_SHORT).show();
    }

    @PermissionCanceled
    public void  cancelSms(CancelBean cancelBean){
        Toast.makeText(this,"发送短信权限申请被取消",Toast.LENGTH_SHORT).show();
    }
}
