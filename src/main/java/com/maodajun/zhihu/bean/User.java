package com.maodajun.zhihu.bean;

import lombok.Data;
import org.nutz.dao.entity.annotation.*;

@Table("t_user_start")
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

}
