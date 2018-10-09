package bg.tu.sofia.service.impl;

import bg.tu.sofia.model.FeedbackMessage;
import bg.tu.sofia.model.Language;
import bg.tu.sofia.repository.FeedbackMessageRepository;
import bg.tu.sofia.repository.LanguageRepository;
import bg.tu.sofia.service.FeedbackMessageService;
import bg.tu.sofia.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class FeedbackMessageServiceImpl implements FeedbackMessageService {

  @Autowired
  private FeedbackMessageRepository feedbackMessageRepository;

  @Override
  public FeedbackMessage save(FeedbackMessage feedbackMessage) {
    if (Objects.isNull(feedbackMessage))
      return null;

    return feedbackMessageRepository.save(feedbackMessage);
  }

  @Override
  public void delete(FeedbackMessage feedbackMessage) {
    if (Objects.nonNull(feedbackMessage))
      feedbackMessageRepository.delete(feedbackMessage);
  }
}
