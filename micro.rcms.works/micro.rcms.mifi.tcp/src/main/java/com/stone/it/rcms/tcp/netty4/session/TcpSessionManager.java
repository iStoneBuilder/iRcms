package com.stone.it.rcms.tcp.netty4.session;

import com.stone.it.rcms.tcp.netty4.service.DeviceStateManager;
import com.stone.it.rcms.tcp.netty4.utils.SpringContextUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;

/**
 *
 * @author cj.stone
 * @Date 2025/1/9
 * @Desc
 */
@Component
public class TcpSessionManager {

    private static final Logger logger = LoggerFactory.getLogger(TcpSessionManager.class);

    private static volatile TcpSessionManager instance = null;
    private final Map<String, TcpSession> sessionIdMap = new ConcurrentHashMap<>();
    private final Map<String, String> snMap = new ConcurrentHashMap<>();

    public static TcpSessionManager getInstance() {
        if (instance == null) {
            Class<TcpSessionManager> var0 = TcpSessionManager.class;
            synchronized (TcpSessionManager.class) {
                if (instance == null) {
                    instance = new TcpSessionManager();
                }
            }
        }
        return instance;
    }

    public void addTcpSession(TcpSession session) {
        this.put(session.getId(), session);
    }

    public boolean containsKey(String sessionId) {
        return this.sessionIdMap.containsKey(sessionId);
    }

    public boolean containsSession(TcpSession session) {
        return this.sessionIdMap.containsValue(session);
    }

    public TcpSession findBySessionId(String id) {
        return this.sessionIdMap.get(id);
    }

    public TcpSession findByTerminalSn(String sn) {
        String sessionId = this.snMap.get(sn);
        return sessionId == null ? null : this.findBySessionId(sessionId);
    }

    public synchronized TcpSession put(String key, TcpSession session) {
        if (StringUtils.isNotBlank(session.getSn())) {
            this.snMap.put(session.getSn(), session.getId());
        }
        return this.sessionIdMap.put(key, session);
    }

    public synchronized TcpSession removeBySessionId(String sessionId) {
        if (sessionId == null) {
            return null;
        } else {
            TcpSession session = this.sessionIdMap.remove(sessionId);
            if (session == null) {
                return null;
            } else {
                String sn = session.getSn();
                if (sn != null) {
                    this.snMap.remove(sn);
                    this.offline(sn);
                }

                return session;
            }
        }
    }

    private void offline(String sn) {
        DeviceStateManager deviceStateManager = SpringContextUtils.getBean(DeviceStateManager.class);
        deviceStateManager.remove(sn);
    }

    public Set<String> keySet() {
        return this.sessionIdMap.keySet();
    }

    public void forEach(BiConsumer<? super String, ? super TcpSession> action) {
        this.sessionIdMap.forEach(action);
    }

    public Set<Map.Entry<String, TcpSession>> entrySet() {
        return this.sessionIdMap.entrySet();
    }

    public List<TcpSession> toList() {
        return new ArrayList<>(this.sessionIdMap.values());
    }
}
