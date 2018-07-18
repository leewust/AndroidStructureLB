package com.app.liliuhuan.armstest.mvp.model;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;

import com.app.liliuhuan.armstest.api.service.UserService;
import com.app.liliuhuan.armstest.bean.User;
import com.app.liliuhuan.armstest.mvp.contract.UserContract;
import com.app.liliuhuan.mylibrary.base.mvp.BaseModel;
import com.app.liliuhuan.mylibrary.di.scope.ActivityScope;
import com.app.liliuhuan.mylibrary.http.net.IRepositoryManager;

import org.json.JSONObject;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import timber.log.Timber;


@ActivityScope
public class UserModel extends BaseModel implements UserContract.Model {
    public static final int USERS_PER_PAGE = 10;

    @Inject
    public UserModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }


    @Override
    public Observable<List<User>> getUsers(int lastIdQueried, boolean update) {
        //使用rxcache缓存,上拉刷新则不读取缓存,加载更多读取缓存
//        return Observable.just(mRepositoryManager.obtainRetrofitService(UserService.class)
//                .getUsers(lastIdQueried, USERS_PER_PAGE))
//                .flatMap(new Function<Observable<List<User>>, ObservableSource<List<User>>>() {
//                    @Override
//                    public ObservableSource<List<User>> apply(@NonNull Observable<List<User>> listObservable) throws Exception {
//                        return mRepositoryManager.obtainCacheService(CommonCache.class)
//                                .getUsers(listObservable
//                                        , new DynamicKey(lastIdQueried)
//                                        , new EvictDynamicKey(update))
//                                .map(listReply -> listReply.getData());
//                    }
//                });
        return  mRepositoryManager.obtainRetrofitService(UserService.class)
                .getUsers(lastIdQueried, USERS_PER_PAGE);
    }

    @Override
    public Observable<String> getBanners() {
        return mRepositoryManager.obtainRetrofitService(UserService.class)
                .getBanner();
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    void onPause() {
        Timber.d("Release Resource");
    }
}