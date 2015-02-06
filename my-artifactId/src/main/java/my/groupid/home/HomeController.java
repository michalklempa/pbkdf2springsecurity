package my.groupid.home;

import java.security.Principal;
import my.groupid.account.Account;
import my.groupid.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
  @Autowired
  private AccountRepository accountRepository;

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String index(Model model, Principal principal) {
    if (principal != null) {
      Account account = accountRepository.findByEmail(principal.getName());
      model.addAttribute("passwordhash", account.getPassword());
    }
    return principal != null ? "home/homeSignedIn" : "home/homeNotSignedIn";
  }
}