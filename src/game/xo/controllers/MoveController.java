package game.xo.controllers;

import game.xo.model.Field;
import game.xo.model.Figure;
import game.xo.model.exceptions.InvalidPointException;
import game.xo.model.exceptions.PointAlreadyOccupiedException;

import java.awt.*;

public class MoveController {

    public void applyFigure(final Field field,
                            final Point point,
                            final Figure figure) throws InvalidPointException,
                                                        PointAlreadyOccupiedException {

        if (field.getFigure(point) != null){
            throw new PointAlreadyOccupiedException();
        }

        field.setFigure(point, figure);

    }

}
