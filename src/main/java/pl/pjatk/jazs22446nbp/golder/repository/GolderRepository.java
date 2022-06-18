package pl.pjatk.jazs22446nbp.golder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pjatk.jazs22446nbp.golder.model.Golder;

public interface GolderRepository extends JpaRepository<Golder, Integer> {
    Golder save(Golder golder);
}
