package org.example.web.api;


import org.example.model.Greeting;
import org.example.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class GreetingController {

    @Autowired
    private GreetingService greetingService;

    @RequestMapping(
            value = "/api/greetings",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Greeting>> getGreetings() {

        Collection<Greeting> greetings = greetingService.findAll();

        return new ResponseEntity<Collection<Greeting>>(greetings,
                HttpStatus.OK);
    }

    @RequestMapping(
            value = "/api/greetings/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Greeting> getGreeting(@PathVariable("id") Long id){
        Greeting gr = greetingService.findOne(id);
        if(gr == null){
            return new ResponseEntity<Greeting>(HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<Greeting>(gr, HttpStatus.OK);
        }
    }

    @RequestMapping(
            value = "/api/greetings",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Greeting>  createGreeting(@RequestBody Greeting gr){
        Greeting greet = greetingService.create(gr);

        return new ResponseEntity<Greeting>(greet, HttpStatus.CREATED);

    }

    @RequestMapping(
            value = "/api/greetings/{id}",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Greeting> updateGreeting(@RequestBody Greeting gr){
        Greeting greet = greetingService.update(gr);

        if(greet == null){
            return new ResponseEntity<Greeting>(greet, HttpStatus.NOT_MODIFIED);
        }
        else{
            return new ResponseEntity<Greeting>(greet, HttpStatus.OK);
        }

    }

    @RequestMapping(
            value = "/api/greetings/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Greeting> deleteGreeting(@PathVariable("id") Long id, @RequestBody Greeting greeting){
        greetingService.delete(id);

        return new ResponseEntity<Greeting>(HttpStatus.NO_CONTENT);
    }

}
