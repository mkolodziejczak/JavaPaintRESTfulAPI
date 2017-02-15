package pl.edu.uam.restapi.displayImage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class DisplayImageController {

    @RequestMapping("/show")
    public ModelAndView helloWorld() {

        return new ModelAndView("show");
    }
}