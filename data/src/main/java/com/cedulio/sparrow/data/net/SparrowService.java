package com.cedulio.sparrow.data.net;


import com.cedulio.sparrow.data.entity.BillEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface SparrowService {

    String BASE_URL = "https://s3-sa-east-1.amazonaws.com/mobile-challenge/bill/";
//    String BASE_URL = "http://demo1073540.mockable.io/";

    @GET("bill_new.json")
    Call<List<BillEntity>> listBill();

}
