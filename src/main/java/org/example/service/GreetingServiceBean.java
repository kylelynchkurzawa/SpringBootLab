package org.example.service;

import org.example.model.Greeting;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class GreetingServiceBean implements GreetingService {

    private static long nextId;
    private static Map<Long, Greeting> greetingMap;

    private static Greeting save(Greeting gr){
        if(greetingMap == null){
            greetingMap = new HashMap<Long, Greeting>();
            nextId = 1L;
        }
        //if it has an id, its an update
        if(gr.getId() != null){
            Greeting oldGreeting = greetingMap.get(gr.getId());
            if(oldGreeting == null){
                return null;
            }
            else{
                greetingMap.remove(gr.getId());
                greetingMap.put(gr.getId(), gr);
                return gr;
            }
        }

        //else it's a create
        gr.setId(nextId);
        nextId++;
        greetingMap.put(gr.getId(), gr);
        return gr;
    }

    private static boolean remove(long id){
        Greeting deletedGreeting = greetingMap.remove(id);
        if(deletedGreeting == null){
            return false;
        }
        else{
            return true;
        }
    }

    static{
        Greeting g1 = new Greeting();
        g1.setText("Hello Digisoft!!");
        save(g1);

        Greeting g2 = new Greeting();
        g2.setText("Goodbye Digisoft!!");
        save(g2);
    }

    @Override
    public Collection<Greeting> findAll() {

        Collection<Greeting> greetings = greetingMap.values();

        return greetings;
    }

    @Override
    public Greeting findOne(Long id) {
        Greeting gr = greetingMap.get(id);
        if(gr == null){
            return gr;
        }
        else{
            return gr;
        }
    }

    @Override
    public Greeting create(Greeting greeting) {
        if(greeting.getId() != null){
            //can't make a new greeting with a pre-defined id
            return null;
        }
        Greeting greet = save(greeting);

        return greet;
    }

    @Override
    public Greeting update(Greeting greeting) {
        Greeting persisted = findOne(greeting.getId());
        if(persisted == null){
            //can't update an item that doesn't exist
            return null;
        }

        Greeting greet = save(greeting);
        return greet;
    }

    @Override
    public void delete(Long id) {
        remove(id);
    }
}
