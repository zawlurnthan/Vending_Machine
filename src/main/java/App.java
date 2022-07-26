import controller.VMController;
import dao.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class App {
    public static void main(String[] args) throws VMPersistenceException {
        // update to spring dependency Injection XML configuration from constructor injection
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        VMController controller = ctx.getBean("controller", VMController.class);
        controller.run();
    }
}
