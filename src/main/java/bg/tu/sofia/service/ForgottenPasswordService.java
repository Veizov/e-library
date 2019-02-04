package bg.tu.sofia.service;

import bg.tu.sofia.model.ForgottenPassword;

public interface ForgottenPasswordService {

    ForgottenPassword save(ForgottenPassword forgottenPassword);

    ForgottenPassword findByToken(String token);

    ForgottenPassword findByEmail(String email);

}
