package com.github.lansheng228;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

public class Application {

    public static void main(String[] args) throws Exception {
        ActorSystem system = ActorSystem.create("userServer");
        ActorRef userActor = system.actorOf(UserActor.props(), "userActor");
        UserServer server = new UserServer(userActor);
        server.startServer("0.0.0.0", 8080, system);
    }
}
