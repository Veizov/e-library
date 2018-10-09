package bg.tu.sofia.service;

import bg.tu.sofia.model.FeedbackMessage;

public interface FeedbackMessageService {

    FeedbackMessage save(FeedbackMessage book);

    void delete(FeedbackMessage book);
}
