package akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

/**
 * @author linckye
 */
public class AkkaMain {

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("hello-akka");
        ActorRef master = system.actorOf(Props.create(Master.class), "master");
    }

}
