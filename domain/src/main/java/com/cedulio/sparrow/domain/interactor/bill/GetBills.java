package com.cedulio.sparrow.domain.interactor.bill;


import com.cedulio.sparrow.domain.exception.ConnectionProblemException;
import com.cedulio.sparrow.domain.executor.Executor;
import com.cedulio.sparrow.domain.executor.MainThread;
import com.cedulio.sparrow.domain.interactor.AbstractUseCase;
import com.cedulio.sparrow.domain.model.Bill;
import com.cedulio.sparrow.domain.repository.BillRepository;

import java.util.List;

public class GetBills extends AbstractUseCase {

    private CallBack callBack;

    private BillRepository billRepository;

    public GetBills(
            BillRepository billRepository, CallBack callBack, MainThread mainThread,
            Executor threadExecutor) {
        super(threadExecutor, mainThread);

        setBillRepository(billRepository);
        setCallBack(callBack);
    }

    @Override
    public void run() {
        try {
            final List<Bill> list = getBillRepository().getBills();
            deliverBillsOnMainThread(list);
        }catch (ConnectionProblemException e){
            notifyOnMainThread(e);
        } catch (Exception e) {
            notifyErrorOnMainThread(e);
        }
    }

    private void notifyOnMainThread(final ConnectionProblemException e) {
        getMainThread().post(new Runnable() {
            @Override
            public void run() {
                getCallBack().connectionProblemLoadingBills(e);
            }
        });
    }

    private void notifyErrorOnMainThread(final Exception e) {
        getMainThread().post(new Runnable() {
            @Override
            public void run() {
                getCallBack().problemLoadingBills(e);
            }
        });

    }

    private void deliverBillsOnMainThread(final List<Bill> list) {
        getMainThread().post(new Runnable() {
            @Override
            public void run() {
                getCallBack().onLoadBills(list);
            }
        });
    }

    private CallBack getCallBack() {
        return callBack;
    }

    private void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    private BillRepository getBillRepository() {
        return billRepository;
    }

    private void setBillRepository(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    @Override
    public void onFinished() {
        super.onFinished();
    }

    public interface CallBack {

        void onLoadBills(List<Bill> bills);

        void problemLoadingBills(Exception e);

        void connectionProblemLoadingBills(ConnectionProblemException e);
    }
}


