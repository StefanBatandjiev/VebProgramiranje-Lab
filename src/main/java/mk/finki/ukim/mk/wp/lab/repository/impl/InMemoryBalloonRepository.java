package mk.finki.ukim.mk.wp.lab.repository.impl;

import mk.finki.ukim.mk.wp.lab.bootstrap.DataHolder;
import mk.finki.ukim.mk.wp.lab.model.Balloon;
import mk.finki.ukim.mk.wp.lab.model.Manufacturer;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class InMemoryBalloonRepository {

    public List<Balloon> findAllBalloons(){
        return DataHolder.balloons;
    }

    public List<Balloon> findAllByNameOrDescription(String text){
        return DataHolder.balloons.stream().filter(r->r.getName().contains(text) || r.getDescription().contains(text)).collect(Collectors.toList());
    }
    public Optional<Balloon> save(String name, String description, Manufacturer manufacturer){
        DataHolder.balloons.removeIf(r -> r.getName().equals(name));
        Balloon balloon = new Balloon(name, description, manufacturer);
        DataHolder.balloons.add(balloon);
        return Optional.of(balloon);
    }

    public void deleteById(Long id){
        DataHolder.balloons.removeIf(r -> r.getId().equals(id));
    }

    public Optional<Balloon> findById(Long id) {
        return DataHolder.balloons.stream().filter(r -> r.getId().equals(id)).findFirst();
    }

}
