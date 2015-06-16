package de.tivsource.page.install;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.tivsource.page.dao.administration.RoleDaoLocal;
import de.tivsource.page.dao.administration.UserDaoLocal;
import de.tivsource.page.entity.administration.Role;
import de.tivsource.page.entity.administration.User;

public class CreateUser {

    private UserDaoLocal userDaoLocal;

    private RoleDaoLocal roleDaoLocal;

    public void setUserDaoLocal(UserDaoLocal userDaoLocal) {
    	this.userDaoLocal = userDaoLocal;
    }

    public void setRoleDaoLocal(RoleDaoLocal roleDaoLocal) {
    	this.roleDaoLocal = roleDaoLocal;
    }

    public void generate() {
    	InputStream inputStream = CreateUser.class.getClassLoader().getResourceAsStream("csv/user.csv");
    	generate(inputStream);
    }// Ende generate()

    public void generate(InputStream inputStream) {
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String line = null;
            while ((line = in.readLine()) != null) {
                if (!line.startsWith("[Format Definition]")) {
                    User user = convert(line);
                    userDaoLocal.merge(user);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
    
    private User convert(String line) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        // uuid|username|email|firstname|lastname|password|roles|added|modified|ip|
    	// Zerlege CSV-Zeile in String-Array.
    	String[] items = line.split("\\|");

    	// Erstelle Announcement-Objekt
    	User user = new User();

    	user.setUuid(items[0]);
    	user.setUsername(items[1]);
    	user.setEmail(items[2]);
        user.setFirstname(items[3]);
        user.setLastname(items[4]);
        user.setPassword(items[5]);

        // Erzeuge Role-List.
        List<Role> roles = new ArrayList<Role>();

        // Zerlege CSV-String.
        String[] csvTags = items[6].split(";");

        // Laufe durch das Array und füge die Tags der Tag-Liste hinzu.
        for (int i = 0; i < csvTags.length; i++) {
            roles.add(roleDaoLocal.findByTechnical(csvTags[i]));
        }
        user.setRoles(roles);

    	// Füge Erstellungsdatum hinzu.
    	user.setCreated(convertDateString(items[7]));
    	user.setModified(convertDateString(items[8]));

    	// Setze IP auf localhost.
    	user.setIp(items[9]);

    	user.setModifiedBy(items[10]);

    	return user;
    }// Ende convert(String line)

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
    }

}// Ende class