package com.github.lansheng228.remote;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class RemoteApplication {

    public static void main(String[] args) {
        //初始化远程角色
        ActorSystem remoteActorSystem = ActorSystem.create("RemoteApp");
        ActorRef remoteActorRef = remoteActorSystem.actorOf(Props.create(RemoteActor.class), "RemoteActor");
    }
}
