package com.example.thsk.hareskovskole.messages;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.thsk.hareskovskole.utils.data.Message;

import java.io.Serializable;
import java.util.List;

/**
 * Created by thsk on 04/08/2017.
 */

public class ChatObject  implements Serializable   {

    private String enviromentName;
    private String groupName;
    private String logo;
    private List<Message> chat;


    public ChatObject(String enviromentName, List<Message> chat) {
        this.enviromentName = enviromentName;
        this.chat = chat;
    }

    public ChatObject(String enviromentName, String groupName, List<Message> chat) {
        this.enviromentName = enviromentName;
        this.groupName = groupName;
        this.chat = chat;
    }

    public ChatObject(String enviromentName, String groupName, List<Message> chat, String logo) {
        this.enviromentName = enviromentName;
        this.groupName = groupName;
        this.chat = chat;
        this.logo = logo;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getEnviromentName() {
        return enviromentName;
    }

    public void setEnviromentName(String enviromentName) {
        this.enviromentName = enviromentName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<Message> getChat() {
        return chat;
    }

    public void setChat(List<Message> chat) {
        this.chat = chat;
    }
}
