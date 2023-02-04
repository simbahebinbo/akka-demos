package com.github.lansheng228.local;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.github.lansheng228.common.enums.MessageModeEnum;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LocalApplication {

    public static void main(String[] args) {
        ActorSystem localActorSystem = ActorSystem.create("LocalApp");
        //初始化本地角色
        ActorRef localActorRef = localActorSystem.actorOf(Props.create(LocalActor.class), "LocalActor");
        //给本地角色发送消息, 本地角色根据消息类型做出相应处理
        localActorRef.tell(MessageModeEnum.MESSAGE_MODE_TO_LOCAL, ActorRef.noSender());
        localActorRef.tell(MessageModeEnum.MESSAGE_MODE_TO_REMOTE, ActorRef.noSender());
    }
}
