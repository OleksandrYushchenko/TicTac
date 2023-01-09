package game.controller;

public interface Persistence {
    void create(Game game);
    Game read (Class gameClass);
}
