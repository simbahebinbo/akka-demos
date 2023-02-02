package com.github.lansheng228;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.japi.pf.FI;
import com.github.lansheng228.UserMessages.ActionPerformed;
import com.github.lansheng228.UserMessages.CreateUserMessage;
import com.github.lansheng228.UserMessages.GetUserMessage;


class UserActor extends AbstractActor {

    private final UserService userService = new UserService();

    static Props props() {
        return Props.create(UserActor.class);
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(CreateUserMessage.class, handleCreateUser())
                .match(GetUserMessage.class, handleGetUser())
                .build();
    }

    private FI.UnitApply<CreateUserMessage> handleCreateUser() {
        return createUserMessageMessage -> {
            userService.createUser(createUserMessageMessage.getUser());
            sender().tell(new ActionPerformed(String.format("User %s created.", createUserMessageMessage.getUser()
                    .getName())), getSelf());
        };
    }

    private FI.UnitApply<GetUserMessage> handleGetUser() {
        return getUserMessageMessage -> sender().tell(userService.getUser(getUserMessageMessage.getUserId()), getSelf());
    }

}
