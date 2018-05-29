package bg.tu.sofia.service;

import bg.tu.sofia.model.VerificationToken;

public interface VerificationTokenService {

    VerificationToken save(VerificationToken token);

    VerificationToken getVerificationToken(String VerificationToken);

    VerificationToken findByUser_Id(Integer id);

}
