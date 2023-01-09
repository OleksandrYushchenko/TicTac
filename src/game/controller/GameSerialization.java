package game.controller;

import java.io.*;

public class GameSerialization implements Persistence {
    @Override
    public void create (Game game) {
        try{
            String name = game.getClass().getName();
            //Creating stream and writing the object
            FileOutputStream fout=new FileOutputStream("Serialize/" + name + ".ser");
            ObjectOutputStream out=new ObjectOutputStream(fout);
            out.writeObject(game);
            out.flush();
            //closing the stream
            out.close();
            System.out.println("game was saved");
        }catch(Exception e){System.out.println(e);}
    }
    @Override
    public Game read (Class gameClass){
        Game game = null;
        try {
            String name = gameClass.getName();
            FileInputStream fileIn = new FileInputStream("Serialize/"+name+".ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            if(gameClass == TicTacToe.class) game = (TicTacToe) in.readObject();
            else if(gameClass == Gomoku.class) game = (Gomoku) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            System.out.println("There is no previous game saved");
        } catch (ClassNotFoundException c) {
            c.printStackTrace();
        }
        return game;
    }
}
