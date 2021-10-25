package com.github.martinfrank.data.service;

import com.github.martinfrank.data.entity.QuestItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;

@Service
public class QuestItemService extends CrudService<QuestItem, Integer> {

    private QuestItemRepository questItemRepository;

    public QuestItemService(@Autowired QuestItemRepository questItemRepository) {
        this.questItemRepository = questItemRepository;
    }

    @Override
    protected QuestItemRepository getRepository() {
        return questItemRepository;
    }

}
