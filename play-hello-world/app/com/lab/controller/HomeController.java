package com.lab.controller;

import play.mvc.Controller;
import play.mvc.Result;

public class HomeController extends Controller {

  public Result index() {
    return ok("Hello World!");
  }
}
