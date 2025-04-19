package DAO;

import Modele.Users;
import java.util.List;

public interface UsersDAO {
    Users findById(int ID);

    List<Users> findAll();

    void create(Users user);

    void update(Users user);

    void delete(int ID);

    // Ex: Rechercher un user par email
    Users findByEmail(String email);
}
