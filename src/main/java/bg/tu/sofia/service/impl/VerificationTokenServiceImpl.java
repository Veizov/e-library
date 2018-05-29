package bg.tu.sofia.service.impl;


import bg.tu.sofia.model.VerificationToken;
import bg.tu.sofia.repository.VerificationTokenRepository;
import bg.tu.sofia.service.VerificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class VerificationTokenServiceImpl implements VerificationTokenService {

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Override
    public VerificationToken save(VerificationToken token) {
        if (Objects.isNull(token))
            return null;
        return verificationTokenRepository.save(token);
    }

    @Override
    public VerificationToken getVerificationToken(String VerificationToken) {
        return verificationTokenRepository.findByToken(VerificationToken);
    }

    @Override
    public VerificationToken findByUser_Id(Integer id) {
        if (Objects.isNull(id))
            return null;
        return verificationTokenRepository.findByUser_Id(id);
    }


}
