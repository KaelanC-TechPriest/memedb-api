package App.Application.Controllers;

import App.Domain.Meme;
import App.Infrastructure.MemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// URI uniform resource identifier
// http://localhost:8080/Meme/1 (GET)
// http://localhost:8080/Meme (POST) {}
// http://localhost:8080/Meme/1 (DELETE)

@RestController()
@RequestMapping("/Meme")
public class MemeController {

    private final MemeRepository memeRepository;

    public MemeController(@Autowired JdbcTemplate databaseConnection){
        this.memeRepository = new MemeRepository(databaseConnection);
    }

    @GetMapping("/{id}")
    public Meme get(@PathVariable("id") String id) throws Exception {
        return this.memeRepository.get(id);
    }

    @GetMapping("")
    public List<Meme> getAllMemes(){
        return this.memeRepository.get();
    }

    @PostMapping("")
    public void create(@RequestBody() Meme meme){
        this.memeRepository.create(meme);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") String id){
        this.memeRepository.delete(id);
    }

    @PutMapping("/{id}")
    public void updateById(@PathVariable("id") String id, @RequestBody() Meme meme) throws Exception {
        Meme existingMeme = this.memeRepository.get(id);
        existingMeme.setCreatorId(meme.getCreatorId());
        existingMeme.setDislikes(meme.getDislikes());
        existingMeme.setLikes(meme.getLikes());
        existingMeme.setPrivate(meme.isPrivate());
        existingMeme.setSize(meme.getSize());
        existingMeme.setTimesReported(meme.getTimesReported());
        existingMeme.setUploadDate(meme.getUploadDate());
        existingMeme.setViews(meme.getViews());
        this.memeRepository.update(id, existingMeme);
    }

}

