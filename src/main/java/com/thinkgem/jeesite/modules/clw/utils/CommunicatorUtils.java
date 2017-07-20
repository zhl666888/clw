package com.thinkgem.jeesite.modules.clw.utils;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.qq.tars.client.CommunicatorConfig;
import com.qq.tars.client.CommunicatorFactory;
import com.qq.tars.client.Communicator;
public class CommunicatorUtils {
	/**
	 * 通讯器创建
	 * @return
	 */
	public static  Communicator connCommunicator(){
    	CommunicatorConfig cfg;
    	Communicator communicator = null;
	 ClassLoader classLoader = CommunicatorUtils.class.getClassLoader();
		try {
			cfg = CommunicatorConfig.load(classLoader.getResource("").getPath()+("tars.conf"));
			 communicator	 = CommunicatorFactory.getInstance().getCommunicator(cfg);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return communicator;
}
}
