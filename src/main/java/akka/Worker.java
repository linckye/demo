package akka;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

/**
 * @author linckye
 */
public class Worker extends AbstractActor {

    public void onMessage(TaskMessage taskMessage) {
        int sum = 0;
        for (int i = taskMessage.from; i <= taskMessage.to; i++) {
            sum += i;
        }
        System.out.println("Worker:" + sum);
        getContext().getSender().tell(
                new TaskFinishMessage()
                        .setSum(sum),
                getSelf()
        );
    }

    public Receive createReceive() {
        return new ReceiveBuilder()
                .match(TaskMessage.class, this::onMessage)
                .build();
    }

}
