package actors;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import mail.Message;

public class Main {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("test-system");
        ActorRef actorRef = system.actorOf(Props.create(MyActor.class), "my-actor");
//        ActorRef readingActorRef = system.actorOf(actors.ReadingActor.props("text"), "reading-actor");
        ActorRef wordCounterRef = system.actorOf(WordCounterActor.props("text"), "word-counter-actor");
        wordCounterRef.tell("printit", ActorRef.noSender());
        wordCounterRef.tell(new Message("test message from actor"), ActorRef.noSender());
    }
}
