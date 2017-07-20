package com.thinkgem.jeesite.modules.clw.web;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.qq.tars.client.Communicator;
import com.qq.tars.client.CommunicatorConfig;
import com.qq.tars.client.CommunicatorFactory;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.clw.entity.carlistapp.CarListPrx;
import com.thinkgem.jeesite.modules.clw.entity.carposition.CarPositionPrx;
import com.thinkgem.jeesite.modules.clw.entity.clapp.Car;
import com.thinkgem.jeesite.modules.clw.entity.clapp.ClPrx;
import com.thinkgem.jeesite.modules.clw.entity.coordinateapp.CoordinatePrx;
import com.thinkgem.jeesite.modules.clw.utils.CommunicatorUtils;
/**
 * 车辆位置Controller
 * @author zhl
 * @version 2017-07-04
 */
@Controller
@RequestMapping(value = "${adminPath}/clw/carMonitor")
public class ClwCarMonitor extends BaseController{
	public String carJson;
	public String carPositionJson;
	public static Communicator communicator;

	@RequestMapping(value = {"carmonitor"})
	public String carmonitor(Model model) {
		return "modules/clw/carMonitor";
	}
	
	@ResponseBody
	@RequestMapping(value = {"selectCar"})
	public List<Car> selectCar(Model model) {
	     /*HelloPrx proxy = communicator.stringToProxy(HelloPrx.class,"zhl.TestServer.TestObj@tcp -h 10.44.26.84  -t 60000 -p 18809");
	     String ret = proxy.hello(1000, "Hello World");*/
	        //System.out.println(ret);
	
			 ClPrx cp=communicator.stringToProxy(ClPrx.class,"Zysapp.WlServer.ClObj@tcp -h 10.44.26.81 -t 60000 -p 18955");
			 List<Car> carList =new ArrayList<Car>();
		     List<Car> lc=cp.cl("",carList);
		     if(lc.size()>0){
		    	 model.addAttribute("lc",lc);
		     }
			return lc;
	}
	@ResponseBody
	@RequestMapping(value = {"positionForCar"})
	public List<String> positionForCar(Model model,String checkedid) {
		//if(StringUtils.isNotBlank(checkedid)){
		CommunicatorConfig cfg;
		try {
			 ClassLoader classLoader = this.getClass().getClassLoader();
			cfg = CommunicatorConfig.load(classLoader.getResource("").getPath()+("tars.conf"));
			Communicator communicator = CommunicatorFactory.getInstance().getCommunicator(cfg);
			
	        CoordinatePrx cp=communicator.stringToProxy(CoordinatePrx.class,"Zysapp.CoordinateServer.CoordinateObj@tcp -h 10.44.26.83 -t 60000 -p 19004");
	        List<String> list =new ArrayList<String>();
		     List<String> lc=cp.coordinate("DPTEST", checkedid, list);
		     if(lc.size()>0){
		    	 
		     }
		     return lc;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	@ResponseBody
	@RequestMapping(value = {"carList"})
	public String carList(Model model) {
		if("".equals(communicator)||null==communicator){
		CommunicatorConfig cfg;
		ClassLoader classLoader = this.getClass().getClassLoader();
		try {
			System.out.println(classLoader.getResource("").getPath());
			cfg = CommunicatorConfig.load(classLoader.getResource("").getPath()+("tars.conf"));
			 communicator	 = CommunicatorFactory.getInstance().getCommunicator(cfg);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		}
		 CarListPrx cp=communicator.stringToProxy(CarListPrx.class,"zhl.CarListServer.carListObj@tcp -h 10.44.26.84 -t 60000 -p 18816");
		 carJson=cp.getCarList("");
		return carJson;
		
	}
	@ResponseBody
	@RequestMapping(value = {"carPosition"})
	public String carPosition(Model model,String checkedid) {
		if("".equals(communicator)||null==communicator){
			CommunicatorConfig cfg;
			ClassLoader classLoader = this.getClass().getClassLoader();
			try {
				cfg = CommunicatorConfig.load(classLoader.getResource("").getPath()+("tars.conf"));
				 communicator	 = CommunicatorFactory.getInstance().getCommunicator(cfg);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			}
			CarPositionPrx cp=communicator.stringToProxy(CarPositionPrx.class,"zhl.CarPositionServer.carPositionObj@tcp -h 10.44.26.84 -t 60000 -p 18818");
			 carPositionJson=cp.getCarPosition("DPTEST", checkedid);
			return carPositionJson;
	}
}
