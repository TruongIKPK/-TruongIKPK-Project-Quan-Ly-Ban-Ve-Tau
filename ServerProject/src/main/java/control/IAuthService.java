package control;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.UUID;

public interface IAuthService extends Remote, Serializable {
    boolean login(String username, String password, UUID sessionId) throws RemoteException;

    void logout(String username) throws RemoteException;

    boolean ping(String username, UUID sessionId) throws RemoteException;
}
