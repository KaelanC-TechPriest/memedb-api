package App.Infrastructure;

import App.Domain.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserRepository extends BaseRepository<User>{

    public UserRepository(JdbcTemplate databaseConnection){
        super(databaseConnection);
    }

    @Override
    public User get(String id) throws Exception {
        String sql = "SELECT * FROM [USER] WHERE User_id = " + id +";";
        List<User> users = this.getDatabaseConnection().query(sql, BeanPropertyRowMapper.newInstance(User.class));
        if (users.size() >= 1) {
            return users.getFirst();
        }
        throw new Exception();
    }

    @Override
    public List<User> get() {
        String sql = "SELECT * FROM [USER];";
        return this.getDatabaseConnection().query(sql, BeanPropertyRowMapper.newInstance(User.class));
    }

    @Override
    public void create(User user) {
        String sql = "SELECT MAX(User_id)+1 FROM [USER];";
        Integer newUserId = this.getDatabaseConnection().queryForObject(sql, Integer.class);
        sql = "INSERT INTO [USER] VALUES (" + 
            newUserId + ", '" + 
            user.getUsername() + "', '" + 
            user.getEmail() + "', '" + 
            user.getPassword() + "', '" +
            user.getJoinDate() + "', " + "0 );";
        this.getDatabaseConnection().execute(sql);
    }

    @Override
    public void delete(String id) {
        String sql = "DELETE FROM [USER] WHERE User_id = " + id + ";";
        this.getDatabaseConnection().execute(sql);
    }

    @Override
    public void update(String id, User user) {
        String sql = "UPDATE [USER] SET Username = '" + user.getUsername() + 
            "', Email = '" + user.getEmail() + 
            "', Password = '" + user.getPassword() + 
            "' WHERE User_id = " + id + ";";
        this.getDatabaseConnection().execute(sql);
    }
}

