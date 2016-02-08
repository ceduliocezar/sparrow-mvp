package com.cedulio.mvp;

import android.content.Context;


public interface MVPView {
    void showError(String message);

    Context getContext();
}
