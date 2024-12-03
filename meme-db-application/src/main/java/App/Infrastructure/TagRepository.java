package App.Infrastructure;

import App.Domain.Tag;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class TagRepository extends BaseRepository<Tag>{

    public TagRepository(JdbcTemplate databaseConnection){
        super(databaseConnection);
    }

    @Override
    public Tag get(String name) throws Exception {
        String sql = "SELECT * FROM TAG WHERE [Name] = '" + name + "';";
        List<Tag> tags = this.getDatabaseConnection().query(sql, BeanPropertyRowMapper.newInstance(Tag.class));
        if (tags.size() == 1) {
            return tags.getFirst();
        }
        throw new Exception();
    }

    @Override
    public List<Tag> get() {
        String sql = "SELECT * FROM TAG ORDER BY Times_used DESC;";
        return this.getDatabaseConnection().query(sql, BeanPropertyRowMapper.newInstance(Tag.class));
    }

    @Override
    public void create(Tag tag) {
        String sql = "INSERT INTO TAG VALUES ('" + tag.getName() + "', " + tag.getTimesUsed() + ");";
        this.getDatabaseConnection().execute(sql);
    }

    @Override
    public void delete(String name) {
        String sql = "DELETE FROM TAG WHERE [Name] = '" + name + "';";
        this.getDatabaseConnection().execute(sql);
    }

    @Override
    public void update(String name, Tag tag) {
        String sql = "UPDATE TAG SET Times_used = " + tag.getTimesUsed() + " WHERE [Name] = '" + name + "';";
        this.getDatabaseConnection().execute(sql);
    }
}

