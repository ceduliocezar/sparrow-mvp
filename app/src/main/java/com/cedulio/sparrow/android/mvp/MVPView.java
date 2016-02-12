package com.cedulio.sparrow.android.mvp;

import android.content.Context;


public interface MVPView {
    void showError(String message);

    Context getContext();
}
