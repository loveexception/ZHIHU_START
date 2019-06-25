package com.maodajun.zhihu.bean;

import lombok.Data;
import org.nutz.dao.entity.annotation.*;

@Table("t_user")
@Data
public class User {
    @Id
    private long id;
    public static String URL  = "https://www.zhihu.com/api/v4/members/" +
            "%s" +
            "?include=locations,employments,gender,educations," +
            "business,voteup_count,thanked_Count,follower_count," +
            "following_count,cover_url,following_topic_count," +
            "following_question_count,following_favlists_count," +
            "following_columns_count,answer_count,articles_count," +
            "pins_count,question_count,commercial_question_count," +
            "favorite_count,favorited_count,logs_count,marked_answers_count," +
            "marked_answers_text,message_thread_token,account_status," +
            "is_active,is_force_renamed,is_bind_sina,sina_weibo_url," +
            "sina_weibo_name,show_sina_weibo,is_blocking,is_blocked," +
            "is_following,is_followed,mutual_followees_count,vote_to_count," +
            "vote_from_count,thank_to_count,thank_from_count,thanked_count," +
            "description,hosted_live_count,participated_live_count," +
            "allow_message,industry_category,org_name,org_homepage," +
            "badge[?(type=best_answerer)].topics";
    @Name
    private String url_token;
    @Column
    private String name;
    @Column
    private String avatar_url;
    @Column
    private String avatar_url_template;
    @Column
    private boolean is_org;
    @Column
    private String type;
    @Column
    private String url;
    @Column
    private String user_type;
    @Column
    private String headline;
    @Column
    private int is_active;
    @Column
    @ColDefine(type=ColType.TEXT)
    private String description;
    @Column
    private int gender;
    @Column
    private boolean is_advertiser;

    @Column

    private String message_thread_token;
    @Column
    private boolean allow_message;
    @Column
    private boolean is_following;
    @Column
    private boolean is_followed;
    @Column
    private boolean is_blocking;
    @Column
    private boolean is_blocked;
    @Column
    private boolean is_force_renamed;
    @Column
    private long follower_count;
    @Column
    private int following_count;
    @Column
    private int mutual_followees_count;
    @Column
    private int answer_count;
    @Column
    private int question_count;
    @Column
    private int commercial_question_count;
    @Column
    private int articles_count;
    @Column
    private int favorite_count;
    @Column
    private int favorited_count;
    @Column
    private int pins_count;
    @Column
    private int logs_count;
    @Column
    private int voteup_count;
    @Column
    private int thanked_count;
    @Column
    private int hosted_live_count;
    @Column
    private int participated_live_count;
    @Column
    private int marked_answers_count;
    @Column
    private String marked_answers_text;
    @Column
    private int following_columns_count;
    @Column
    private int following_topic_count;
    @Column
    private int following_question_count;
    @Column
    private int following_favlists_count;
    @Column
    private int vote_to_count;
    @Column
    private int vote_from_count;
    @Column
    private int thank_to_count;
    @Column
    private int thank_from_count;
    @Column

    private String cover_url;
    @Column
    private boolean is_bind_sina;
    @Column
    private String sina_weibo_name;
    @Column
    private String sina_weibo_url;


}
