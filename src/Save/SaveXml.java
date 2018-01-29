/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Save;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 *
 * @author abanoub samy
 */
public class SaveXml {

    File file;

    /**
     *
     * @param allMessages
     * @param chatName
     * 
     * save chat messages of user and his friends
     * into xml file
     * @throws FileNotFoundException
     */
    public void save(ListMessages allMessages,String chatName) throws FileNotFoundException {

        try {
            JAXBContext jc = JAXBContext.newInstance(ListMessages.class);
            Marshaller ms = jc.createMarshaller();
            ms.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            ms.marshal(allMessages, System.out);
            ms.marshal(allMessages, new FileOutputStream("Messages.xml"));
        } catch (JAXBException ex) {
            Logger.getLogger(SaveXml.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            BufferedReader bufferedReader;
            PrintWriter printWriter;
            file = new File("Final.txt");
            bufferedReader = new BufferedReader(new FileReader("Messages.xml"));
            printWriter = new PrintWriter(file);
            String string;
            int i = 1;
            while ((string = bufferedReader.readLine()) != null) {
                printWriter.println(string);
                if (i == 1) {
                    printWriter.println("<?xml-stylesheet href =\"XSL.xsl\" type =\"text/xsl\"?>\n");
                    i++;
                }
            }
            System.out.println("Yes Copied");
            printWriter.close();
        } catch (Exception e) {
            System.out.println(e.getMessage().toString());
        }
        try {
            BufferedReader bufferedReader;
            PrintWriter printWriter;
            File fileFinal = new File("test/"+chatName+".xml");
            bufferedReader = new BufferedReader(new FileReader("Final.txt"));
            printWriter = new PrintWriter(fileFinal);
            String string;
            while ((string = bufferedReader.readLine()) != null) {
                printWriter.println(string);
            }
            System.out.println("Yes Copied");
            printWriter.close();
        } catch (Exception e) {
            System.out.println(e.getMessage().toString());
        }

    }

}
