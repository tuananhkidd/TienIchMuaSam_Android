package com.beetech.tienichmuasam.ui.product;

import androidx.lifecycle.MutableLiveData;

import com.beetech.tienichmuasam.BaseApplication;
import com.beetech.tienichmuasam.base.BaseViewModel;
import com.beetech.tienichmuasam.base.ListLoadmoreReponse;
import com.beetech.tienichmuasam.base.ListResponse;
import com.beetech.tienichmuasam.base.ListResponseBody;
import com.beetech.tienichmuasam.base.ObjectResponse;
import com.beetech.tienichmuasam.entity.response.CommentResponse;
import com.beetech.tienichmuasam.entity.response.DetailProductResponse;
import com.beetech.tienichmuasam.network.repository.Repository;
import com.beetech.tienichmuasam.utils.Utils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class DetaillProductViewModel extends BaseViewModel {
    private MutableLiveData<ObjectResponse<DetailProductResponse>> detail = new MutableLiveData<>();
    private String productId;
    private int colorId;
    private MutableLiveData<ListLoadmoreReponse<CommentResponse>> comments = new MutableLiveData<>();
    private Repository repository;

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }

    public MutableLiveData<ObjectResponse<DetailProductResponse>> getDetail() {
        return detail;
    }

    public MutableLiveData<ListLoadmoreReponse<CommentResponse>> getComments() {
        return comments;
    }

    @Inject
    public DetaillProductViewModel(Repository repository) {
        this.repository = repository;
    }

    public void getDetailProduct() {
//        DetailProductResponse detailProductResponse =
//                new Gson().fromJson(Utils.readJsonFormAsset(BaseApplication.getContext(), "detail.json"), DetailProductResponse.class);
//        detail.setValue(new ObjectResponse<DetailProductResponse>().success(detailProductResponse));

        mDisposable.add(repository.getDetailProduct(productId, colorId)
                .doOnSubscribe(disposable -> {
                    detail.setValue(new ObjectResponse<DetailProductResponse>().loading());
                })
                .subscribe(
                        response -> {
                            detail.setValue(new ObjectResponse<DetailProductResponse>().success(response.getData()));
                        },
                        throwable -> {
                            detail.setValue(new ObjectResponse<DetailProductResponse>().error(throwable));
                        }
                ));
    }

    public void getCommentsAboutProduct(boolean isRefresh) {
        //fixme call api
        Type commentListType = new TypeToken<ArrayList<CommentResponse>>() {
        }.getType();
        List<CommentResponse> data = new Gson().fromJson(commentJson, commentListType);

        comments.setValue(new ListLoadmoreReponse<CommentResponse>().success(new ListResponseBody(data), isRefresh,
                true));
    }

    private String commentJson = "[\n" +
            "  {\n" +
            "  \"userId\":\"UI100000042\",\n" +
            "  \"productId\":\"A101\",\n" +
            "  \"createdDate\":\"12/12/2019\",\n" +
            "  \"content\":\"Món hàng rất đẹp,Ahuhu!\",\n" +
            "  \"rating\":4.0,\n" +
            "  \"userAvatar\":\"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRkJwJD-KnYOYHuWNTV1WDbwD_BHyxGDbqMi5kvJ05220IcaT52qw&s\",\n" +
            "  \"userName\":\"Tuan Anh Kidd\"\n" +
            "},\n" +
            "{\n" +
            "  \"userId\":\"UI100000042\",\n" +
            "  \"productId\":\"A101\",\n" +
            "  \"createdDate\":\"12/12/2019\",\n" +
            "  \"content\":\"Món hàng rất đẹp,Ahuhu!\",\n" +
            "  \"rating\":4.5,\n" +
            "  \"userAvatar\":\"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTaYJHeBYhbjVP003XFQSn0iGAL9IRZL3QbgNrGaSBfFJDnOPuc&s\",\n" +
            "  \"userName\":\"Tuan Anh Kidd\"\n" +
            "},\n" +
            "{\n" +
            "  \"userId\":\"UI100000042\",\n" +
            "  \"productId\":\"A101\",\n" +
            "  \"createdDate\":\"12/12/2019\",\n" +
            "  \"content\":\"Món hàng rất xấu ,ship thì chậm và không tử tế và có ý định bố  láo với tôi.lại còn có vấn đề về quang học\",\n" +
            "  \"rating\":1.0,\n" +
            "  \"userAvatar\":\"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQOoXF47Ck4noSIej0cepYFs-9LmhW7z7aN_ltSF0tMHC_Q_oo8IA&s\",\n" +
            "  \"userName\":\"Tuan Anh Kidd\"\n" +
            "},\n" +
            "{\n" +
            "  \"userId\":\"UI100000042\",\n" +
            "  \"productId\":\"A101\",\n" +
            "  \"createdDate\":\"12/12/2019\",\n" +
            "  \"content\":\"Món hàng rất đẹp,Ahuhu!\",\n" +
            "  \"rating\":3.5,\n" +
            "  \"userAvatar\":\"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRkJwJD-KnYOYHuWNTV1WDbwD_BHyxGDbqMi5kvJ05220IcaT52qw&s\",\n" +
            "  \"userName\":\"Tuan Anh Kidd\"\n" +
            "}\n" +
            ",\n" +
            "{\n" +
            "  \"userId\":\"UI100000042\",\n" +
            "  \"productId\":\"A101\",\n" +
            "  \"createdDate\":\"12/12/2019\",\n" +
            "  \"content\":\"Món hàng rất đẹp,Ahuhu!\",\n" +
            "  \"rating\":3.5,\n" +
            "  \"userAvatar\":\"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRkJwJD-KnYOYHuWNTV1WDbwD_BHyxGDbqMi5kvJ05220IcaT52qw&s\",\n" +
            "  \"userName\":\"Tuan Anh Kidd\"\n" +
            "}\n" +
            ",\n" +
            "{\n" +
            "  \"userId\":\"UI100000042\",\n" +
            "  \"productId\":\"A101\",\n" +
            "  \"createdDate\":\"12/12/2019\",\n" +
            "  \"content\":\"Món hàng rất đẹp,Ahuhu!\",\n" +
            "  \"rating\":3.5,\n" +
            "  \"userAvatar\":\"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRkJwJD-KnYOYHuWNTV1WDbwD_BHyxGDbqMi5kvJ05220IcaT52qw&s\",\n" +
            "  \"userName\":\"Tuan Anh Kidd\"\n" +
            "}\n" +
            ",\n" +
            "{\n" +
            "  \"userId\":\"UI100000042\",\n" +
            "  \"productId\":\"A101\",\n" +
            "  \"createdDate\":\"12/12/2019\",\n" +
            "  \"content\":\"Món hàng rất đẹp,Ahuhu!\",\n" +
            "  \"rating\":3.5,\n" +
            "  \"userAvatar\":\"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRkJwJD-KnYOYHuWNTV1WDbwD_BHyxGDbqMi5kvJ05220IcaT52qw&s\",\n" +
            "  \"userName\":\"Tuan Anh Kidd\"\n" +
            "}\n" +
            "]";
}
