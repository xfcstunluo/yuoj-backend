package com.yupi.yuoj.model.vo;

import cn.hutool.json.JSONUtil;

import com.yupi.yuoj.model.entity.QuestionSubmit;
import com.yupi.yuoj.judge.codesandbox.model.JudgeInfo;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * 题目提交封装类
 * @TableName question
 */

@Data
public class QuestionSubmitVO implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 编程语言
     */
    private String language;

    /**
     * 用户代码
     */
    private String code;

    /**
     * 判题信息
     */
    private JudgeInfo judgeInfo;

    /**
     * 判题状态（0-待判题，1-判题中，2-成功，3-失败）
     */
    private Integer status;

    /**
     * 题目 id
     */
    private Long questionId;

    /**
     * 创建用户 id
     */
    private Long userId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 提交用户信息
     */
    private UserVO userVO;

    /**
     * 对应题目信息
     */
    private QuestionVO questionVO;


    /**
     * 包装类转对象
     *
     * @param questionSubmitVO
     * @return
     */
    public static QuestionSubmit voToObj(QuestionSubmitVO questionSubmitVO) {
        if (questionSubmitVO == null) {
            return null;
        }
        QuestionSubmit questionSubmit = new QuestionSubmit();
        BeanUtils.copyProperties(questionSubmitVO, questionSubmit);
        JudgeInfo judgeINfoObj=questionSubmitVO.getJudgeInfo();
        if (judgeINfoObj != null) {
            questionSubmit.setJudgeInfo(JSONUtil.toJsonStr(judgeINfoObj));
        }
        return questionSubmit;
    }

    /**
     * 对象转包装类
     *
     * @param questionSubmit
     * @return
     */
    public static QuestionSubmitVO objToVo(QuestionSubmit questionSubmit) {
        if (questionSubmit == null) {
            return null;
        }
        QuestionSubmitVO questionSubmitVO = new QuestionSubmitVO();
        //①把questionSubmit拷贝给questionSubmitVO
        BeanUtils.copyProperties(questionSubmit, questionSubmitVO);
        //②把获取到的JudgeInfo转化为json
        String judgeInfoStr = questionSubmit.getJudgeInfo();
        //③转化为对象
        questionSubmitVO.setJudgeInfo(JSONUtil.toBean(judgeInfoStr,JudgeInfo.class));
        return questionSubmitVO;
    }

    private static final long serialVersionUID = 1L;
}