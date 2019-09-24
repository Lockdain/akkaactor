package http.actor;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.japi.pf.FI;
import http.actor.mail.UserMessages;
import http.actor.service.UserService;

public class UserActor extends AbstractActor {

    private UserService userService = new UserService();

    public static Props props() {
        return Props.create(UserActor.class);
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(UserMessages.CreateUserMessage.class, handleCreateUser())
                .match(UserMessages.GetUserMessage.class, handleGetUser())
                .build();
    }

    private FI.UnitApply<UserMessages.CreateUserMessage> handleCreateUser() {
        return createUserMessage -> {
            userService.createUser(createUserMessage.getUser());
            sender().tell(new UserMessages.ActionPerformed(String.format("User %s created.", createUserMessage.getUser()
            .getName())), getSelf());
        };
    }

    private FI.UnitApply<UserMessages.GetUserMessage> handleGetUser() {
        return getUserMessage -> {
            sender().tell(userService.getUser(getUserMessage.getUserId()), getSelf());
        };
    }
}
