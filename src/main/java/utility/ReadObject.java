package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
public class ReadObject {
    Properties properties = new Properties();
    public Properties getObjectRepository() throws IOException{
        //Read object repository file

        InputStream stream = new FileInputStream(new File("src/properties/object.properties"));
        //load all objects
        properties.load(stream);
        return properties;
    }

}
