package control;

import entity.Ga;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IDAOGa extends Remote {
    //Get danh sách ga
    ArrayList<Ga> getDsGa() throws RemoteException;

    // Get Ga theo mã ga
    Ga getGaTheoMaGa(int maGa) throws RemoteException;

    // them ga
    boolean themGa(Ga ga) throws RemoteException;

    // sua ga tra ve doi tuong ga
    Ga suaGa(Ga ga) throws RemoteException;
}
