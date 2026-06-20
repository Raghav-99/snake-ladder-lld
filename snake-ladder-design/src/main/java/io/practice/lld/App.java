package io.practice.lld;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import io.practice.lld.controller.GameController;
import io.practice.lld.service.GameState;
import io.practice.lld.service.SimpleGameService;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Properties properties = new Properties();
        try(FileInputStream input = new FileInputStream("./src/resources/config.xml")) {
            properties.loadFromXML(input);
            Game game = new Game(properties);
            GameState gameState = new GameState(game.getBoard(), game.getDie());
            GameController controller = new GameController(new SimpleGameService(gameState));
            controller.run(game.getPlayers(), game.getDie());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
