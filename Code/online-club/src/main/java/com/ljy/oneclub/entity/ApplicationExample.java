package com.ljy.oneclub.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ApplicationExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ApplicationExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andAppIdIsNull() {
            addCriterion("app_id is null");
            return (Criteria) this;
        }

        public Criteria andAppIdIsNotNull() {
            addCriterion("app_id is not null");
            return (Criteria) this;
        }

        public Criteria andAppIdEqualTo(Integer value) {
            addCriterion("app_id =", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdNotEqualTo(Integer value) {
            addCriterion("app_id <>", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdGreaterThan(Integer value) {
            addCriterion("app_id >", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("app_id >=", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdLessThan(Integer value) {
            addCriterion("app_id <", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdLessThanOrEqualTo(Integer value) {
            addCriterion("app_id <=", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdIn(List<Integer> values) {
            addCriterion("app_id in", values, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdNotIn(List<Integer> values) {
            addCriterion("app_id not in", values, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdBetween(Integer value1, Integer value2) {
            addCriterion("app_id between", value1, value2, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdNotBetween(Integer value1, Integer value2) {
            addCriterion("app_id not between", value1, value2, "appId");
            return (Criteria) this;
        }

        public Criteria andAppTypeIsNull() {
            addCriterion("app_type is null");
            return (Criteria) this;
        }

        public Criteria andAppTypeIsNotNull() {
            addCriterion("app_type is not null");
            return (Criteria) this;
        }

        public Criteria andAppTypeEqualTo(Integer value) {
            addCriterion("app_type =", value, "appType");
            return (Criteria) this;
        }

        public Criteria andAppTypeNotEqualTo(Integer value) {
            addCriterion("app_type <>", value, "appType");
            return (Criteria) this;
        }

        public Criteria andAppTypeGreaterThan(Integer value) {
            addCriterion("app_type >", value, "appType");
            return (Criteria) this;
        }

        public Criteria andAppTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("app_type >=", value, "appType");
            return (Criteria) this;
        }

        public Criteria andAppTypeLessThan(Integer value) {
            addCriterion("app_type <", value, "appType");
            return (Criteria) this;
        }

        public Criteria andAppTypeLessThanOrEqualTo(Integer value) {
            addCriterion("app_type <=", value, "appType");
            return (Criteria) this;
        }

        public Criteria andAppTypeIn(List<Integer> values) {
            addCriterion("app_type in", values, "appType");
            return (Criteria) this;
        }

        public Criteria andAppTypeNotIn(List<Integer> values) {
            addCriterion("app_type not in", values, "appType");
            return (Criteria) this;
        }

        public Criteria andAppTypeBetween(Integer value1, Integer value2) {
            addCriterion("app_type between", value1, value2, "appType");
            return (Criteria) this;
        }

        public Criteria andAppTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("app_type not between", value1, value2, "appType");
            return (Criteria) this;
        }

        public Criteria andAppUserIdIsNull() {
            addCriterion("app_user_id is null");
            return (Criteria) this;
        }

        public Criteria andAppUserIdIsNotNull() {
            addCriterion("app_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andAppUserIdEqualTo(Integer value) {
            addCriterion("app_user_id =", value, "appUserId");
            return (Criteria) this;
        }

        public Criteria andAppUserIdNotEqualTo(Integer value) {
            addCriterion("app_user_id <>", value, "appUserId");
            return (Criteria) this;
        }

        public Criteria andAppUserIdGreaterThan(Integer value) {
            addCriterion("app_user_id >", value, "appUserId");
            return (Criteria) this;
        }

        public Criteria andAppUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("app_user_id >=", value, "appUserId");
            return (Criteria) this;
        }

        public Criteria andAppUserIdLessThan(Integer value) {
            addCriterion("app_user_id <", value, "appUserId");
            return (Criteria) this;
        }

        public Criteria andAppUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("app_user_id <=", value, "appUserId");
            return (Criteria) this;
        }

        public Criteria andAppUserIdIn(List<Integer> values) {
            addCriterion("app_user_id in", values, "appUserId");
            return (Criteria) this;
        }

        public Criteria andAppUserIdNotIn(List<Integer> values) {
            addCriterion("app_user_id not in", values, "appUserId");
            return (Criteria) this;
        }

        public Criteria andAppUserIdBetween(Integer value1, Integer value2) {
            addCriterion("app_user_id between", value1, value2, "appUserId");
            return (Criteria) this;
        }

        public Criteria andAppUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("app_user_id not between", value1, value2, "appUserId");
            return (Criteria) this;
        }

        public Criteria andAppToUserIdIsNull() {
            addCriterion("app_to_user_id is null");
            return (Criteria) this;
        }

        public Criteria andAppToUserIdIsNotNull() {
            addCriterion("app_to_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andAppToUserIdEqualTo(Integer value) {
            addCriterion("app_to_user_id =", value, "appToUserId");
            return (Criteria) this;
        }

        public Criteria andAppToUserIdNotEqualTo(Integer value) {
            addCriterion("app_to_user_id <>", value, "appToUserId");
            return (Criteria) this;
        }

        public Criteria andAppToUserIdGreaterThan(Integer value) {
            addCriterion("app_to_user_id >", value, "appToUserId");
            return (Criteria) this;
        }

        public Criteria andAppToUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("app_to_user_id >=", value, "appToUserId");
            return (Criteria) this;
        }

        public Criteria andAppToUserIdLessThan(Integer value) {
            addCriterion("app_to_user_id <", value, "appToUserId");
            return (Criteria) this;
        }

        public Criteria andAppToUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("app_to_user_id <=", value, "appToUserId");
            return (Criteria) this;
        }

        public Criteria andAppToUserIdIn(List<Integer> values) {
            addCriterion("app_to_user_id in", values, "appToUserId");
            return (Criteria) this;
        }

        public Criteria andAppToUserIdNotIn(List<Integer> values) {
            addCriterion("app_to_user_id not in", values, "appToUserId");
            return (Criteria) this;
        }

        public Criteria andAppToUserIdBetween(Integer value1, Integer value2) {
            addCriterion("app_to_user_id between", value1, value2, "appToUserId");
            return (Criteria) this;
        }

        public Criteria andAppToUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("app_to_user_id not between", value1, value2, "appToUserId");
            return (Criteria) this;
        }

        public Criteria andAppUserNameIsNull() {
            addCriterion("app_user_name is null");
            return (Criteria) this;
        }

        public Criteria andAppUserNameIsNotNull() {
            addCriterion("app_user_name is not null");
            return (Criteria) this;
        }

        public Criteria andAppUserNameEqualTo(String value) {
            addCriterion("app_user_name =", value, "appUserName");
            return (Criteria) this;
        }

        public Criteria andAppUserNameNotEqualTo(String value) {
            addCriterion("app_user_name <>", value, "appUserName");
            return (Criteria) this;
        }

        public Criteria andAppUserNameGreaterThan(String value) {
            addCriterion("app_user_name >", value, "appUserName");
            return (Criteria) this;
        }

        public Criteria andAppUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("app_user_name >=", value, "appUserName");
            return (Criteria) this;
        }

        public Criteria andAppUserNameLessThan(String value) {
            addCriterion("app_user_name <", value, "appUserName");
            return (Criteria) this;
        }

        public Criteria andAppUserNameLessThanOrEqualTo(String value) {
            addCriterion("app_user_name <=", value, "appUserName");
            return (Criteria) this;
        }

        public Criteria andAppUserNameLike(String value) {
            addCriterion("app_user_name like", value, "appUserName");
            return (Criteria) this;
        }

        public Criteria andAppUserNameNotLike(String value) {
            addCriterion("app_user_name not like", value, "appUserName");
            return (Criteria) this;
        }

        public Criteria andAppUserNameIn(List<String> values) {
            addCriterion("app_user_name in", values, "appUserName");
            return (Criteria) this;
        }

        public Criteria andAppUserNameNotIn(List<String> values) {
            addCriterion("app_user_name not in", values, "appUserName");
            return (Criteria) this;
        }

        public Criteria andAppUserNameBetween(String value1, String value2) {
            addCriterion("app_user_name between", value1, value2, "appUserName");
            return (Criteria) this;
        }

        public Criteria andAppUserNameNotBetween(String value1, String value2) {
            addCriterion("app_user_name not between", value1, value2, "appUserName");
            return (Criteria) this;
        }

        public Criteria andAppGenderIsNull() {
            addCriterion("app_gender is null");
            return (Criteria) this;
        }

        public Criteria andAppGenderIsNotNull() {
            addCriterion("app_gender is not null");
            return (Criteria) this;
        }

        public Criteria andAppGenderEqualTo(String value) {
            addCriterion("app_gender =", value, "appGender");
            return (Criteria) this;
        }

        public Criteria andAppGenderNotEqualTo(String value) {
            addCriterion("app_gender <>", value, "appGender");
            return (Criteria) this;
        }

        public Criteria andAppGenderGreaterThan(String value) {
            addCriterion("app_gender >", value, "appGender");
            return (Criteria) this;
        }

        public Criteria andAppGenderGreaterThanOrEqualTo(String value) {
            addCriterion("app_gender >=", value, "appGender");
            return (Criteria) this;
        }

        public Criteria andAppGenderLessThan(String value) {
            addCriterion("app_gender <", value, "appGender");
            return (Criteria) this;
        }

        public Criteria andAppGenderLessThanOrEqualTo(String value) {
            addCriterion("app_gender <=", value, "appGender");
            return (Criteria) this;
        }

        public Criteria andAppGenderLike(String value) {
            addCriterion("app_gender like", value, "appGender");
            return (Criteria) this;
        }

        public Criteria andAppGenderNotLike(String value) {
            addCriterion("app_gender not like", value, "appGender");
            return (Criteria) this;
        }

        public Criteria andAppGenderIn(List<String> values) {
            addCriterion("app_gender in", values, "appGender");
            return (Criteria) this;
        }

        public Criteria andAppGenderNotIn(List<String> values) {
            addCriterion("app_gender not in", values, "appGender");
            return (Criteria) this;
        }

        public Criteria andAppGenderBetween(String value1, String value2) {
            addCriterion("app_gender between", value1, value2, "appGender");
            return (Criteria) this;
        }

        public Criteria andAppGenderNotBetween(String value1, String value2) {
            addCriterion("app_gender not between", value1, value2, "appGender");
            return (Criteria) this;
        }

        public Criteria andAppUserTelNumIsNull() {
            addCriterion("app_user_tel_num is null");
            return (Criteria) this;
        }

        public Criteria andAppUserTelNumIsNotNull() {
            addCriterion("app_user_tel_num is not null");
            return (Criteria) this;
        }

        public Criteria andAppUserTelNumEqualTo(String value) {
            addCriterion("app_user_tel_num =", value, "appUserTelNum");
            return (Criteria) this;
        }

        public Criteria andAppUserTelNumNotEqualTo(String value) {
            addCriterion("app_user_tel_num <>", value, "appUserTelNum");
            return (Criteria) this;
        }

        public Criteria andAppUserTelNumGreaterThan(String value) {
            addCriterion("app_user_tel_num >", value, "appUserTelNum");
            return (Criteria) this;
        }

        public Criteria andAppUserTelNumGreaterThanOrEqualTo(String value) {
            addCriterion("app_user_tel_num >=", value, "appUserTelNum");
            return (Criteria) this;
        }

        public Criteria andAppUserTelNumLessThan(String value) {
            addCriterion("app_user_tel_num <", value, "appUserTelNum");
            return (Criteria) this;
        }

        public Criteria andAppUserTelNumLessThanOrEqualTo(String value) {
            addCriterion("app_user_tel_num <=", value, "appUserTelNum");
            return (Criteria) this;
        }

        public Criteria andAppUserTelNumLike(String value) {
            addCriterion("app_user_tel_num like", value, "appUserTelNum");
            return (Criteria) this;
        }

        public Criteria andAppUserTelNumNotLike(String value) {
            addCriterion("app_user_tel_num not like", value, "appUserTelNum");
            return (Criteria) this;
        }

        public Criteria andAppUserTelNumIn(List<String> values) {
            addCriterion("app_user_tel_num in", values, "appUserTelNum");
            return (Criteria) this;
        }

        public Criteria andAppUserTelNumNotIn(List<String> values) {
            addCriterion("app_user_tel_num not in", values, "appUserTelNum");
            return (Criteria) this;
        }

        public Criteria andAppUserTelNumBetween(String value1, String value2) {
            addCriterion("app_user_tel_num between", value1, value2, "appUserTelNum");
            return (Criteria) this;
        }

        public Criteria andAppUserTelNumNotBetween(String value1, String value2) {
            addCriterion("app_user_tel_num not between", value1, value2, "appUserTelNum");
            return (Criteria) this;
        }

        public Criteria andAppUserDetailInfoIsNull() {
            addCriterion("app_user_detail_info is null");
            return (Criteria) this;
        }

        public Criteria andAppUserDetailInfoIsNotNull() {
            addCriterion("app_user_detail_info is not null");
            return (Criteria) this;
        }

        public Criteria andAppUserDetailInfoEqualTo(String value) {
            addCriterion("app_user_detail_info =", value, "appUserDetailInfo");
            return (Criteria) this;
        }

        public Criteria andAppUserDetailInfoNotEqualTo(String value) {
            addCriterion("app_user_detail_info <>", value, "appUserDetailInfo");
            return (Criteria) this;
        }

        public Criteria andAppUserDetailInfoGreaterThan(String value) {
            addCriterion("app_user_detail_info >", value, "appUserDetailInfo");
            return (Criteria) this;
        }

        public Criteria andAppUserDetailInfoGreaterThanOrEqualTo(String value) {
            addCriterion("app_user_detail_info >=", value, "appUserDetailInfo");
            return (Criteria) this;
        }

        public Criteria andAppUserDetailInfoLessThan(String value) {
            addCriterion("app_user_detail_info <", value, "appUserDetailInfo");
            return (Criteria) this;
        }

        public Criteria andAppUserDetailInfoLessThanOrEqualTo(String value) {
            addCriterion("app_user_detail_info <=", value, "appUserDetailInfo");
            return (Criteria) this;
        }

        public Criteria andAppUserDetailInfoLike(String value) {
            addCriterion("app_user_detail_info like", value, "appUserDetailInfo");
            return (Criteria) this;
        }

        public Criteria andAppUserDetailInfoNotLike(String value) {
            addCriterion("app_user_detail_info not like", value, "appUserDetailInfo");
            return (Criteria) this;
        }

        public Criteria andAppUserDetailInfoIn(List<String> values) {
            addCriterion("app_user_detail_info in", values, "appUserDetailInfo");
            return (Criteria) this;
        }

        public Criteria andAppUserDetailInfoNotIn(List<String> values) {
            addCriterion("app_user_detail_info not in", values, "appUserDetailInfo");
            return (Criteria) this;
        }

        public Criteria andAppUserDetailInfoBetween(String value1, String value2) {
            addCriterion("app_user_detail_info between", value1, value2, "appUserDetailInfo");
            return (Criteria) this;
        }

        public Criteria andAppUserDetailInfoNotBetween(String value1, String value2) {
            addCriterion("app_user_detail_info not between", value1, value2, "appUserDetailInfo");
            return (Criteria) this;
        }

        public Criteria andAppReasonIsNull() {
            addCriterion("app_reason is null");
            return (Criteria) this;
        }

        public Criteria andAppReasonIsNotNull() {
            addCriterion("app_reason is not null");
            return (Criteria) this;
        }

        public Criteria andAppReasonEqualTo(String value) {
            addCriterion("app_reason =", value, "appReason");
            return (Criteria) this;
        }

        public Criteria andAppReasonNotEqualTo(String value) {
            addCriterion("app_reason <>", value, "appReason");
            return (Criteria) this;
        }

        public Criteria andAppReasonGreaterThan(String value) {
            addCriterion("app_reason >", value, "appReason");
            return (Criteria) this;
        }

        public Criteria andAppReasonGreaterThanOrEqualTo(String value) {
            addCriterion("app_reason >=", value, "appReason");
            return (Criteria) this;
        }

        public Criteria andAppReasonLessThan(String value) {
            addCriterion("app_reason <", value, "appReason");
            return (Criteria) this;
        }

        public Criteria andAppReasonLessThanOrEqualTo(String value) {
            addCriterion("app_reason <=", value, "appReason");
            return (Criteria) this;
        }

        public Criteria andAppReasonLike(String value) {
            addCriterion("app_reason like", value, "appReason");
            return (Criteria) this;
        }

        public Criteria andAppReasonNotLike(String value) {
            addCriterion("app_reason not like", value, "appReason");
            return (Criteria) this;
        }

        public Criteria andAppReasonIn(List<String> values) {
            addCriterion("app_reason in", values, "appReason");
            return (Criteria) this;
        }

        public Criteria andAppReasonNotIn(List<String> values) {
            addCriterion("app_reason not in", values, "appReason");
            return (Criteria) this;
        }

        public Criteria andAppReasonBetween(String value1, String value2) {
            addCriterion("app_reason between", value1, value2, "appReason");
            return (Criteria) this;
        }

        public Criteria andAppReasonNotBetween(String value1, String value2) {
            addCriterion("app_reason not between", value1, value2, "appReason");
            return (Criteria) this;
        }

        public Criteria andAppFileIsNull() {
            addCriterion("app_file is null");
            return (Criteria) this;
        }

        public Criteria andAppFileIsNotNull() {
            addCriterion("app_file is not null");
            return (Criteria) this;
        }

        public Criteria andAppFileEqualTo(String value) {
            addCriterion("app_file =", value, "appFile");
            return (Criteria) this;
        }

        public Criteria andAppFileNotEqualTo(String value) {
            addCriterion("app_file <>", value, "appFile");
            return (Criteria) this;
        }

        public Criteria andAppFileGreaterThan(String value) {
            addCriterion("app_file >", value, "appFile");
            return (Criteria) this;
        }

        public Criteria andAppFileGreaterThanOrEqualTo(String value) {
            addCriterion("app_file >=", value, "appFile");
            return (Criteria) this;
        }

        public Criteria andAppFileLessThan(String value) {
            addCriterion("app_file <", value, "appFile");
            return (Criteria) this;
        }

        public Criteria andAppFileLessThanOrEqualTo(String value) {
            addCriterion("app_file <=", value, "appFile");
            return (Criteria) this;
        }

        public Criteria andAppFileLike(String value) {
            addCriterion("app_file like", value, "appFile");
            return (Criteria) this;
        }

        public Criteria andAppFileNotLike(String value) {
            addCriterion("app_file not like", value, "appFile");
            return (Criteria) this;
        }

        public Criteria andAppFileIn(List<String> values) {
            addCriterion("app_file in", values, "appFile");
            return (Criteria) this;
        }

        public Criteria andAppFileNotIn(List<String> values) {
            addCriterion("app_file not in", values, "appFile");
            return (Criteria) this;
        }

        public Criteria andAppFileBetween(String value1, String value2) {
            addCriterion("app_file between", value1, value2, "appFile");
            return (Criteria) this;
        }

        public Criteria andAppFileNotBetween(String value1, String value2) {
            addCriterion("app_file not between", value1, value2, "appFile");
            return (Criteria) this;
        }

        public Criteria andAppStatusIsNull() {
            addCriterion("app_status is null");
            return (Criteria) this;
        }

        public Criteria andAppStatusIsNotNull() {
            addCriterion("app_status is not null");
            return (Criteria) this;
        }

        public Criteria andAppStatusEqualTo(Integer value) {
            addCriterion("app_status =", value, "appStatus");
            return (Criteria) this;
        }

        public Criteria andAppStatusNotEqualTo(Integer value) {
            addCriterion("app_status <>", value, "appStatus");
            return (Criteria) this;
        }

        public Criteria andAppStatusGreaterThan(Integer value) {
            addCriterion("app_status >", value, "appStatus");
            return (Criteria) this;
        }

        public Criteria andAppStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("app_status >=", value, "appStatus");
            return (Criteria) this;
        }

        public Criteria andAppStatusLessThan(Integer value) {
            addCriterion("app_status <", value, "appStatus");
            return (Criteria) this;
        }

        public Criteria andAppStatusLessThanOrEqualTo(Integer value) {
            addCriterion("app_status <=", value, "appStatus");
            return (Criteria) this;
        }

        public Criteria andAppStatusIn(List<Integer> values) {
            addCriterion("app_status in", values, "appStatus");
            return (Criteria) this;
        }

        public Criteria andAppStatusNotIn(List<Integer> values) {
            addCriterion("app_status not in", values, "appStatus");
            return (Criteria) this;
        }

        public Criteria andAppStatusBetween(Integer value1, Integer value2) {
            addCriterion("app_status between", value1, value2, "appStatus");
            return (Criteria) this;
        }

        public Criteria andAppStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("app_status not between", value1, value2, "appStatus");
            return (Criteria) this;
        }

        public Criteria andAppDealResultIsNull() {
            addCriterion("app_deal_result is null");
            return (Criteria) this;
        }

        public Criteria andAppDealResultIsNotNull() {
            addCriterion("app_deal_result is not null");
            return (Criteria) this;
        }

        public Criteria andAppDealResultEqualTo(String value) {
            addCriterion("app_deal_result =", value, "appDealResult");
            return (Criteria) this;
        }

        public Criteria andAppDealResultNotEqualTo(String value) {
            addCriterion("app_deal_result <>", value, "appDealResult");
            return (Criteria) this;
        }

        public Criteria andAppDealResultGreaterThan(String value) {
            addCriterion("app_deal_result >", value, "appDealResult");
            return (Criteria) this;
        }

        public Criteria andAppDealResultGreaterThanOrEqualTo(String value) {
            addCriterion("app_deal_result >=", value, "appDealResult");
            return (Criteria) this;
        }

        public Criteria andAppDealResultLessThan(String value) {
            addCriterion("app_deal_result <", value, "appDealResult");
            return (Criteria) this;
        }

        public Criteria andAppDealResultLessThanOrEqualTo(String value) {
            addCriterion("app_deal_result <=", value, "appDealResult");
            return (Criteria) this;
        }

        public Criteria andAppDealResultLike(String value) {
            addCriterion("app_deal_result like", value, "appDealResult");
            return (Criteria) this;
        }

        public Criteria andAppDealResultNotLike(String value) {
            addCriterion("app_deal_result not like", value, "appDealResult");
            return (Criteria) this;
        }

        public Criteria andAppDealResultIn(List<String> values) {
            addCriterion("app_deal_result in", values, "appDealResult");
            return (Criteria) this;
        }

        public Criteria andAppDealResultNotIn(List<String> values) {
            addCriterion("app_deal_result not in", values, "appDealResult");
            return (Criteria) this;
        }

        public Criteria andAppDealResultBetween(String value1, String value2) {
            addCriterion("app_deal_result between", value1, value2, "appDealResult");
            return (Criteria) this;
        }

        public Criteria andAppDealResultNotBetween(String value1, String value2) {
            addCriterion("app_deal_result not between", value1, value2, "appDealResult");
            return (Criteria) this;
        }

        public Criteria andAppTimeIsNull() {
            addCriterion("app_time is null");
            return (Criteria) this;
        }

        public Criteria andAppTimeIsNotNull() {
            addCriterion("app_time is not null");
            return (Criteria) this;
        }

        public Criteria andAppTimeEqualTo(Date value) {
            addCriterion("app_time =", value, "appTime");
            return (Criteria) this;
        }

        public Criteria andAppTimeNotEqualTo(Date value) {
            addCriterion("app_time <>", value, "appTime");
            return (Criteria) this;
        }

        public Criteria andAppTimeGreaterThan(Date value) {
            addCriterion("app_time >", value, "appTime");
            return (Criteria) this;
        }

        public Criteria andAppTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("app_time >=", value, "appTime");
            return (Criteria) this;
        }

        public Criteria andAppTimeLessThan(Date value) {
            addCriterion("app_time <", value, "appTime");
            return (Criteria) this;
        }

        public Criteria andAppTimeLessThanOrEqualTo(Date value) {
            addCriterion("app_time <=", value, "appTime");
            return (Criteria) this;
        }

        public Criteria andAppTimeIn(List<Date> values) {
            addCriterion("app_time in", values, "appTime");
            return (Criteria) this;
        }

        public Criteria andAppTimeNotIn(List<Date> values) {
            addCriterion("app_time not in", values, "appTime");
            return (Criteria) this;
        }

        public Criteria andAppTimeBetween(Date value1, Date value2) {
            addCriterion("app_time between", value1, value2, "appTime");
            return (Criteria) this;
        }

        public Criteria andAppTimeNotBetween(Date value1, Date value2) {
            addCriterion("app_time not between", value1, value2, "appTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}