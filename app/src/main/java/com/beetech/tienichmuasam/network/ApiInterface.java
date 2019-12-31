package com.beetech.tienichmuasam.network;


import com.beetech.tienichmuasam.base.ListResponse;
import com.beetech.tienichmuasam.entity.response.ListProductResponse;
import com.beetech.tienichmuasam.entity.response.SearchResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("search")
    @Headers({"lang: vi","Content-Type: application/json"})
    Single<ListResponse<SearchResponse>> search(@Query("s") String keyword,
                                                @Query("page") int pageIndex);

    @GET("products")
    Single<ListResponse<ListProductResponse>> getListProduct(@Query("category_id") Integer categoryID,
                                                     @Query("page") int pageIndex);
}
