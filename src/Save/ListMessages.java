/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Save;

import databaseclasses.Message;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author abanoub samy
 */

@XmlRootElement(name="MyMessages")
public class ListMessages {
    
private ArrayList<Message> messageList = new ArrayList<>();

    /**
     * @return the messageList
     */

@XmlElement(name="message")
    public ArrayList<Message> getMessageList() {
        return messageList;
    }

    /**
     * @param messageList the messageList to set
     */
    public void setMessageList(ArrayList<Message> messageList) {
        this.messageList = messageList;
    }

    /**
     *
     * @param m
     */
    public void add(Message m) {
       
       messageList.add(m);
    }




}
