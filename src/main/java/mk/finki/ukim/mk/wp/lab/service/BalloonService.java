package mk.finki.ukim.mk.wp.lab.service;

import mk.finki.ukim.mk.wp.lab.model.Balloon;

import java.util.List;
import java.util.Optional;

public interface BalloonService {

    List<Balloon> listAll();

    List<Balloon> searchByNameOrDescription(String text);

    Optional<Balloon> saveBalloon(String name, String description,Long manufacturerId);

    void deleteById(Long id);

    Optional<Balloon> findById(Long id);

}
