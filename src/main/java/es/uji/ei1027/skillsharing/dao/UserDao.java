package es.uji.ei1027.skillsharing.dao;

import es.uji.ei1027.skillsharing.model.UserDetails;

import java.util.Collection;

public interface UserDao {
    UserDetails loadUserByUsername(String username, String password);
    Collection<UserDetails> listAllUsers();
}
