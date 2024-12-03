package App.Infrastructure;
import App.Domain.Ad; import org.springframework.jdbc.core.BeanPropertyRowMapper; import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class AdRepository extends BaseRepository<Ad>{

    public AdRepository(JdbcTemplate databaseConnection){
        super(databaseConnection);
    }

    @Override
    public Ad get(String id) throws Exception {
        String sql = "SELECT * FROM AD WHERE [Number] = " + id + ";";
        List<Ad> ads = this.getDatabaseConnection().query(sql, BeanPropertyRowMapper.newInstance(Ad.class));
        if (ads.size() == 1) {
            return ads.getFirst();
        }
        throw new Exception();
    }

    @Override
    public List<Ad> get() {
        String sql = "SELECT * FROM AD;";
        return this.getDatabaseConnection().query(sql, BeanPropertyRowMapper.newInstance(Ad.class));
    }

    @Override
    public void create(Ad ad) {
        String sql = "SELECT MAX(Number)+1 FROM AD;";
        Integer nextNumber = this.getDatabaseConnection().queryForObject(sql, Integer.class);
        sql = "INSERT INTO AD VALUES (" + 
            nextNumber + ", '" + 
            ad.getPublisher() + "', '" + 
            ad.getUrl()+ "', '" + 
            ad.getDatePublished() + "');";
        this.getDatabaseConnection().execute(sql);
    }

    @Override
    public void delete(String id) {
        String sql = "DELETE FROM AD WHERE [Number] = " + id + ";";
        this.getDatabaseConnection().execute(sql);
    }

    @Override
    public void update(String number, Ad ad) {
        String sql = "UPDATE AD SET Publisher = '" + ad.getPublisher() + 
            "', [Url] = '" + ad.getUrl() + 
            "', Date_published = '" + ad.getDatePublished() +
            "' WHERE [Number] = " + number + ";";
        this.getDatabaseConnection().execute(sql);
    }
}

