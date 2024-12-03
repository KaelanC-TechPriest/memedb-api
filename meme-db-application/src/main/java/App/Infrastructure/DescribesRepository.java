package App.Infrastructure;

import App.Domain.Describes;
import App.Domain.Meme;
import App.Domain.Tag;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class DescribesRepository extends BaseRepository<Describes>{

    public DescribesRepository(JdbcTemplate databaseConnection){
        super(databaseConnection);
    }

    // helper function
    private void updateTimesUsed(String tagName, int change) {
        String sql = "SELECT COUNT(*) FROM DESCRIBES WHERE Tag_name = '" + tagName + "';";
        Integer count = this.getDatabaseConnection().queryForObject(sql, Integer.class);
        count += change;
        sql = "UPDATE TAG SET Times_used = " + count + " WHERE [Name] = '" + tagName + "';";
        this.getDatabaseConnection().execute(sql);
    }

    @Override
    public Describes get(String name) throws Exception {
        String sql = "SELECT * FROM DESCRIBES WHERE Tag_name = '" + name +"';";
        List<Describes> descriptions = this.getDatabaseConnection().query(sql, BeanPropertyRowMapper.newInstance(Describes.class));
        if (descriptions.size() >= 1) {
            return descriptions.getFirst();
        }
        throw new Exception();
    }

    public List<Tag> getTags(String id) throws Exception {
        String sql = "SELECT DISTINCT t.* FROM TAG t INNER JOIN DESCRIBES d ON t.Name = d.Tag_name WHERE d.Meme_id = '" + id +"';";
        List<Tag> tags = this.getDatabaseConnection().query(sql, BeanPropertyRowMapper.newInstance(Tag.class));
        if (tags.size() == 1) {
            return tags;
        }
        throw new Exception();
    }

    public List<Meme> getMemes(String name) throws Exception {
        String sql = "SELECT DISTINCT m.* FROM MEME m INNER JOIN DESCRIBES d ON m.Meme_id = d.Meme_id WHERE d.Tag_name = '" + name +"';";
        List<Meme> memes = this.getDatabaseConnection().query(sql, BeanPropertyRowMapper.newInstance(Meme.class));
        if (memes.size() >= 1) {
            return memes;
        }
        throw new Exception();
    }

    @Override
    public List<Describes> get() {
        String sql = "SELECT * FROM DESCRIBES;";
        return this.getDatabaseConnection().query(sql, BeanPropertyRowMapper.newInstance(Describes.class));
    }

    @Override
    public void create(Describes description) {
        String sql = "INSERT INTO DESCRIBES VALUES ('" + description.getTagName() + "', " + description.getMemeId() + ");";
        updateTimesUsed(description.getTagName(), 1);
        this.getDatabaseConnection().execute(sql);
    }

    @Override
    public void delete(String name) {} // just to get rid of error. Both tagName and meme_id are part of the primary key for describes, so it needs both to be uniquely identified

    public void delete(Describes description) {
        String sql = "DELETE FROM DESCRIBES WHERE Tag_name = '" + description.getTagName() + "' AND Meme_id=" + description.getMemeId() + ";";
        updateTimesUsed(description.getTagName(), -1);
        this.getDatabaseConnection().execute(sql);
    }

    @Override
    public void update(String name, Describes description) {
        String sql = "UPDATE DESCRIBES SET Tag_name = '" + name + 
            "' WHERE Tag_name = '" + description.getTagName() + "' AND Meme_id = " + description.getMemeId() + ";";
        updateTimesUsed(name, 1);
        updateTimesUsed(description.getTagName(), -1);
        this.getDatabaseConnection().execute(sql);

    }

    public void update(int id, Describes description) {
        String sql = "UPDATE DESCRIBES SET Meme_id = " + id + 
            " WHERE Tag_name = '" + description.getTagName() + "' AND Meme_id = " + description.getMemeId() + ";";
        this.getDatabaseConnection().execute(sql);
 
    }
}

