package util;

import java.io.InputStream;
import java.util.Properties;
import java.util.Random;

public class CommonMeth {
    public String getRandomName(){
        String resourceName = "testData/nameList.properties"; // could also be a constant
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties props = new Properties();
        InputStream resourceStream = null;

        try {
            resourceStream = loader.getResourceAsStream(resourceName);
            props.load(resourceStream);
        } catch (Exception e) {
            System.out.println("Exception opening the properties file: " + e);
        }

        String[] names = props.getProperty("name").split(",",-1);

        if (null != resourceStream) {
            try {
                resourceStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return names[new Random().nextInt(names.length)];
    }
}
