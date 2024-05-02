package br.unoeste.fipp.ativooperante.restControllers;

import br.unoeste.fipp.ativooperante.dataBase.entities.User;
import br.unoeste.fipp.ativooperante.services.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value="apis/user/")
public class userRestController {

        @Autowired
        private userService usService;

        @GetMapping(value="connection-test")
        public String connectionTest(){
            return "connected";
        }

        /////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////// CRUD for User //////////////////////////////////////////////

        @PostMapping(value="/add-user")
        public ResponseEntity<Object> addUser(@RequestParam("cpf") String cpf,
                                                   @RequestParam("email") String email,
                                                   @RequestParam("password") int password,
                                                   @RequestParam("password2") int password2){
                try {
                        if(password==password2) {
                                User user = null;
                                user.setCpf(cpf);
                                user.setEmail(email);
                                user.setPassword(password);

                                return new ResponseEntity<>(usService.addUser(user),HttpStatus.OK);
                        }
                        else
                                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
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
