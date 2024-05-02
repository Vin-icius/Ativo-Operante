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
public class accessRestController {

        @Autowired
        private userService usService;

        @GetMapping(value="connection-test")
        public String connectionTest(){
            return "connected";
        }

        /////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////// CRUD for User //////////////////////////////////////////////

        @PostMapping(value="/add-user")
        public ResponseEntity<Object> addUser(@RequestBody User user){
                try {
                        String token="not authentic";
                        //acesso ao banco de dados para verificar a existencia do usuario
                        //comparar a senha
                        if(user.getPassword()==123){
                                token = JWTTokenProvider.getToken(user.getEmail(),""+user.getNivel());
                                return ResponseEntity.ok(token);
                        }
                        return ResponseEntity.badRequest().body(token);

                }catch (Exception e){
                        return ResponseEntity.badRequest().body("Erro ao cadastrar usuario "+e.getMessage());
                }
        }
        @GetMapping("/get-user")
        public ResponseEntity<Object> getUser(@RequestParam(value="usu_id") Long usu_id)
        {
            return new ResponseEntity<>(usService.getById(usu_id),HttpStatus.OK);
        }


}
