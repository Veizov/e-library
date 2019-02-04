package bg.tu.sofia.repository;

import bg.tu.sofia.model.ForgottenPassword;

public interface ForgottenPasswordRepository extends BaseRepository<ForgottenPassword, Integer> {

    ForgottenPassword findByEmail(String email);

    ForgottenPassword findByToken(String token);

}
