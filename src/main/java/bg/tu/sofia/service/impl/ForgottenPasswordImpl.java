package bg.tu.sofia.service.impl;

import bg.tu.sofia.model.ForgottenPassword;
import bg.tu.sofia.repository.ForgottenPasswordRepository;
import bg.tu.sofia.service.ForgottenPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Service
public class ForgottenPasswordImpl implements ForgottenPasswordService {

    @Autowired
    private ForgottenPasswordRepository forgottenPasswordRepository;


    @Override
    public ForgottenPassword save(ForgottenPassword forgottenPassword) {
        if (Objects.isNull(forgottenPassword))
            return null;
        return forgottenPasswordRepository.save(forgottenPassword);
    }

    @Override
    public ForgottenPassword findByToken(String token) {
        if (StringUtils.isEmpty(token))
            return null;
        return forgottenPasswordRepository.findByToken(token);
    }

    @Override
    public ForgottenPassword findByEmail(String email) {
        if (StringUtils.isEmpty(email))
            return null;
        return forgottenPasswordRepository.findByEmail(email);
    }
}
