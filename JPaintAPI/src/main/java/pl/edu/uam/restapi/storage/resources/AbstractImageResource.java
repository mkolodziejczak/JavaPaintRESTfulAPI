package pl.edu.uam.restapi.storage.resources;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.edu.uam.restapi.storage.database.ImageDatabase;
import pl.edu.uam.restapi.storage.model.Image;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.Collection;

@RestController
public abstract class AbstractImageResource {

    protected abstract ImageDatabase getDatabase();


    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create image", notes = "Create image", response = Image.class)
    public ResponseEntity createImage(@RequestBody Image image, HttpServletRequest request) {
        Image dbImage = new Image(image.getByteArray());

        Image createdImage = getDatabase().createImage(dbImage);

        return ResponseEntity.created(URI.create(request.getPathInfo() + "/" )).body(createdImage);
    }

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ApiOperation(value = "Get image", notes = "Get image", response = Image.class)
    public Image getImage() {
        return getDatabase().getImage();
    }
}
