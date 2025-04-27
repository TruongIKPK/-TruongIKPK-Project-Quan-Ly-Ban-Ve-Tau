package server;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public final class SessionManager {
    private static final ConcurrentHashMap<String, SessionInfo> activeSessions = new ConcurrentHashMap<>();
    private static final long SESSION_TIMEOUT = TimeUnit.MINUTES.toMillis(5);

    public static class SessionInfo {
        private final UUID sessionId;
        private volatile long lastActivityTime;
        private final String username;

        public SessionInfo(String username, UUID sessionId) {
            this.username = username;
            this.sessionId = sessionId;
            this.lastActivityTime = System.currentTimeMillis();
        }

        public void updateActivity() {
            this.lastActivityTime = System.currentTimeMillis();
        }

        public boolean isExpired() {
            return (System.currentTimeMillis() - lastActivityTime) > SESSION_TIMEOUT;
        }
    }

    public static synchronized boolean login(String username, UUID sessionId) {
        // Kiểm tra nếu tài khoản đã đăng nhập
        if (activeSessions.containsKey(username)) {
            SessionInfo existingSession = activeSessions.get(username);
            if (!existingSession.isExpired()) {
                return false; // Tài khoản đang đăng nhập ở nơi khác
            }
            // Nếu session hết hạn thì xóa đi
            activeSessions.remove(username);
        }

        // Tạo session mới
        activeSessions.put(username, new SessionInfo(username, sessionId));
        return true;
    }

    public static synchronized void logout(String username) {
        SessionInfo removed = activeSessions.remove(username);
        if (removed != null) {
            System.out.println("Đã logout: " + username);
        }
    }

    public static synchronized void cleanup() {
        activeSessions.entrySet().removeIf(entry -> {
            boolean expired = entry.getValue().isExpired();
            if (expired) {
                System.out.println("Session hết hạn: " + entry.getKey());
            }
            return expired;
        });
    }

    public static synchronized boolean ping(String username, UUID sessionId) {
        SessionInfo session = activeSessions.get(username);
        if (session != null && session.sessionId.equals(sessionId)) {
            session.updateActivity();
            return true;
        }
        return false;
    }
}
