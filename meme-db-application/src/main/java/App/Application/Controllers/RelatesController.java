package App.Application.Controllers;

import App.Domain.Relates;
import App.Domain.Ad;
import App.Domain.Tag;
import App.Infrastructure.RelatesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// URI uniform resource identifier
// http://localhost:8080/Relates/1 (GET)
// http://localhost:8080/Relates (POST) {}
// http://localhost:8080/Relates/1 (DELETE)

@RestController()
@RequestMapping("/Relates")
public class RelatesController {

    private final RelatesRepository relatesRepository;

    public RelatesController(@Autowired JdbcTemplate databaseConnection){
        this.relatesRepository = new RelatesRepository(databaseConnection);
    }

    @GetMapping("")
    public List<Relates> getAllRelationships(){
        return this.relatesRepository.get();
    }

    @GetMapping("/Tag/{name}")
    public List<Ad> getAds(@PathVariable("name") String name) throws Exception {
        List<Ad> descriptions = this.relatesRepository.getAds(name);
        return descriptions;
    }

    @GetMapping("/Ad/{name}")
    public List<Tag> getTags(@PathVariable("name") String name) throws Exception {
        List<Tag> descriptions = this.relatesRepository.getTags(name);
        return descriptions;
    }

    @PostMapping("")
    public void create(@RequestBody() Relates relationship){
        this.relatesRepository.create(relationship);
    }

    @DeleteMapping("")
    public void delete(@RequestBody() Relates relationship){
        this.relatesRepository.delete(relationship);
    }

    @PutMapping("/Tag/{name}")
    public void updateByName(@PathVariable("name") String name, @RequestBody() Relates relationship) throws Exception {
        this.relatesRepository.update(name, relationship);
    }

    @PutMapping("/Ad/{id}")
    public void updateById(@PathVariable("id") int id, @RequestBody() Relates relationship) throws Exception {
        this.relatesRepository.update(id, relationship);
    }
}
