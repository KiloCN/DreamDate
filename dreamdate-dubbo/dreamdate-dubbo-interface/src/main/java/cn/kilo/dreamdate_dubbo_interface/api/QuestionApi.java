package cn.kilo.dreamdate_dubbo_interface.api;

import cn.kilo.dreamdate_model.pojo.Question;

public interface QuestionApi {

    Question findByUserId(Long userId);


    void save(Question question);

    void update(Question question);
}