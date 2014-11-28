/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.ac.dundee.computing.aec.instagrim.stores;

/**
 *
 * @author Administrator
 */
public class ListStore {
    private String user = null;
    private boolean party = false;
    private String partyid = null;
    private java.util.LinkedList<String> items = new java.util.LinkedList<>();

    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * @return the items
     */
    public java.util.LinkedList<String> getItems() {
        return items;
    }
    
    

    /**
     * @param items the items to set
     */
    public void setItems(java.util.LinkedList<String> items) {
        this.items = items;
    }
    
    public void addItem(String item){
        this.items.add(item);
    }

    /**
     * @return the party
     */
    public boolean isParty() {
        return party;
    }

    /**
     * @param party the party to set
     */
    public void setParty(boolean party) {
        this.party = party;
    }

    /**
     * @return the partyid
     */
    public String getPartyid() {
        return partyid;
    }

    /**
     * @param partyid the partyid to set
     */
    public void setPartyid(String partyid) {
        this.partyid = partyid;
    }
    
    
    
}
