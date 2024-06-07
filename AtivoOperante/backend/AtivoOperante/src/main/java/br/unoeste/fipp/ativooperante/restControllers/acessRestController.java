package br.unoeste.fipp.ativooperante.restControllers;

import br.unoeste.fipp.ativooperante.dataBase.entities.User;
import br.unoeste.fipp.ativooperante.security.JWTTokenProvider;
import br.unoeste.fipp.ativooperante.services.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="apis/security/")
public class acessRestController {

    @Autowired
    private userService users;

    @GetMapping(value="connection-test")
    public String connectionTest() {
        return "connected";
    }

    /////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////// CRUD for User //////////////////////////////////////////////
    @CrossOrigin(origins = "http://localhost:63342")
    @PostMapping(value="/login")
    public ResponseEntity<Object> login(@RequestBody User user){
        try {
            User existingUser = users.getByEmail(user.getEmail());
            if(existingUser == null) {
                return ResponseEntity.badRequest().body("Usuário não cadastrado");
            }
            if(existingUser.getPassword().equals(user.getPassword())) {
                // Gera o token JWT
                String token = JWTTokenProvider.getToken(existingUser.getEmail(),"" + existingUser.getNivel());
                return ResponseEntity.ok(token);
            } else {
                return ResponseEntity.badRequest().body("Senha incorreta");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao autenticar usuário: " + e.getMessage());
        }
    }

    @GetMapping("/get-user")
    public ResponseEntity<Object> getUser(@RequestParam(value="usu_id") Long usu_id) {
        try {
            User user = users.getById(usu_id);
            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
            }
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao obter usuário: " + e.getMessage());
        }
    }

    @GetMapping("/get-id-by-email")
    public ResponseEntity<Object> getIdByEmail(@RequestParam(value="email") String email) {
        try {
            User user = users.getByEmail(email);
            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
            }
            return ResponseEntity.ok(user.getId());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao obter ID do usuário: " + e.getMessage());
        }
    }

}
