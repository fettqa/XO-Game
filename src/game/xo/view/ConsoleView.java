package game.xo.view;

import game.xo.controllers.CurrentMoveController;
import game.xo.controllers.MoveController;
import game.xo.controllers.WinnerController;
import game.xo.model.Field;
import game.xo.model.Figure;
import game.xo.model.Game;
import game.xo.model.exceptions.InvalidPointException;
import game.xo.model.exceptions.PointAlreadyOccupiedException;

import java.awt.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleView {

    private final CurrentMoveController currentMoveController = new CurrentMoveController();
    private final WinnerController winnerController = new WinnerController();
    private final MoveController moveController = new MoveController();

    public void show(final Game game) {
        System.out.format("Game name: %s", game.getName());
        System.out.println();
        final Field field = game.getField();
        for (int x = 0; x < field.getSize(); x++) {
            printLine(field, x);
        }
        printSeparator();
    }

    public boolean move(final Game game) {
        final Field field = game.getField();
        final Figure currentFigure = currentMoveController.currentMove(field);
        final Figure winner = winnerController.getWinner(field);
        if (winner != null) {
            System.out.format("Winner is: %s\n", winner);
            return false;
        }
        if (currentFigure == null) {
            if (winner == null) {
                System.out.println("No winner and no moves left!");
            } else {
                System.out.format("Winner is: %s\n", winner);
            }
            return false;
        }
        System.out.format("Please enter move point for %s\n", currentFigure);
        final Point point = askPoint();
        try {
            moveController.applyFigure(field, point, currentFigure);
        } catch (InvalidPointException | PointAlreadyOccupiedException e) {
                //e.printStackTrace();
                System.out.println("Invalid point");
        }
        return true;

    }


    private Point askPoint() {
        return new Point(askCoordinate("X") - 1, askCoordinate("Y") - 1);
    }

    private int askCoordinate(final String coordinateName) {
        System.out.format("Please input %s: ", coordinateName);
        final Scanner in = new Scanner(System.in);
        try {
            return in.nextInt();}
        catch (final InputMismatchException e) {
            System.out.println("Enter INT");
            return askCoordinate(coordinateName);
        }
    }

    private void printLine(final Field field,
                           int x) {
        printSeparator();
        System.out.print("|");
        for (int y = 0; y < field.getSize(); y++) {
            final Figure figure;
            try {
                figure = field.getFigure(new Point(y,x));
            } catch (InvalidPointException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
            System.out.print(figure != null ? figure : " ");
            System.out.print("|");
        }
        System.out.println();
    }

    private void printSeparator() {
        System.out.println("~~~~~~~");
    }
}

