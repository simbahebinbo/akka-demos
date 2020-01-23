package com.lab;

import java.io.IOException;

import akka.actor.typed.ActorSystem;
import com.lab.GreeterMain.Start;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AkkaQuickstart {

  public static void main(String[] args) {
    final ActorSystem<Start> greeterMain = ActorSystem.create(GreeterMain.create(), "helloakka");

    greeterMain.tell(new GreeterMain.Start("Charles"));

    try {
      System.out.println(">>> Press ENTER to exit <<<");
      System.in.read();
    } catch (IOException ignored) {
    } finally {
      greeterMain.terminate();
    }
  }
}
