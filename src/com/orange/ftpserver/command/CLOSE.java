package com.orange.ftpserver.command;

import com.orange.ftpserver.context.AbstractFtpCommand;
import com.orange.ftpserver.context.FtpRequestCommand;
import com.orange.ftpserver.context.IFtpSession;
import com.orange.ftpserver.exception.FtpCommandException;

public final class CLOSE extends AbstractFtpCommand{

	public CLOSE(IFtpSession ftpSession, String[] parameters) {
		super(ftpSession, FtpRequestCommand.CLOSE, parameters);
	}

	@Override
	public void exec() throws FtpCommandException{
		super.executCommand();
	}
}