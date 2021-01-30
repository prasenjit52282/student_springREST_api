package stddb.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import stddb.DB.DataRepo;
import stddb.DB.Student;
import java.util.NoSuchElementException;

@RestController
public class DBController {
    @Autowired
    DataRepo database;

    //endpoints

    //home endpoint
    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody String home(){
        return "hello from Database Server";
    }

    //insert endpoint
    @PostMapping("/insert")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody String insert(@RequestBody Student std){
        database.save(std);
        return "Data Saved Successfully";
    }

    //read endpoints
    @GetMapping("/findAll")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody Iterable<Student> findAll(){
        return database.findAll();
    }

    @GetMapping("/find/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody Student findById(@PathVariable int id){
        return database.findById(id)
                .orElseThrow(()->new NoSuchElementException("Student not found with id: "+id));
    }

    //update endpoint
    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody String update(@PathVariable int id,@RequestBody Student std){
        if (database.existsById(id)) {
            database.save(std);
            return "Data updated Successfully";
        }
        else
            throw new NoSuchElementException("Student with id: "+id+" doesn't exist to update");
    }

    //delete endpoint
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody String delete(@PathVariable int id){
        if(database.existsById(id)){
            database.deleteById(id);
            return "Data deleted Successfully";
        }
        else
            throw new NoSuchElementException("Student with id: "+id+" doesn't exist to delete");
    }

}
