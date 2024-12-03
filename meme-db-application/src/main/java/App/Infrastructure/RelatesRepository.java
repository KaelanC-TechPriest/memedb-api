package App.Infrastructure;

import App.Domain.Relates;
import App.Domain.Tag;
import App.Domain.Ad;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class RelatesRepository extends BaseRepository<Relates>{

    public RelatesRepository(JdbcTemplate databaseConnection){
        super(databaseConnection);
    }

    @Override
    public Relates get(String name) throws Exception {
        String sql = "SELECT * FROM RELATES_TO WHERE Tag_id = '" + name +"';";
        List<Relates> descriptions = this.getDatabaseConnection().query(sql, BeanPropertyRowMapper.newInstance(Relates.class));
        if (descriptions.size() >= 1) {
            return descriptions.getFirst();
        }
        throw new Exception();
    }

    public Relates get(Relates relation) throws Exception {
        String sql = "SELECT * FROM RELATES_TO WHERE Tag_id = '" + relation.getTagId() + "' AND Ad_id = " + relation.getAdId() + ";";
        List<Relates> descriptions = this.getDatabaseConnection().query(sql, BeanPropertyRowMapper.newInstance(Relates.class));
        if (descriptions.size() == 1) {
            return descriptions.getFirst();
        }
        throw new Exception();
    }

    public List<Tag> getTags(String id) throws Exception {
        String sql = "SELECT DISTINCT t.* FROM TAG t INNER JOIN RELATES_TO r ON t.Name = r.Tag_id WHERE r.Ad_id = '" + id +"';";
        List<Tag> tags = this.getDatabaseConnection().query(sql, BeanPropertyRowMapper.newInstance(Tag.class));
        if (tags.size() >= 1) {
            return tags;
        }
        throw new Exception();
    }

    public List<Ad> getAds(String name) throws Exception {
        String sql = "SELECT DISTINCT a.* FROM AD a INNER JOIN RELATES_TO r ON a.Number = r.Ad_id WHERE r.Tag_id = '" + name +"';";
        List<Ad> ads = this.getDatabaseConnection().query(sql, BeanPropertyRowMapper.newInstance(Ad.class));
        if (ads.size() >= 1) {
            return ads;
        }
        throw new Exception();
    }

    @Override
    public List<Relates> get() {
        String sql = "SELECT * FROM RELATES_TO;";
        return this.getDatabaseConnection().query(sql, BeanPropertyRowMapper.newInstance(Relates.class));
    }

    @Override
    public void create(Relates relation) {
        String sql = "INSERT INTO RELATES_TO VALUES (" + 
             relation.getAdId() + ", '" + 
            relation.getTagId() + "');";
        this.getDatabaseConnection().execute(sql);
    }

    @Override
    public void delete(String name) {} // just to get rid of error. Both tagId and meme_id are part of the primary key for a relationship, so it needs both to be uniquely identified

    public void delete(Relates relation) {
        String sql = "DELETE FROM RELATES_TO WHERE Tag_id = '" + relation.getTagId() + 
            "' AND Ad_id = " + relation.getAdId() + ";";
        this.getDatabaseConnection().execute(sql);
    }

    @Override
    public void update(String name, Relates relation) {
        String sql = "UPDATE RELATES_TO SET Tag_id = '" + name + 
            "' WHERE Tag_id = '" + relation.getTagId() + 
            "' AND Ad_id = " + relation.getAdId() + ";";
        this.getDatabaseConnection().execute(sql);

    }

    public void update(int id, Relates relation) {
        String sql = "UPDATE RELATES_TO SET Ad_id = " + id + 
            " WHERE Tag_id = '" + relation.getTagId() + 
            "' AND Ad_id = " + relation.getAdId() + ";";
        this.getDatabaseConnection().execute(sql);
 
    }
}

