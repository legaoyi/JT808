package com.legaoyi.storer.handler;

/**
 * @author <a href="mailto:shengbo.gao@gmail.com;gaoshengbo@legaoyi.com">gaoshengbo</a>
 * @version 1.0.0
 * @since 2019-08-18
 */
public interface MQMessageHandler {

	public void handle(String message) throws Exception;

}
