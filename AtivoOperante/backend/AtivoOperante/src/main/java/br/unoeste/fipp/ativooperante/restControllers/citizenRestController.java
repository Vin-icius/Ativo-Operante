package br.unoeste.fipp.ativooperante.restControllers;



import br.unoeste.fipp.ativooperante.dataBase.entities.Complaint;
import br.unoeste.fipp.ativooperante.dataBase.entities.User;
import br.unoeste.fipp.ativooperante.services.agencyService;
import br.unoeste.fipp.ativooperante.services.complaintService;
import br.unoeste.fipp.ativooperante.services.typeService;
import br.unoeste.fipp.ativooperante.services.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping(value="apis/citizen/")
public class citizenRestController {

    @Autowired
    private complaintService cpService;
    @Autowired
    private agencyService agService;
    @Autowired
    private typeService tpService;
    @Autowired
    private userService usService;
    @CrossOrigin(origins = "http://localhost:63342")
    @GetMapping(value="connection-test")
    public String connectionTest(){
        return "connected";
    }

    //demais APIs

    /////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////// CRUD for Agencies //////////////////////////////////////////////

    @CrossOrigin(origins = "http://localhost:63342")
    @GetMapping("/get-all-agencies")
    public ResponseEntity<Object> getAllAgencies()
    {
        return new ResponseEntity<>(agService.getAll(),HttpStatus.OK);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////// CRUD for Types //////////////////////////////////////////////

    @GetMapping("/get-all-types")
    public ResponseEntity<Object> getAllTypes()
    {
        return new ResponseEntity<>(tpService.getAll(),HttpStatus.OK);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////// CRUD for Complaints ///////////////////////////////////////

    @PostMapping(value="/add-complaint")
    public ResponseEntity<Object> addComplaint(@RequestParam("title") String title,
                                               @RequestParam("text") String text,
                                               @RequestParam("urgency") int urgency,
                                               @RequestParam("org_id") Long org_id,
                                               @RequestParam("tip_id") Long tip_id,
                                               @RequestParam("usu_id") Long usu_id) {
        try {
            Complaint complaint = new Complaint();

            complaint.setTitle(title);
            complaint.setText(text);
            complaint.setUrgency(urgency);
            complaint.setAgency(agService.getById(org_id));
            complaint.setData(LocalDate.now());
            complaint.setType(tpService.getById(tip_id));
            complaint.setUser(usService.getById(usu_id));

            return new ResponseEntity<>(cpService.addDenuncia(complaint), HttpStatus.OK);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao enviar denuncia " + e.getMessage());
        }
    }

    @GetMapping("get-complaints-by-email")
    public ResponseEntity<List<Complaint>> getComplaintsByEmail(@RequestParam("email") String email) {
        try {
            List<Complaint> complaints = cpService.getComplaintsByUserEmail(email);
            return ResponseEntity.ok(complaints);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
    //Crud para user
    @Autowired
    private userService userService;

    @CrossOrigin(origins = "http://localhost:63342")
    @PostMapping("/add-user")
    public ResponseEntity<Object> addUser(@RequestBody User user) {
        if (!User.isValidEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body("Email inválido");
        }
        if (!User.isValidPassword(user.getPassword())) {
            return ResponseEntity.badRequest().body("Senha inválida");
        }

        try {
            return new ResponseEntity<>(userService.addUser(user), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-user")
    public ResponseEntity<Object> getUser(@RequestParam(value="usu_email") String usu_email) {
        return new ResponseEntity<>(userService.getByEmail(usu_email),HttpStatus.OK);
    }

    @GetMapping("/get-all-users")
    public ResponseEntity<Object> getAllUsers() {
        return new ResponseEntity<>(userService.getAll(),HttpStatus.OK);
    }
    //---
}
