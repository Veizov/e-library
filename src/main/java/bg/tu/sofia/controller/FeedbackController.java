package bg.tu.sofia.controller;

import bg.tu.sofia.model.FeedbackMessage;
import bg.tu.sofia.service.FeedbackMessageService;
import bg.tu.sofia.validator.FeedbackMessageValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/feedback")
public class FeedbackController {
  private static Logger LOG = LoggerFactory.getLogger(FeedbackController.class);

  @Autowired
  private FeedbackMessageValidator feedbackMessageValidator;

  @Autowired
  private FeedbackMessageService feedbackMessageService;

  @RequestMapping(method = RequestMethod.GET)
  public String feedback() {
    return "public/feedback";
  }

  @RequestMapping(method = RequestMethod.POST)
  public String sendMessage(RedirectAttributes redirectAttributes,
                            @ModelAttribute FeedbackMessage feedbackMessage,
                            BindingResult bindingResult) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    feedbackMessage.setUserEmail(auth.getName());

    feedbackMessageValidator.validate(feedbackMessage, bindingResult);
    if (bindingResult.hasErrors()) {
      redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.feedbackMessage", bindingResult);
      redirectAttributes.addFlashAttribute("feedbackMessage", feedbackMessage);
    } else {
      feedbackMessageService.save(feedbackMessage);
      redirectAttributes.addFlashAttribute("sendFeedbackSuccessful", true);
    }
    return "redirect:/feedback";
  }


}
