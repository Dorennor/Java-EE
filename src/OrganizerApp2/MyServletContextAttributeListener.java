package OrganizerApp2;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;

public class MyServletContextAttributeListener implements ServletContextAttributeListener {

    public void attributeAdded(ServletContextAttributeEvent scab) {
        System.out.println("An attribute was added to the ServletContext object");
    }

    public void attributeRemoved(ServletContextAttributeEvent scab) {
        System.out.println("An attribute was removed from the ServletContext object");
    }
    public void attributeReplaced(ServletContextAttributeEvent scab) {
        System.out.println("An attribute was replaced in the ServletContext object");
    }

}
