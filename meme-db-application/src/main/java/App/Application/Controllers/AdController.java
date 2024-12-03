package App.Application.Controllers;

import App.Domain.Ad;
import App.Infrastructure.AdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// URI uniform resource identifier
// http://localhost:8080/Ad/1 (GET)
// http://localhost:8080/Ad (POST) {}
// http://localhost:8080/Ad/1 (DELETE)

@RestController()
@RequestMapping("/Ad")
public class AdController {

    private final AdRepository adRepository;

    public AdController(@Autowired JdbcTemplate databaseConnection){
        this.adRepository = new AdRepository(databaseConnection);
    }

    @GetMapping("/{number}")
    public Ad get(@PathVariable("number") String number) throws Exception {
        return this.adRepository.get(number);
    }

    @GetMapping("")
    public List<Ad> getAllAds(){
        return this.adRepository.get();
    }

    @PostMapping("")
    public void create(@RequestBody() Ad ad){
        this.adRepository.create(ad);
    }

    @DeleteMapping("/{number}")
    public void deleteById(@PathVariable("number") String number){
        this.adRepository.delete(number);
    }

    @PutMapping("/{number}")
    public void updateById(@PathVariable("number") String number, @RequestBody() Ad ad) throws Exception {
        Ad updatedAd = this.adRepository.get(number);
        updatedAd.setPublisher(ad.getPublisher());
        updatedAd.setUrl(ad.getUrl());
        updatedAd.setDatePublished(ad.getDatePublished());
        this.adRepository.update(number, updatedAd);
    }

}

