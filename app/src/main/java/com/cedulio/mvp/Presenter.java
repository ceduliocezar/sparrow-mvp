package com.cedulio.mvp;

import android.os.Bundle;

public abstract class Presenter {


    public abstract void onCreateView(Bundle savedInstanceState);

    public abstract void onResumeView();

    public abstract void onPauseView();

    public abstract void onSaveInstanceState(Bundle outState);
}
