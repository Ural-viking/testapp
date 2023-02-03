package com.example.testapp.service;

import com.example.testapp.entity.Action;
import com.example.testapp.repository.ActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.GregorianCalendar;

@Service
public class ActionServiceImpl implements ActionService {
    private ActionRepository actionRepository;

    @Autowired
    public ActionServiceImpl(ActionRepository actionRepository) {
        this.actionRepository = actionRepository;
    }

    @Override
    public void saveLogOfAction(String state) {
        Calendar calendar = new GregorianCalendar();
        Action action = new Action();
        action.setDataOfAction(calendar.getTime().toString());
        action.setDescriptionOfAction(state);
        actionRepository.save(action);
    }
}