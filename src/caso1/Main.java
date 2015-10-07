package caso1;

import caso1.actores.Espadachin;

public final class Main {

    private Main() {}

    public static void main(String[] args) {
        akka.Main.main(new String[]{Espadachin.class.getName()});
    }
}