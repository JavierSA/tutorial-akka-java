package caso2;

import caso2.actores.Espadachin;

public final class Main {

    private Main() {}

    public static void main(String[] args) {
        akka.Main.main(new String[]{Espadachin.class.getName()});
    }
}