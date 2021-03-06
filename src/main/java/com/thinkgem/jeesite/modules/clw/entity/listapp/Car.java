// **********************************************************************
// This file was generated by a TARS parser!
// TARS version 1.0.1.
// **********************************************************************

package com.thinkgem.jeesite.modules.clw.entity.listapp;

import com.qq.tars.protocol.util.*;
import com.qq.tars.protocol.tars.*;
import com.qq.tars.protocol.tars.annotation.*;

@TarsStruct
public class Car {

	@TarsStructProperty(order = 0, isRequire = true)
	public int cid = 0;
	@TarsStructProperty(order = 1, isRequire = true)
	public String carno = "";

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getCarno() {
		return carno;
	}

	public void setCarno(String carno) {
		this.carno = carno;
	}

	public Car() {
	}

	public Car(int cid, String carno) {
		this.cid = cid;
		this.carno = carno;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + TarsUtil.hashCode(cid);
		result = prime * result + TarsUtil.hashCode(carno);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Car)) {
			return false;
		}
		Car other = (Car) obj;
		return (
			TarsUtil.equals(cid, other.cid) &&
			TarsUtil.equals(carno, other.carno) 
		);
	}

	public void writeTo(TarsOutputStream _os) {
		_os.write(cid, 0);
		if (null != carno) {
			_os.write(carno, 1);
		}
	}

	public void readFrom(TarsInputStream _is) {
		this.cid = _is.read(cid, 0, true);
		this.carno = _is.read(carno, 1, true);
	}

}
