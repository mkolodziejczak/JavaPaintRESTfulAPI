package pl.edu.uam.restapi.storage.resources;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.uam.restapi.storage.database.ImageDatabase;
import pl.edu.uam.restapi.storage.database.MysqlDB;


@RestController
@RequestMapping("/mysql/images")
@Api(value = "/mysql/images", description = "Operations about images using mysql")
@Component
public class MysqlImagesResource extends AbstractImageResource {

    private static final ImageDatabase database = new MysqlDB();

    @Override
    protected ImageDatabase getDatabase() {
        return database;
    }

}
