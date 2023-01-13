/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Final_Exam.Exam_Pws;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpEntity;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author AXEL
 */
@RestController
@CrossOrigin
public class myController {
    Datapen data = new Datapen();
    DatapenJpaController dctrl = new DatapenJpaController();
    

    
    @RequestMapping("/GET")
    @ResponseBody
    public List<Datapen> getDatabyID(){
        List<Datapen> datas = new ArrayList<>();
        try {datas = dctrl.findDatapenEntities();}
        catch(Exception error) {}        
        return datas;
    }
    

    @RequestMapping(value ="/POST", method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    public String sendData(HttpEntity<String> kiriman) throws Exception{
        String message="no action";
        String json_receive = kiriman.getBody();
        ObjectMapper mapper = new ObjectMapper();
        Datapen data = new Datapen(); //jika ingin banyak data pake List atau ArrayList
        data = mapper.readValue(json_receive, Datapen.class);
        dctrl.create(data);
        message = data.getNama()+" Saved";
        return message;
    }
    

    @RequestMapping(value ="/PUT", method = RequestMethod.PUT, consumes = APPLICATION_JSON_VALUE)
    public String editData(HttpEntity<String> kiriman) throws Exception{
        String message="no action";
        String json_receive = kiriman.getBody();
        ObjectMapper mapper = new ObjectMapper();
        Datapen newdatas = new Datapen(); //jika ingin banyak data pake List atau ArrayList
        
        newdatas = mapper.readValue(json_receive, Datapen.class);
        try {dctrl.edit(newdatas);} catch (Exception e){}
        message = newdatas.getNama()+" Saved";
        return message;
    }
    
        @RequestMapping(value ="/DELETE", method = RequestMethod.DELETE, consumes = APPLICATION_JSON_VALUE)
    public String deleteData(HttpEntity<String> kiriman) throws Exception{
        String message="no action";
        String json_receive = kiriman.getBody();
        ObjectMapper mapper = new ObjectMapper();
        Datapen newdatas = new Datapen(); //jika ingin banyak data pake List atau ArrayList     
        newdatas = mapper.readValue(json_receive, Datapen.class);
        dctrl.destroy(newdatas.getId());
        return "deleted";
    }
    
}