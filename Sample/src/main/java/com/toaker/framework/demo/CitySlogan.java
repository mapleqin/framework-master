package com.toaker.framework.demo;


import com.toaker.commons.db.annotation.Column;
import com.toaker.commons.db.annotation.NoAutoIncrement;
import com.toaker.commons.db.annotation.Table;

/**
 * Created by Daniel on 2014/12/19.
 */
@Table(name="slogan")
public class CitySlogan {

    @Column
    @NoAutoIncrement
    public int _id;

    @Column
    public String city;

    @Column
    public String slogan;

    public CitySlogan() {
    }
}
