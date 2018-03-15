package org.example.service;

import org.example.model.Greeting;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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
    @Cacheable(
            value = "greetings",
            key = "#id")
    @CachePut(
            value = "greetings",
            key = "#result.id")
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
    @CachePut(
            value = "greetings",
            key = "#greeting.id")
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
    @CacheEvict(
            value = "greetings",
            key = "#id"
    )
    public void delete(Long id) {
        remove(id);
    }

    @Override
    @CacheEvict(
            value = "greetings",
            allEntries = true)
    public void evictCache(){

    }
}
