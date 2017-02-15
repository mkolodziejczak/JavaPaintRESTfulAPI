package pl.edu.uam.restapi.storage.database;

import com.google.common.collect.Lists;
import pl.edu.uam.restapi.storage.entity.ImageEntity;
import pl.edu.uam.restapi.storage.model.Image;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.security.MessageDigest;
import java.util.*;

public class MysqlDB implements  ImageDatabase {

    private static final String HOST = "HOST_ADDRESS";
    private static final int PORT = 3306;
    private static final String DATABASE = "DB_NAME";
    private static final String USER_NAME = "DB_USER_NAME";
    private static final String PASSWORD = "DB_PASSWORD";

    private static EntityManager entityManager;

    public static EntityManager getEntityManager() {
        if (entityManager == null) {
            String dbUrl = "jdbc:mysql://" + HOST + ':' + PORT + "/" + DATABASE;

            Map<String, String> properties = new HashMap<String, String>();

            properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
            properties.put("javax.persistence.jdbc.driver", "com.mysql.jdbc.Driver");
            properties.put("hibernate.connection.url", dbUrl);
            properties.put("hibernate.connection.username", USER_NAME);
            properties.put("hibernate.connection.password", PASSWORD);
            properties.put("hibernate.show_sql", "true");
            properties.put("hibernate.format_sql", "true");

            properties.put("hibernate.temp.use_jdbc_metadata_defaults", "false");
            properties.put("hibernate.hbm2ddl.auto", "update");

            EntityManagerFactory emf = Persistence.createEntityManagerFactory("myUnit", properties);
            entityManager = emf.createEntityManager();
        }

        return entityManager;
    }

    @Override
    public Image createImage(final Image image) {
        ImageEntity entity = buildImageEntity(image);

        try {
            getEntityManager().getTransaction().begin();

            getEntityManager().persist(entity);

            getEntityManager().getTransaction().commit();
        } finally {
            if (getEntityManager().getTransaction().isActive()) {
                getEntityManager().getTransaction().rollback();
            }
        }

        return new Image(image.getId(), image.getByteArray(), image.getImageName(), image.getCreatedAt());
    }

    @Override
    public Image getImage() {
        Query query = getEntityManager().createNamedQuery("images.getImage");
        ImageEntity imageEntity = (ImageEntity) query.getSingleResult();



        Image image = buildImageResponse(imageEntity);

        return image;
    }


    private ImageEntity buildImageEntity(Image image) {
        return new ImageEntity(image.getId(), image.getByteArray(), image.getImageName(), image.getCreatedAt());
    }

    private Image buildImageResponse(ImageEntity imageEntity) {
        return new Image(imageEntity.getId(), imageEntity.getByteArray(), imageEntity.getImageName(), imageEntity.getCreatedAt());
    }
}
