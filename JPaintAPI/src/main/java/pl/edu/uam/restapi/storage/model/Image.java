package pl.edu.uam.restapi.storage.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@ApiModel(value = "User")
public class Image {
    private Integer id;
    private String byteArray;
    private String imageName;
    private String createdAt;

    public Image(Integer id, String byteArray, String imageName, String createdAt) {

        this.id = id;
        this.byteArray = byteArray;
        this.imageName = imageName;
        this.createdAt = createdAt;

    }

    public Image(){}

    public Image(String byteArray) {

        this.byteArray = byteArray;

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        this.createdAt = dateFormat.format(date);

        byte[] firstPhoto = Base64.decodeBase64(byteArray);
        OutputStream stream = null;
        String fileName=(System.currentTimeMillis() / 1000L)+"";

        this.imageName = fileName +".bmp";
        try {
            File dir = new File("images");
            dir.mkdirs();

            for(File file: dir.listFiles())
                if (!file.isDirectory())
                    file.delete();

            stream = new FileOutputStream(("images/"+fileName +".bmp"));
            stream.write(firstPhoto);
            stream.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    @ApiModelProperty(value = "Image id", required = true)
    public Integer getId() {
        return id;
    }

    @ApiModelProperty(value = "Byte array of the image", required = true)
    public String getByteArray() {
        return byteArray;
    }

    @ApiModelProperty(value = "Name of the image", required = true)
    public String getImageName() {
        return imageName;
    }

    @ApiModelProperty(value = "Date of creation of the image", required = true)
    public String getCreatedAt() {
        return createdAt;
    }

}
