package com.stone.it.rcms.tcp.netty4.session;

import io.netty.channel.Channel;
import lombok.Data;

/**
 *
 * @author cj.stone
 * @Date 2025/1/9
 * @Desc
 */
@Data
public class TcpSession {
    private Channel channel = null;
    private int currentFlowId = 0;
    private long lastCommunicateTimeStamp = 0L;
    private String id;
    private String sn;

    public static String buildId(Channel channel) {
        return channel.id().asLongText();
    }

    public static TcpSession buildSession(Channel channel, String sn) {
        TcpSession session = new TcpSession();
        session.setChannel(channel);
        session.setId(buildId(channel));
        session.setSn(sn);
        session.setLastCommunicateTimeStamp(System.currentTimeMillis());
        return session;
    }

}
