package com.github.lansheng228.remote;

import akka.actor.AbstractActor;
import com.github.lansheng228.common.enums.MessageContentEnum;
import com.github.lansheng228.common.toolkit.DateTool;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RemoteActor extends AbstractActor {

    @Override
    public Receive createReceive() {
        //远程角色收到信息,根据信息类型做出相应处理
        return receiveBuilder()
                .matchEquals(MessageContentEnum.MESSAGE_CONTENT_PING, msg -> {
                    log.info("From LocalApp: " + msg.getValue());
                    MessageContentEnum result = MessageContentEnum.MESSAGE_CONTENT_PONG;
                    this.getSender().tell(result, this.getSelf());
                })
                .matchEquals(MessageContentEnum.MESSAGE_CONTENT_DATE, msg -> {
                    log.info("From LocalApp: " + msg.getValue());
                    String result = DateTool.getNow();
                    this.getSender().tell(result, this.getSelf());
                })
                .matchAny(msg -> log.info(msg.toString())).build();
    }
}

