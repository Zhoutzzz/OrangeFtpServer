package com.orange.ftpserver.server;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

import com.orange.ftpserver.context.IFtpContext;
import com.orange.ftpserver.handler.FtpServerHandler;

public abstract class AbstractFtpServer implements IFtpServer{
	
	private ExecutorService bossGroup;
	private ExecutorService workerGroup;
	private int serverPort;
	private IFtpContext ftpContext;
	
	public AbstractFtpServer(IFtpContext ftpContext){
		this.ftpContext = ftpContext;
		serverPort = 21;
	}
	
	public AbstractFtpServer(IFtpContext ftpContext,int serverPort){
		this.ftpContext = ftpContext;
		this.serverPort = serverPort;
	}
	
	protected abstract void addSSL(ChannelPipeline pipeline);
	
	@Override
	public void start() {
		ServerBootstrap bootstrap = new ServerBootstrap(); 
		bossGroup = Executors.newCachedThreadPool();  
		workerGroup = Executors.newCachedThreadPool(); 
        bootstrap.setFactory(new NioServerSocketChannelFactory(bossGroup, workerGroup));  
        // ���ùܵ��Ĺ���  
        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {  
            @Override  
            public ChannelPipeline getPipeline() throws Exception {  
                ChannelPipeline pipeline = Channels.pipeline();  
                pipeline.addLast("stringDecoder", new StringDecoder());
                pipeline.addLast("stringEncoder", new StringEncoder());
                pipeline.addLast("ftpServerHandler", new FtpServerHandler(ftpContext));  
                return pipeline;  
            }  
        });
        addSSL(bootstrap.getPipeline());
        bootstrap.bind(new InetSocketAddress(serverPort));  
	}

	@Override
	public void close() {
		if(workerGroup != null)
			workerGroup.shutdownNow();
		if(bossGroup != null)
			bossGroup.shutdownNow();
	}
	
	public void setServerPort(int serverPort){
		this.serverPort = serverPort;
	}

	@Override
	public int getServerPort() {
		return this.serverPort;
	}
}