package br.unoeste.fipp.ativooperante.restControllers;

import br.unoeste.fipp.ativooperante.dataBase.entities.Agency;
import br.unoeste.fipp.ativooperante.dataBase.entities.Complaint;
import br.unoeste.fipp.ativooperante.dataBase.entities.Type;
import br.unoeste.fipp.ativooperante.services.agencyService;
import br.unoeste.fipp.ativooperante.services.complaintService;
import br.unoeste.fipp.ativooperante.services.typeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="apis/admin/")
public class adminRestController {

    @Autowired
    private agencyService agService;

    @Autowired
    private complaintService cpService;

    @Autowired
    private typeService tpService;

    @GetMapping(value="connection-test")
    public String connectionTest(){
        return "connected";
    }


    /////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////// CRUD for Agencies //////////////////////////////////////////////

    @GetMapping("/delete-agency")
    public ResponseEntity<Object> deleteAgency(@RequestParam(value="org_id") Long org_id)
    {
        if(agService.deleteById(org_id))
            return new ResponseEntity<>("",HttpStatus.OK);
        else
            return new ResponseEntity<>("",HttpStatus.BAD_REQUEST);
    }
    @PostMapping("/add-agency")
    public ResponseEntity<Object> addAgency (@RequestBody Agency agency)
    {
        return new ResponseEntity<>(agService.addAgency(agency), HttpStatus.OK);

    }
    @PostMapping("/edit-agency")
    public ResponseEntity<Object> editAgency(@RequestBody Agency agency) {
        if(agService.editAgency(agency)) {
            return new ResponseEntity<>("Orgao editado com sucesso!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Falha ao editar Orgao.", HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/get-agency")
    public ResponseEntity<Object> getAgency(@RequestParam(value="org_id") Long org_id)
    {
        return new ResponseEntity<>(agService.getById(org_id),HttpStatus.OK);
    }
    @GetMapping("/get-all-agencies")
    public ResponseEntity<Object> getAllAgencies()
    {
        return new ResponseEntity<>(agService.getAll(),HttpStatus.OK);
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////// CRUD for Complaints ///////////////////////////////////////////

    @GetMapping("/delete-complaint")
    public ResponseEntity<Object> deleteComplaint(@RequestParam(value="den_id") Long den_id)
    {
        if(cpService.deleteById(den_id))
            return new ResponseEntity<>("",HttpStatus.OK);
        else
            return new ResponseEntity<>("",HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/get-complaint")
    public ResponseEntity<Object> getComplaint(@RequestParam(value="den_id") Long den_id)
    {
        return new ResponseEntity<>(cpService.getById(den_id),HttpStatus.OK);
    }

    @GetMapping("/get-all-complaints")
    public ResponseEntity<Object> getAllComplaints()
    {
        return new ResponseEntity<>(cpService.getAll(),HttpStatus.OK);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////// CRUD for Problems Type /////////////////////////////////////////

    @GetMapping("/delete-type")
    public ResponseEntity<Object> deleteType(@RequestParam(value="tip_id") Long tip_id)
    {
        if(tpService.deleteById(tip_id))
            return new ResponseEntity<>("",HttpStatus.OK);
        else
            return new ResponseEntity<>("",HttpStatus.BAD_REQUEST);
    }
    @PostMapping("/edit-type")
    public ResponseEntity<Object> editType(@RequestBody Type type) {
        if(tpService.editType(type)) {
            return new ResponseEntity<>("Tipo de problema editado com sucesso!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Falha ao editar tipo de problema.", HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/add-type")
    public ResponseEntity<Object> addType (@RequestBody Type type)
    {
        return new ResponseEntity<>(tpService.addType(type), HttpStatus.OK);
    }

    @GetMapping("/get-type")
    public ResponseEntity<Object> getType(@RequestParam(value="tip_id") Long tip_id)
    {
        return new ResponseEntity<>(tpService.getById(tip_id),HttpStatus.OK);
    }

    @GetMapping("/get-all-types")
    public ResponseEntity<Object> getAllTypes()
    {
        return new ResponseEntity<>(tpService.getAll(),HttpStatus.OK);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////// CRUD for Feedbacks //////////////////////////////////////////////

    @PostMapping("/add-feedback")
    public ResponseEntity<Object> addFeedback(@RequestParam("den_id") Long den_id,
                                              @RequestParam("feedback") String feedback)
    {
        if(cpService.addFeed(den_id,feedback))
            return new ResponseEntity<>("",HttpStatus.OK);
        else
            return new ResponseEntity<>("",HttpStatus.BAD_REQUEST);
    }



    }
