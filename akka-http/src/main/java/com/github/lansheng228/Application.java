package com.github.lansheng228;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Application {

    public static void main(String[] args) throws Exception {
        ActorSystem system = ActorSystem.create("userServer");
        ActorRef userActor = system.actorOf(UserActor.props(), "userActor");
        UserServer server = new UserServer(userActor);
        String host = "0.0.0.0";
        int port = 9999;
        log.info("Server listen on {}:{}", host, port);
        server.startServer(host, port, system);
    }
}

