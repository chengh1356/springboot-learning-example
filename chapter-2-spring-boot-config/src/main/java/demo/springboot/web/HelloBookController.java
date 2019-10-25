package demo.springboot.web;

import demo.springboot.config.BookComponent;
import demo.springboot.config.BookProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Spring Boot Hello案例
 * <p>
 * 先找yml结尾的配置文件，后续如果有properties结尾的配置文件，会覆盖前者的
 */
@RestController
public class HelloBookController {

    @Autowired
    BookProperties bookProperties;
    @Autowired
    BookComponent bookComponent;

    @GetMapping("/book/hello")
    public String sayHello() {
        return "Hello， " + bookProperties.getWriter() + " is writing "
                + bookProperties.getName() + " ！";
    }

    @GetMapping("/book/hello2")
    public String sayHello2() {
        return "Hello2， " + bookComponent.getWriter() + " is writing "
                + bookComponent.getName() + " ！";
    }
}
