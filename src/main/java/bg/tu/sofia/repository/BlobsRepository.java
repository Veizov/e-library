package bg.tu.sofia.repository;

import bg.tu.sofia.model.Blobs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlobsRepository extends JpaRepository<Blobs, Integer> {

}
