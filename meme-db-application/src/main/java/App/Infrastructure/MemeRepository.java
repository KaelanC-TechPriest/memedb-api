package App.Infrastructure;

import App.Domain.Meme;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class MemeRepository extends BaseRepository<Meme>{

    public MemeRepository(JdbcTemplate databaseConnection){
        super(databaseConnection);
    }

    // helper function
    private void updateNumPosts(int userId, int change) {
        String sql = "SELECT COUNT(*) FROM MEME WHERE Creator = " + userId + ";";
        Integer count = this.getDatabaseConnection().queryForObject(sql, Integer.class);
        count += change;
        sql = "UPDATE [USER] SET Num_posts = " + count + " WHERE User_id = " + userId + ";";
        this.getDatabaseConnection().execute(sql);
    }
    
    @Override
    public Meme get(String id) throws Exception {
        String sql = "SELECT * FROM MEME WHERE Meme_id = " + id + ";";
        List<Meme> memes = this.getDatabaseConnection().query(sql, BeanPropertyRowMapper.newInstance(Meme.class));
        if (memes.size() == 1) {
            return memes.getFirst();
        }
        throw new Exception();
    }

    @Override
    public List<Meme> get() {
        String sql = "SELECT * FROM MEME ORDER BY Likes DESC;";
        return this.getDatabaseConnection().query(sql, BeanPropertyRowMapper.newInstance(Meme.class));
    }

    @Override
    public void create(Meme meme) {
        String sql = "SELECT MAX(Meme_id)+1 FROM MEME;";
        Integer nextNumber = this.getDatabaseConnection().queryForObject(sql, Integer.class);
        sql = "INSERT INTO MEME VALUES (" +
            nextNumber + ", " +
            meme.getSize() + ", " +
            meme.getTimesReported() + ", '" +
            meme.getUploadDate() + "', " +
            meme.isPrivate() + ", " +
            meme.getViews() + ", " +
            meme.getLikes() + ", " +
            meme.getDislikes() + ", " +
            meme.getCreatorId() + ");";
        this.getDatabaseConnection().execute(sql);
        updateNumPosts(meme.getCreatorId(), 1);
    }

    @Override
    public void delete(String id) {
        String sql = "SELECT Creator FROM MEME WHERE Meme_id = " + id + ";";
        Integer creator = this.getDatabaseConnection().queryForObject(sql, Integer.class);
        sql = "DELETE FROM MEME WHERE Meme_id = " + id + ";";
        this.getDatabaseConnection().execute(sql);
        updateNumPosts(creator, -1);
    }

    @Override
    public void update(String id, Meme meme) {
        String sql = "UPDATE MEME SET [Size] = " + meme.getSize() + ", " +
            "Times_reported = " + meme.getTimesReported() + ", " +
            "Upload_date = '" + meme.getUploadDate() + "', " +
            "Is_private = " + meme.isPrivate() + ", " +
            "Views = " + meme.getViews() + ", " +
            "Likes = " + meme.getLikes() + ", " +
            "Dislikes = " + meme.getDislikes() + ", " +
            "Creator = " + meme.getCreatorId() + 
            " WHERE Meme_id = " + id + ";";
        this.getDatabaseConnection().execute(sql);
    }
}

