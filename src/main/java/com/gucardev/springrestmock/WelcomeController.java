package com.gucardev.springrestmock;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class WelcomeController {

  private List<MockData> mockDataList = new ArrayList<>();

  @GetMapping(value = "/")
  public String showWelcomePage() {
    return "index";
  }
}
