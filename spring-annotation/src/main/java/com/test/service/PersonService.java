package com.test.service;

import com.test.bean.Person;
import com.test.dao.PersonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.inject.Inject;

@Service
public class PersonService {

//    @Qualifier("personDao")
//    @Autowired(required=false)

//    @Resource(name="personDao2")

    @Inject
    private PersonDao personDao;

    @Transactional
    public void insert() {
        personDao.insert();
        int i = 1 / 0;
    }

}
