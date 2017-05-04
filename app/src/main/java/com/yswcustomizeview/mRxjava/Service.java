package com.yswcustomizeview.mRxjava;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by： Ysw on 2016/7/6.11:50.
 */
public interface Service {
    @GET(Url.DISHESLIST)
    Observable<DishesMode> getDishesList(@Query("storeId") String storeId);
}
