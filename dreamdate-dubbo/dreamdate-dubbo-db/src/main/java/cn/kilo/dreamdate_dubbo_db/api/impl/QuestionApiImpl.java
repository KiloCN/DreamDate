package cn.kilo.dreamdate_dubbo_db.api.impl;

import cn.kilo.dreamdate_dubbo_db.mapper.QuestionMapper;
import cn.kilo.dreamdate_dubbo_interface.api.QuestionApi;
import cn.kilo.dreamdate_model.pojo.Question;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@DubboService
public class QuestionApiImpl implements QuestionApi {

    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public Question findByUserId(Long userId) {
        QueryWrapper<Question> qw = new QueryWrapper<>();
        qw.eq("user_id",userId);
        return questionMapper.selectOne(qw);
    }

    @Override
    public void save(Question question) {
        questionMapper.insert(question);
    }

    @Override
    public void update(Question question) {
        questionMapper.updateById(question);
    }
}