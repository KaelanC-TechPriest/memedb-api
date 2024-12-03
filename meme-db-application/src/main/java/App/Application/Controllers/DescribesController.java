package App.Application.Controllers;

import App.Domain.Describes;
import App.Domain.Meme;
import App.Domain.Tag;
import App.Infrastructure.DescribesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// URI uniform resource identifier
// http://localhost:8080/Describes/1 (GET)
// http://localhost:8080/Describes (POST) {}
// http://localhost:8080/Describes/1 (DELETE)

@RestController()
@RequestMapping("/Describes")
public class DescribesController {

    private final DescribesRepository describesRepository;

    public DescribesController(@Autowired JdbcTemplate databaseConnection){
        this.describesRepository = new DescribesRepository(databaseConnection);
    }

    @GetMapping("")
    public List<Describes> getAllDescriptions(){
        return this.describesRepository.get();
    }

    @GetMapping("/Tag/{name}")
    public List<Meme> getMemes(@PathVariable("name") String name) throws Exception {
        List<Meme> descriptions = this.describesRepository.getMemes(name);
        return descriptions;
    }

    @GetMapping("/Meme/{name}")
    public List<Tag> getTags(@PathVariable("name") String name) throws Exception {
        List<Tag> descriptions = this.describesRepository.getTags(name);
        return descriptions;
    }

    @PostMapping("")
    public void create(@RequestBody() Describes describes){
        this.describesRepository.create(describes);
    }

    @DeleteMapping("")
    public void delete(@RequestBody() Describes description){
        this.describesRepository.delete(description);
    }

    @PutMapping("/Tag/{name}")
    public void updateByName(@PathVariable("name") String name, @RequestBody() Describes description) throws Exception {
        this.describesRepository.update(name, description);
    }

    @PutMapping("/Meme/{id}")
    public void updateById(@PathVariable("id") int id, @RequestBody() Describes description) throws Exception {
        this.describesRepository.update(id, description);
    }
}
