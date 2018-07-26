
import flooringmastery.controller.FlooringMasteryController;
import flooringmastery.dao.FloodingMasteryInvalidStateException;
import flooringmastery.dao.FlooringMasteryInvalidConfigException;
import flooringmastery.dao.FlooringMasteryInvalidProductException;
import flooringmastery.dao.FlooringMasteryOrderDoesNotExistException;
import flooringmastery.dao.FlooringMasteryPersistenceException;
import java.io.IOException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Y
 */
public class App {

    public static void main(String[] args) throws Exception {

//        UserIO myIo = new UserIOConsoleImpl();
//        FlooringMasteryView myView = new FlooringMasteryView(myIo);
//        FlooringMasteryDao myDao = new FlooringMasteryDaoImpl();
//        FlooringMasteryService service = new FlooringMasteryServiceImpl(myDao);
//        FlooringMasteryController controller = new FlooringMasteryController(service, myView);
//        controller.run();
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        FlooringMasteryController flooringMasteryController = ctx.getBean("controller", FlooringMasteryController.class);
        flooringMasteryController.run();
    }
}
