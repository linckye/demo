package akka;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.SupervisorStrategy;
import akka.japi.pf.ReceiveBuilder;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author linckye
 */
public class Master extends AbstractActor {

    private AtomicInteger sum = new AtomicInteger();
    private int parallelism = 8;

    @Override
    public void preStart() throws Exception {
        for (int i = 0; i < parallelism; i++) {
            ActorRef worker = getContext().actorOf(Props.create(Worker.class), "work-" + i);
            worker.tell(new TaskMessage()
                    .setFrom(1)
                    .setTo(2)
                    , getSelf());
        }
    }

    @Override
    public SupervisorStrategy supervisorStrategy() {
        return super.supervisorStrategy();
    }

    public void onMessage(TaskFinishMessage taskFinishMessage) {
        sum.addAndGet(taskFinishMessage.sum);
        System.out.println("Master:" + sum);
    }

    public Receive createReceive() {
        return new ReceiveBuilder()
                .match(TaskFinishMessage.class, this::onMessage)
                .build();
    }

}
