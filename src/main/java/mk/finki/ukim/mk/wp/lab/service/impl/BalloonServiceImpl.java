package mk.finki.ukim.mk.wp.lab.service.impl;

import mk.finki.ukim.mk.wp.lab.model.Balloon;
import mk.finki.ukim.mk.wp.lab.model.Manufacturer;
import mk.finki.ukim.mk.wp.lab.repository.jpa.BalloonRepository;
import mk.finki.ukim.mk.wp.lab.repository.jpa.ManufacturerRepository;
import mk.finki.ukim.mk.wp.lab.service.BalloonService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BalloonServiceImpl implements BalloonService {

    private final BalloonRepository balloonRepository;
    private final ManufacturerRepository manufacturerRepository;

    public BalloonServiceImpl(BalloonRepository balloonRepository, ManufacturerRepository manufacturerRepository) {
        this.balloonRepository = balloonRepository;
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public List<Balloon> listAll() {
        return balloonRepository.findAll();
    }

    @Override
    public List<Balloon> searchByNameOrDescription(String text) {
        return balloonRepository.findAllByNameOrDescription(text,text);
    }

    @Override
    public Optional<Balloon> saveBalloon(String name, String description, Long manufacturerId) {
        Manufacturer manufacturer = null;
        if (this.manufacturerRepository.findById(manufacturerId).isPresent()){
            manufacturer = this.manufacturerRepository.findById(manufacturerId).get();
        }
        return Optional.of(this.balloonRepository.save(new Balloon(name,description,manufacturer)));
    }

    @Override
    public void deleteById(Long id) {
        this.balloonRepository.deleteById(id);
    }

    @Override
    public Optional<Balloon> findById(Long id) {
        return this.balloonRepository.findById(id);
    }

}
