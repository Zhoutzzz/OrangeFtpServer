package com.orange.ftpserver.context;

public final class DefaultFtpSession implements FtpSession {
	
	private String sessionId;
	private FtpRequest ftpRequest;
	private FtpResponse ftpResponse;
	
	public DefaultFtpSession(){
		ftpRequest = new DefaultFtpRequest(this);
		ftpResponse = new DefaultFtpResponse(this);
	}
	
	public void setSessionId(String sessionId){
		this.sessionId = sessionId;
	}
	
	@Override
	public String getSessionId() {
		return sessionId;
	}

	@Override
	public FtpResponse getResponse() {
		return ftpResponse;
	}

	@Override
	public boolean isLoggedIn() {
		return false;
	}

	@Override
	public boolean isSecure() {
		return false;
	}

	@Override
	public FtpRequest getRequest() {
		return ftpRequest;
	}
}
