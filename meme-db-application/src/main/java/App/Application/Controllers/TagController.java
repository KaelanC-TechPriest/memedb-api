package App.Application.Controllers;

import App.Domain.Tag;
import App.Infrastructure.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// URI uniform resource identifier
// http://localhost:8080/Tag/1 (GET)
// http://localhost:8080/Tag (POST) {}
// http://localhost:8080/Tag/1 (DELETE)

@RestController()
@RequestMapping("/Tag")
public class TagController {

    private final TagRepository tagRepository;

    public TagController(@Autowired JdbcTemplate databaseConnection){
        this.tagRepository = new TagRepository(databaseConnection);
    }

    @GetMapping("/{name}")
    public Tag get(@PathVariable("name") String name) throws Exception {
        return this.tagRepository.get(name);
    }

    @GetMapping("")
    public List<Tag> getAllTags(){
        return this.tagRepository.get();
    }

    @PostMapping("")
    public void create(@RequestBody() Tag tag){
        this.tagRepository.create(tag);
    }

    @DeleteMapping("/{name}")
    public void deleteByName(@PathVariable("name") String name){
        this.tagRepository.delete(name);
    }

    @PutMapping("/{name}")
    public void updateByName(@PathVariable("name") String name, @RequestBody() Tag tag) throws Exception {
        Tag existingTag = this.tagRepository.get(name);
        existingTag.setTimesUsed(tag.getTimesUsed());
        this.tagRepository.update(name, existingTag);
    }

}

