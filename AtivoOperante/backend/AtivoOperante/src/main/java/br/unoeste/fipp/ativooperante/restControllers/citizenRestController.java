package br.unoeste.fipp.ativooperante.restControllers;



import br.unoeste.fipp.ativooperante.dataBase.entities.Complaint;
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

    @GetMapping(value="connection-test")
    public String connectionTest(){
        return "connected";
    }

    //demais APIs

    /////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////// CRUD for Agencies //////////////////////////////////////////////

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
                                               @RequestParam("usu_id") Long usu_id,
                                               @RequestParam("image") MultipartFile image) {
        try {
            Complaint complaint=null;
            complaint.setTitle(title);
            complaint.setText(text);
            complaint.setUrgency(urgency);
            complaint.setAgency(agService.getById(org_id));
            complaint.setData(LocalDate.now());
            complaint.setType(tpService.getById(tip_id));
            complaint.setUser(usService.getById(usu_id));

            return new ResponseEntity<>(cpService.addComplaint(complaint,image),HttpStatus.OK);

        }catch (Exception e){
            return ResponseEntity.badRequest().body("Erro ao enviar denuncia "+e.getMessage());
        }
    }
}
