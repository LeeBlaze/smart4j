package org.smart4j.service;

import org.smart4j.annotation.Service;
import org.smart4j.annotation.Transaction;

/**
 * Create by Lee on 2017/12/18
 */


/**
 *  test for Transaction annotation
 */
@Service
public class CustomService {


    @Transaction
    public void insertMethod(){

    }


    @Transaction
    public void updateMethod(){

    }

    @Transaction
    public void deleteMethod(){

    }
}
