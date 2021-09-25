package com.yuebaix.pangu.core.concept;

public interface Interceptable {
    void pre(Context ctx);
    void action(Context ctx);
    void post(Context ctx);
}
