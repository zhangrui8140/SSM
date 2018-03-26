package com.Spring.Test.bean.Collection;

import java.util.*;

public class Customer {
    private List<Object> lists;
    private Set<Object> sets;
    private Map<Object,Object> maps;
    private Properties props;

    public List<Object> getLists() {
        return lists;
    }

    public void setLists(List<Object> lists) {
        this.lists = lists;
    }

    public Set<Object> getSets() {
        return sets;
    }

    public void setSets(Set<Object> sets) {
        this.sets = sets;
    }

    public Map<Object, Object> getMaps() {
        return maps;
    }

    public void setMaps(Map<Object, Object> maps) {
        this.maps = maps;
    }

    public Properties getProps() {
        return props;
    }

    public void setProps(Properties props) {
        this.props = props;
    }

    private Person person;

    public void setPerson(Person person) {
        this.person = person;
    }

    public Customer(Person person){
        this.person=person;
    }

    public Customer(){}

    @Override
    public String toString(){
         return "Person"+person;
    }
}
