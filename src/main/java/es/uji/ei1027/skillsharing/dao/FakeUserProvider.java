package es.uji.ei1027.skillsharing.dao;

import es.uji.ei1027.skillsharing.model.UserDetails;
import org.jasypt.util.password.BasicPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.*;

@Repository
public class FakeUserProvider implements UserDao {
    final Map<String, UserDetails> knownUsers = new HashMap<String, UserDetails>();

    private JdbcTemplate jdbcTemplate;
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<UserDetails> getUsers() {
        try {
            return jdbcTemplate.query(
                    "SELECT * FROM UserDetails",
                    new UserRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<UserDetails>();
        }
    }


    @Override
    public UserDetails loadUserByUsername(String username, String password) {
        if (username.equals("Admin") && password.equals("Admin")) {
            UserDetails user = new UserDetails();
            user.setUsername(username);
            user.setPassword(password);
            user.setTipo("Admin");
            user.setCode(5);
            return user;
        }
        String tipo = "alumno";
        int code = 1;
        List userList = jdbcTemplate.query("SELECT email, password,dni FROM alumno WHERE email=?", new UserRowMapper(), username);
        if (userList.isEmpty()) {
            return null; // Usuari no trobat
        }


        //List userList = jdbcTemplate.query("SELECT username, password, dni, tipo FROM Userdetails WHERE username=?", new UserRowMapper(), username);
        UserDetails user = (UserDetails) userList.get(0);
        user.setTipo(tipo);
        user.setCode(code);
        // Contrasenya
        BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
        if (passwordEncryptor.checkPassword(password, user.getPassword())) {
            // Es deuria esborrar de manera segura el camp password abans de tornar-lo
            return user;
        }
        else {
            return null; // bad login!
        }
    }





    @Override
    public Collection<UserDetails> listAllUsers() {
        return knownUsers.values();
    }
}