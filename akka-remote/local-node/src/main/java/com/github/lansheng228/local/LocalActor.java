package com.github.lansheng228.local;


import akka.actor.AbstractActor;
import akka.actor.ActorSelection;
import akka.pattern.Patterns;
import akka.util.Timeout;
import com.github.lansheng228.common.enums.MessageContentEnum;
import com.github.lansheng228.common.enums.MessageModeEnum;
import lombok.extern.slf4j.Slf4j;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.FiniteDuration;

import java.util.concurrent.TimeUnit;


@Slf4j
public class LocalActor extends AbstractActor {
    Timeout timeout = new Timeout(FiniteDuration.apply(5, TimeUnit.SECONDS));

    ActorSelection remoteActorSelection;

    @Override
    public void preStart() {
        remoteActorSelection = getContext().actorSelection(
                "akka://RemoteApp@127.0.0.1:2554/user/RemoteActor");
    }

    private void doProcessMessage(MessageModeEnum msgMode) {
        switch (msgMode) {
            case MESSAGE_MODE_TO_LOCAL -> {
                log.info("to local message");
                //向远程角色发送一条询问信息,等待回复
                try {
                    Future<Object> future = Patterns.ask(remoteActorSelection, MessageContentEnum.MESSAGE_CONTENT_DATE, timeout);
                    String result = Await.result(future, timeout.duration()).toString();
                    log.info("Message received from Server -> {}", result);
                } catch (Exception e) {
                    log.warn("Message can not receive from Server. Exception: {}", e.getMessage());
                }
            }
            case MESSAGE_MODE_TO_REMOTE -> {
                //向远程角色发送一条信息,不等待回复
                log.info("to remote message");
                remoteActorSelection.tell(MessageContentEnum.MESSAGE_CONTENT_PING, this.getSelf());
            }
        }
    }

    @Override
    public Receive createReceive() {
        //本地角色收到信息,根据信息类型做出相应处理
        return receiveBuilder()
                .match(MessageModeEnum.class, this::doProcessMessage)
                .match(MessageContentEnum.class, msg -> log.info("From RemoteApp: " + msg.getValue()))
                .match(String.class, msg -> log.info("message: " + msg))
                .matchAny(msg -> log.info("message: " + msg.toString())).build();
    }
}
