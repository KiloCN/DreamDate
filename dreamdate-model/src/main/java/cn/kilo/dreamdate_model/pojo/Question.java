package cn.kilo.dreamdate_model.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_question")
public class Question extends BasePojo {

    private Long id;
    private Long userId;
    //问题内容
    private String txt;

}