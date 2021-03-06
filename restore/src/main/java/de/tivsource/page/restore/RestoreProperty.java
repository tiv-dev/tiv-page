/**
 * 
 */
package de.tivsource.page.restore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.tivsource.page.dao.property.PropertyDaoLocal;
import de.tivsource.page.entity.property.Property;

/**
 * @author Marc Michele
 *
 */
public class RestoreProperty {

    /**
     * Statischer Logger der Klasse.
     */
    private static final Logger LOGGER = LogManager.getLogger(RestoreProperty.class);

    private PropertyDaoLocal propertyDaoLocal;

    public RestoreProperty(PropertyDaoLocal propertyDaoLocal) {
        super();
        this.propertyDaoLocal = propertyDaoLocal;
    }

    public void generate(InputStream inputStream) {
        LOGGER.info("generate(InputStream inputStream) aufgerufen.");
        cleanup();
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String line = null;
            while ((line = in.readLine()) != null) {
                if (!line.startsWith("[Format Definition]")) {
                    Property property = convert(line);
                    propertyDaoLocal.merge(property);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }// Ende generate()

    private Property convert(String line) {
        // Zerlege CSV-Zeile in String-Array.
        String[] items = line.split("\\|");

        // key|value|modified|modifiedBy|modifiedAddress|
        Property property = new Property();
        property.setKey(items[0]);
        property.setValue(items[1]);
        property.setModified(convertDateString(items[2]));
        property.setModifiedBy(items[3]);
        property.setModifiedAddress(items[4]);

        return property;
    }

    /**
     * Methode zum Konvertieren eines Strings des Formates "1970-12-01 23:59:59" in ein Date-Object. 
     * @param dateString
     * @return
     */
    private Date convertDateString(String dateString) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return simpleDateFormat.parse(dateString);
        } catch (ParseException e) {
            return new Date();
        }
    }// Ende convertDateString(String dateString)

    private void cleanup() {
        if(propertyDaoLocal.countAll() > 0) {
            Iterator<Property> propertyIterator = propertyDaoLocal.findAll(0, propertyDaoLocal.countAll()).iterator();
            while(propertyIterator.hasNext()) {
                Property next = propertyIterator.next();
                propertyDaoLocal.delete(next);
            }
        }
    }// Ende cleanup()
    
}// Ende class
