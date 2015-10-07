package caso3;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import caso3.actores.Espadachin;
import caso3.actores.Herrero;
import caso3.actores.Minero;

public final class Main {

    public static ActorRef espadachin1;
    public static ActorRef espadachin2;
    public static ActorRef herrero;
    public static ActorRef minero;
    public static ActorSystem actorSystem;

    private static final long TIEMPO_ESPERA = 1000;

    private Main() {}

    public static void main(String[] args) throws InterruptedException {
        actorSystem = ActorSystem.create("ActorSystem");
        espadachin1 = actorSystem.actorOf(Props.create(Espadachin.class), "espadachin1");
        espadachin2 = actorSystem.actorOf(Props.create(Espadachin.class), "espadachin2");
        herrero = actorSystem.actorOf(Props.create(Herrero.class), "herrero");
        minero = actorSystem.actorOf(Props.create(Minero.class), "minero");

        espadachin1.tell(Espadachin.Mensaje.ESPADA_ROTA, ActorRef.noSender());
        Thread.sleep(TIEMPO_ESPERA);
        espadachin2.tell(Espadachin.Mensaje.ESPADA_ROTA, ActorRef.noSender());
    }
}