// **********************************************************************
// This file was generated by a TARS parser!
// TARS version 1.0.1.
// **********************************************************************

package com.thinkgem.jeesite.modules.clw.entity.carlistapp;

import com.qq.tars.protocol.annotation.*;
import com.qq.tars.protocol.tars.annotation.*;
import com.qq.tars.common.support.Holder;

@Servant
public interface CarListPrx {

	public String getCarList(String id);

	public String getCarList(String id, @TarsContext java.util.Map<String, String> ctx);

	public void async_getCarList(@TarsCallback CarListPrxCallback callback, String id);

	public void async_getCarList(@TarsCallback CarListPrxCallback callback, String id, @TarsContext java.util.Map<String, String> ctx);
}
