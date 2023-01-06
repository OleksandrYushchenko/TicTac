package game.controller;

import java.io.*;

public interface Persistence extends Serializable {
    void create(Game game);
    Game read (Class gameClass);
}
