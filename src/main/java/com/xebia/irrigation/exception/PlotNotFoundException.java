package com.xebia.irrigation.exception;

import java.util.function.Supplier;

public class PlotNotFoundException  extends RuntimeException {
    String msg;

   public PlotNotFoundException(String msg) {
        this.msg=msg;
    }


    }


