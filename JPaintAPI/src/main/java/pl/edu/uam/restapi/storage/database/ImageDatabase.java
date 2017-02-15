package pl.edu.uam.restapi.storage.database;

import pl.edu.uam.restapi.storage.model.Image;


import java.util.Collection;

public interface ImageDatabase {

    Image createImage(Image image);
    Image getImage();

}
