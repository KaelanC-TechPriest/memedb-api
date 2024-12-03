package App.Application.Controllers;

import App.Domain.User;
import App.Infrastructure.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// URI uniform resource identifier
// http://localhost:8080/User/1 (GET)
// http://localhost:8080/User (POST) {}
// http://localhost:8080/User/1 (DELETE)

@RestController()
@RequestMapping("/User")
public class UserController {

    private final UserRepository userRepository;

    public UserController(@Autowired JdbcTemplate databaseConnection){
        this.userRepository = new UserRepository(databaseConnection);
    }

    @GetMapping("/{id}")
    public User get(@PathVariable("id") String id) throws Exception {
        return this.userRepository.get(id);
    }

    @GetMapping("")
    public List<User> getAllUsers(){
        return this.userRepository.get();
    }

    @PostMapping("")
    public void create(@RequestBody() User user){
        this.userRepository.create(user);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") String id){
        this.userRepository.delete(id);
    }

    @PutMapping("/{id}")
    public void updateById(@PathVariable("id") String id, @RequestBody() User user) throws Exception {
        User updatedUser = this.userRepository.get(id);
        updatedUser.setUsername(user.getUsername());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setPassword(user.getPassword());
        this.userRepository.update(id, updatedUser);
    }

}

