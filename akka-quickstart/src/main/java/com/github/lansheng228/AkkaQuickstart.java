package com.github.lansheng228;

import akka.actor.typed.ActorSystem;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j

public class AkkaQuickstart {

    public static void main(String[] args) {
        final ActorSystem<GreeterMain.Start> greeterMain = ActorSystem.create(GreeterMain.create(), "helloakka");

        greeterMain.tell(new GreeterMain.Start("Charles"));

        try {
            log.info(">>> Press ENTER to Exit <<<");
            System.in.read();
        } catch (IOException ignored) {
            log.warn("something error");
        } finally {
            log.info(">>> Now Exit <<<");
            greeterMain.terminate();
        }
    }
}
