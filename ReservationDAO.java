package DAO;

import Modele.Reservation;
import java.util.List;


public interface ReservationDAO {

    void create(Reservation reservation);

    Reservation findById(int ID_reservation);

    List<Reservation> findAll();

    void update(Reservation reservation);

    void delete(int ID_reservation);

    List<Reservation> findByUserId(int userID);
}
