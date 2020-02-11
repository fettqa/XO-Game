package game.xo;

import game.xo.model.Field;
import game.xo.model.Figure;
import game.xo.model.Game;
import game.xo.model.Player;
import game.xo.view.ConsoleView;

public class XOCLI {

    public static void main(String[] args) {
        final String name1 = "Sanya";
        final String name2 = "Petya";

        final Player[] players = new Player[2];
        players[0] = new Player(name1, Figure.X);
        players[1] = new Player(name2, Figure.O);

        final Game gameXO = new Game(players, new Field(3), "XO");

        final ConsoleView consoleView = new ConsoleView();
        consoleView.show(gameXO);
        while (consoleView.move(gameXO)) {
            consoleView.show(gameXO);
        }
    }
}
