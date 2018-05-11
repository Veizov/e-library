package bg.tu.sofia.service.impl;


import bg.tu.sofia.model.Blobs;
import bg.tu.sofia.model.Book;
import bg.tu.sofia.repository.BlobsRepository;
import bg.tu.sofia.repository.BookRepository;
import bg.tu.sofia.service.BlobsService;
import bg.tu.sofia.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class BlobsServiceImpl implements BlobsService {

    @Autowired
    private BlobsRepository blobsRepository;

    @Override
    public Blobs findBlobsById(Integer id) {
        //TODO
        return blobsRepository.findById(id).get();
    }

}
