package mk.finki.ukim.mk.wp.lab.repository.jpa;

import mk.finki.ukim.mk.wp.lab.model.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer,Long> {

}
