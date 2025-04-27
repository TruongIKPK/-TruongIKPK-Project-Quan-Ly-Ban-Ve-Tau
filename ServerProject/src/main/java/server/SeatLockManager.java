package server;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;

public class SeatLockManager {
    private static final ConcurrentHashMap<String, Lock> seatLocks = new ConcurrentHashMap<>();
    private static final int LOCK_TIMEOUT_SECONDS = 300; // 5 phút

    @Data
    @AllArgsConstructor
    private static class Lock {
        private String userId;
        private LocalDateTime lockTime;
    }

    //version1
    private String createLockKey (String trainId, String seatId){
        return trainId + "_" + seatId;
    }

    public synchronized boolean lockSeat(String trainId, String seatId, String userId) {
        String lockKey = createLockKey(trainId, seatId);
        Lock existingLock = seatLocks.get(lockKey);
        // Nếu ghế đã bị khóa
        if (existingLock != null) {
            // Kiểm tra xem khóa có hết hạn chưa
            if (existingLock.lockTime.plusSeconds(LOCK_TIMEOUT_SECONDS).isBefore(LocalDateTime.now())) {
                // Nếu hết hạn, xóa khóa cũ
                seatLocks.remove(lockKey);
            } else if (existingLock.userId.equals(userId)) {
                // Nếu cùng user thì cho phép
                return true;
            } else {
                // Nếu khóa còn hạn và khác user thì không cho phép
                return false;
            }
        }
        // Tạo khóa mới
        seatLocks.put(lockKey, new Lock(userId, LocalDateTime.now()));
        return true;
    }

    public synchronized void unlockSeat(String trainId, String seatId, String userId) {
        String lockKey = createLockKey(trainId, seatId);
        Lock lock = seatLocks.get(lockKey);
        if (lock != null && lock.userId.equals(userId)) {
            seatLocks.remove(lockKey);
        }
    }

    // Thêm phương thức để debug
    public void printLocks() {
        System.out.println("Current locks:");
        seatLocks.forEach((key, lock) -> {
            System.out.println("Key: " + key + ", User: " + lock.userId + ", Time: " + lock.lockTime);
        });
    }

    public synchronized boolean isLockedByUser(String trainId, String seatId, String userId) {
        String lockKey = createLockKey(trainId, seatId);
        Lock lock = seatLocks.get(lockKey);
        if (lock == null) {
            return false;
        }

        if (lock.getLockTime().plusSeconds(LOCK_TIMEOUT_SECONDS)
                .isBefore(LocalDateTime.now())) {
            seatLocks.remove(lockKey);
            return false;
        }

        return lock.getUserId().equals(userId);
    }

    public void cleanupExpiredLocks() {
        LocalDateTime now = LocalDateTime.now();
        seatLocks.entrySet().removeIf(entry ->
                entry.getValue().getLockTime().plusSeconds(LOCK_TIMEOUT_SECONDS).isBefore(now)
        );
    }
}