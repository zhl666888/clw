// **********************************************************************
// This file was generated by a TARS parser!
// TARS version 1.0.1.
// **********************************************************************

package com.thinkgem.jeesite.modules.clw.entity.carposition;

import com.qq.tars.protocol.annotation.*;
import com.qq.tars.protocol.tars.annotation.*;
import com.qq.tars.common.support.Holder;

@Servant
public interface CarPositionPrx {

	public String getCarPosition(String syskey, String carids);

	public String getCarPosition(String syskey, String carids, @TarsContext java.util.Map<String, String> ctx);

	public void async_getCarPosition(@TarsCallback CarPositionPrxCallback callback, String syskey, String carids);

	public void async_getCarPosition(@TarsCallback CarPositionPrxCallback callback, String syskey, String carids, @TarsContext java.util.Map<String, String> ctx);
}
