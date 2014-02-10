package com.ideais.spring.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class RootController
{

  @RequestMapping(method=RequestMethod.GET)
  public String index()
  {
    return "index";
  }

}