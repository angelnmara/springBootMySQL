package com.lamarrulla.mysql;

import com.lamarrulla.mysql.model.User;
import com.lamarrulla.mysql.model.Usuarios;
import com.lamarrulla.mysql.repository.UserRepository;
import com.lamarrulla.mysql.repository.UsuariosRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller // This means that this class is a Controller
@RequestMapping(path="/demo") // This means URL's start with /demo (after Application path)
public class MainController {
    //@Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private final UserRepository userRepository;
    private final UsuariosRepository usuariosRepository;

    Logger logger = LoggerFactory.getLogger(MainController.class);


    public MainController(UserRepository userRepository, UsuariosRepository usuariosRepository) {
        this.userRepository = userRepository;
        this.usuariosRepository = usuariosRepository;
    }

    @PostMapping(path="/add") // Map ONLY POST Requests
    public @ResponseBody String addNewUser (@RequestParam String name
            , @RequestParam String email) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        User n = new User();
        n.setName(name);
        n.setEmail(email);
        userRepository.save(n);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        // This returns a JSON or XML with the users
        return userRepository.findAll();
    }

    @GetMapping(path="/Usuario")
    public @ResponseBody Iterable<Usuarios> getAllUsuarios(){
        return this.usuariosRepository.findAll();
    }

    @GetMapping(path="/Usuario/{id}")
    public @ResponseBody Optional<Usuarios> getUsuariosById(@PathVariable("id") Integer id){
        return this.usuariosRepository.findById(id);
    }

    @PostMapping(path="/Usuario")
    public ResponseEntity createUsuario(@RequestBody Usuarios usuarios){
        this.usuariosRepository.save(usuarios);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping(path="/Usuario/{id}")
    public ResponseEntity deleteUsuario(@PathVariable("id") Integer id){
        logger.debug(id.toString());
        this.usuariosRepository.deleteById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}