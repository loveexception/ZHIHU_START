package com.maodajun.zhihu.bean;

import lombok.Data;
import org.nutz.dao.entity.annotation.*;
import org.nutz.lang.Lang;

@Table("t_user_end")
@Data
public class User {


    @Name
    private String url_token;
    @Column
    private String name;


    @Column
    private String is_active;
    @Column
    private String gender;
    @Column
    private String is_advertiser;
    @Column
    private String rename_days;
    @Column
    private String message_thread_token;
    @Column
    private String follower_count;
    @Column
    private String following_count;
    @Column
    private String mutual_followees_count;
    @Column
    private String answer_count;
    @Column
    private String question_count;
    @Column
    private String commercial_question_count;
    @Column
    private String articles_count;
    @Column
    private String favorite_count;
    @Column
    private String favorited_count;
    @Column
    private String pins_count;
    @Column
    private String logs_count;
    @Column
    private String voteup_count;
    @Column
    private String thanked_count;
    @Column
    private String hosted_live_count;
    @Column
    private String participated_live_count;
    @Column
    private String marked_answers_count;
    @Column
    private String marked_answers_text;
    @Column
    private String following_columns_count;
    @Column
    private String following_topic_count;
    @Column
    private String following_question_count;
    @Column
    private String following_favlists_count;
    @Column
    private String vote_to_count;
    @Column
    private String vote_from_count;
    @Column
    private String thank_to_count;
    @Column
    private String thank_from_count;

    @Column
    private  String status = "0";

    public int hello() {
        return Lang.str2number(getFollowing_topic_count()).intValue()+
                Lang.str2number(getQuestion_count()).intValue()+
                Lang.str2number(getAnswer_count()).intValue()+
                Lang.str2number(getCommercial_question_count()).intValue()+
                Lang.str2number(getFollowing_topic_count()).intValue()+
                Lang.str2number(getFavorite_count()).intValue()+
                Lang.str2number(getArticles_count()).intValue()+
                Lang.str2number(getFollowing_columns_count()).intValue()+
                Lang.str2number(getFollowing_favlists_count()).intValue()+
                Lang.str2number(getFollowing_count()).intValue()+
                Lang.str2number(getMarked_answers_count()).intValue();
//        TOPIC_FOLLOW
//                MEMBER_VOTEUP_ARTICLE
//        QUESTION_FOLLOW
//                ANSWER_VOTE_UP
//        MEMBER_FOLLOW_COLUMN
//                MEMBER_FOLLOW_ROUNDTABLE
//        QUESTION_FOLLOW
//                MEMBER_CREATE_ARTICLE
//        MEMBER_FOLLOW_COLLECTION
//                QUESTION_CREATE
//        ANSWER_CREATE
//                ANSWER_VOTE_UP
//        ANSWER_CREATE
//                QUESTION_CREATE

    }
}
