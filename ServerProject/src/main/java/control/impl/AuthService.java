package control.impl;

import control.IAuthService;
import server.SessionManager;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AuthService extends UnicastRemoteObject implements IAuthService {
    private final ScheduledExecutorService scheduler;
    private final DAOTaiKhoan accountDao;

    public AuthService() throws RemoteException {
        this.accountDao = new DAOTaiKhoan();
        this.scheduler = Executors.newSingleThreadScheduledExecutor();
        this.scheduler.scheduleAtFixedRate(
                SessionManager::cleanup,
                0,
                1,
                TimeUnit.MINUTES
        );
    }

    @Override
    public synchronized boolean login(String username, String password, UUID sessionId) throws RemoteException {
        // 1. Kiểm tra thông tin đăng nhập
        if (accountDao.login(username, password) == null) {
            return false;
        }

        // 2. Kiểm tra session hiện tại
        return SessionManager.login(username, sessionId);
    }

    @Override
    public synchronized void logout(String username) throws RemoteException {
        SessionManager.logout(username);
    }

    @Override
    public synchronized boolean ping(String username, UUID sessionId) throws RemoteException {
        return SessionManager.ping(username, sessionId);
    }
}
