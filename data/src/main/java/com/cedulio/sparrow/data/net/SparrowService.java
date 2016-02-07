package com.cedulio.sparrow.data.net;


import com.cedulio.sparrow.data.transfer.BillTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface SparrowService {

    String BASE_URL = "https://s3-sa-east-1.amazonaws.com/";

    @GET("mobile-challenge/bill/bill_new.json")
    Call<List<BillTO>> listBill();
}
