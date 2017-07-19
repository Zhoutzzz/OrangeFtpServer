package com.orange.ftpserver.context;

import java.util.Map;

import com.orange.ftpserver.server.AbstractFtpServerListener;
import com.orange.ftpserver.user.IUserManager;

public interface IFtpContext {
	IUserManager getUserManager();
	Map<String,AbstractFtpServerListener> getListenerMap();
}
