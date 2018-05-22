package bg.tu.sofia.service.impl;

import bg.tu.sofia.model.Language;
import bg.tu.sofia.repository.LanguageRepository;
import bg.tu.sofia.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LanguageServiceImpl implements LanguageService {

    @Autowired
    private LanguageRepository languageRepository;

    @Override
    public List<Language> findAll() {
        return languageRepository.findAll();
    }

}
