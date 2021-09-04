package com.yuebaix.pangu.web.base;

import java.util.List;

public interface BaseMapStruct<O, I> {
    I toInner(O outer);
    O toOuter(I inner);
    List<I> toInner(List<O> outerList);
    List<O> toOuter(List<I> innerList);
}
