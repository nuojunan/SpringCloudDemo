package com.bjnja.netty.service;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class ServerHandler extends ChannelHandlerAdapter {

	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		super.channelRegistered(ctx);
		System.out.println("ServerHandler -> channelRegistered");
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		super.channelActive(ctx);
		System.out.println("ServerHandler -> channelActive");
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		super.channelInactive(ctx);
		System.out.println("ServerHandler -> channelInactive");
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		super.channelRead(ctx, msg);
		System.out.println("ServerHandler -> channelRead");
		if (msg instanceof MethodInvokeMeta) {
			System.out.println("是方法调用===========");
			RequestDispatcher.dispatcher(ctx, (MethodInvokeMeta) msg);
			return;
		}
		ctx.writeAndFlush("bye!!!!!!!!!!!!!!!");
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		super.channelReadComplete(ctx);
		System.out.println("ServerHandler -> channelReadComplete");
	}

}
