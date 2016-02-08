package com.cedulio.sparrow.domain.interactor.bill;


import com.cedulio.sparrow.domain.executor.Executor;
import com.cedulio.sparrow.domain.executor.MainThread;
import com.cedulio.sparrow.domain.interactor.base.AbstractAsyncUseCase;
import com.cedulio.sparrow.domain.model.Bill;
import com.cedulio.sparrow.domain.repository.BillRepository;

import java.util.List;

public class GetBills extends AbstractAsyncUseCase {

    private CallBack callBack;
    private BillRepository billRepository;

    public GetBills(BillRepository billRepository, CallBack callBack, MainThread mainThread, Executor threadExecutor) {
        super(threadExecutor, mainThread);

        setBillRepository(billRepository);
        setCallBack(callBack);
    }

    @Override
    public void run() {
        try {
            List<Bill> list = getBillRepository().getBills();
            getCallBack().onLoadBills(list);
        } catch (Exception e) {
            getCallBack().onErrorLoadingBills(e);
        }
    }

    public interface CallBack {
        void onLoadBills(List<Bill> bills);

        void onErrorLoadingBills(Exception e);
    }

    private CallBack getCallBack() {
        return callBack;
    }

    private BillRepository getBillRepository() {
        return billRepository;
    }

    private void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    private void setBillRepository(BillRepository billRepository) {
        this.billRepository = billRepository;
    }
}


