package bg.tu.sofia.service.impl;


import bg.tu.sofia.model.Blobs;
import bg.tu.sofia.repository.BlobsRepository;
import bg.tu.sofia.service.BlobsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlobsServiceImpl implements BlobsService {

    @Autowired
    private BlobsRepository blobsRepository;

    @Override
    public Blobs findBlobsById(Integer id) {
        return blobsRepository.findById(id).orElse(null);
    }

}
