package pl.edu.uam.restapi.storage.entity;

import com.google.common.base.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PostLoad;
import javax.persistence.Table;

@Entity
@Table(name = "images")
@NamedQueries({
        @NamedQuery(name = "images.getImage", query = "SELECT i FROM ImageEntity i WHERE i.id = (SELECT MAX( u.id) FROM ImageEntity u)")
})
public class ImageEntity {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "byte_array")
    private String byteArray;

    @Column(name = "image_name")
    private String imageName;

    @Column(name="created_at")
    private String createdAt;



    //Lifecycle methods -- Pre/PostLoad, Pre/PostPersist...
    @PostLoad
    private void postLoad() {

    }

    public ImageEntity() {
    }

    public ImageEntity(Integer id, String byteArray, String imageName, String createdAt) {
        this.id = id;
        this.byteArray = byteArray;
        this.imageName = imageName;
        this.createdAt = createdAt;

    }

    public Integer getId() {
        return id;
    }

    public String getByteArray() {
        return byteArray;
    }

    public String getImageName() {
        return imageName;
    }

    public String getCreatedAt() {
        return createdAt;
    }



    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("byteArray", byteArray)
                .add("imageName", imageName)
                .add("createdAt", createdAt)
                .toString();
    }
}
