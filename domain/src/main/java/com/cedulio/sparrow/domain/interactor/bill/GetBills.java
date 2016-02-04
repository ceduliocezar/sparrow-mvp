package com.cedulio.sparrow.domain.interactor.bill;


import com.cedulio.sparrow.domain.Bill;
import com.cedulio.sparrow.domain.Summary;
import com.cedulio.sparrow.domain.interactor.UseCase;
import com.cedulio.sparrow.domain.repository.BillRepository;

import android.util.Log;

import java.io.IOException;
import java.util.List;

public class GetBills extends UseCase {

    private BillRepository mBillRepository;

    public GetBills(BillRepository billRepository) {
        this.mBillRepository = billRepository;
    }

    public List<Bill> execute() throws IOException {
        Log.d("debug", "execute");
        return mBillRepository.getBills();
    }


}
