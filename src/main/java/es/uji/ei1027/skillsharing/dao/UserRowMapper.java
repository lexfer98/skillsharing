package es.uji.ei1027.skillsharing.dao;

import es.uji.ei1027.skillsharing.model.UserDetails;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class UserRowMapper implements RowMapper<UserDetails> {
    @Override
    public UserDetails mapRow(ResultSet rs, int i) throws SQLException {
        UserDetails user = new UserDetails();
        user.setUsername(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        try {
            user.setDni(rs.getString("dni"));
        }catch (Exception e){
            try{
                user.setDni(rs.getString("dni"));
            }catch (Exception ex) {
                try {
                    user.setDni(rs.getString("dni"));
                } catch (Exception ex1) {
                    user.setDni(rs.getString("dni"));
                }
            }
        }
        return user;
    }
}
