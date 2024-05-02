package br.unoeste.fipp.ativooperante.services;

import br.unoeste.fipp.ativooperante.dataBase.entities.Complaint;
import br.unoeste.fipp.ativooperante.dataBase.repositories.complaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
public class complaintService{

    @Autowired
    private complaintRepository complaintRepo;

    @Autowired
    private ResourceLoader resourceLoader;

    public boolean addFeed(Long den_id, String feedback){
        complaintRepo.addFeedback(den_id,feedback);
        return true;
    }

    public Complaint addComplaint(Complaint complaint, MultipartFile image) throws IOException {

        File pasta_img = new File(getStaticPath()+"\\images");

        String nomeArq;

        complaint = complaintRepo.save(complaint);
        if(!pasta_img.exists())
            pasta_img.mkdir();

        nomeArq=complaint.getId()+"."+extensao(image.getOriginalFilename());

        Files.copy(image.getInputStream(),
                Paths.get(pasta_img.getAbsolutePath()).resolve(nomeArq), StandardCopyOption.REPLACE_EXISTING);

        return complaint;
    }

    public Complaint getById(Long id){
        Complaint complaint = complaintRepo.findById(id).get();
        return complaint;
    }

    public List<Complaint> getAll(){
        return complaintRepo.findAll();
    }

    public boolean deleteById(Long id){
        try{
            complaintRepo.deleteById(id);
        }
        catch (Exception e){
            return false;
        }
        return true;
    }

    public String getStaticPath() throws IOException {
        String staticPath;
        staticPath = resourceLoader.getResource("classpath:static").getFile().getAbsolutePath();
        return staticPath;
    }

    private String extensao(String extensao)
    {
        int lastIndex = extensao.lastIndexOf(".");
        return  extensao.substring(lastIndex + 1);
    }
}
