package com.beetech.tienichmuasam.network;


import com.beetech.tienichmuasam.base.ListLoadmoreReponse;
import com.beetech.tienichmuasam.base.ListResponse;
import com.beetech.tienichmuasam.base.ObjectResponse;
import com.beetech.tienichmuasam.entity.response.BannerResponse;
import com.beetech.tienichmuasam.entity.response.DetailProductResponse;
import com.beetech.tienichmuasam.entity.response.ListProductResponse;
import com.beetech.tienichmuasam.entity.response.SearchResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("search")
    @Headers({"lang: vi", "Content-Type: application/json"})
    Single<ListLoadmoreReponse<SearchResponse>> search(@Query("s") String keyword,
                                                       @Query("page") int pageIndex);

    @GET("san_pham/search")
    Single<ListLoadmoreReponse<ListProductResponse>> getListProduct(@Query("categoryId") Integer categoryID,
                                                                    @Query("page") int pageIndex);

    @GET("banner")
    Single<ListResponse<BannerResponse>> getListHomeBanner();

    @GET("san_pham/detail/{product_id}")
    Single<ObjectResponse<DetailProductResponse>> getDetailProduct(@Path("product_id") String productId,
                                                                   @Query("color") int colorId);
}
