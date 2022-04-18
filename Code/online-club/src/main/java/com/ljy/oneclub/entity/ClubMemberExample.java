package com.ljy.oneclub.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClubMemberExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ClubMemberExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andClubIdIsNull() {
            addCriterion("club_id is null");
            return (Criteria) this;
        }

        public Criteria andClubIdIsNotNull() {
            addCriterion("club_id is not null");
            return (Criteria) this;
        }

        public Criteria andClubIdEqualTo(Integer value) {
            addCriterion("club_id =", value, "clubId");
            return (Criteria) this;
        }

        public Criteria andClubIdNotEqualTo(Integer value) {
            addCriterion("club_id <>", value, "clubId");
            return (Criteria) this;
        }

        public Criteria andClubIdGreaterThan(Integer value) {
            addCriterion("club_id >", value, "clubId");
            return (Criteria) this;
        }

        public Criteria andClubIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("club_id >=", value, "clubId");
            return (Criteria) this;
        }

        public Criteria andClubIdLessThan(Integer value) {
            addCriterion("club_id <", value, "clubId");
            return (Criteria) this;
        }

        public Criteria andClubIdLessThanOrEqualTo(Integer value) {
            addCriterion("club_id <=", value, "clubId");
            return (Criteria) this;
        }

        public Criteria andClubIdIn(List<Integer> values) {
            addCriterion("club_id in", values, "clubId");
            return (Criteria) this;
        }

        public Criteria andClubIdNotIn(List<Integer> values) {
            addCriterion("club_id not in", values, "clubId");
            return (Criteria) this;
        }

        public Criteria andClubIdBetween(Integer value1, Integer value2) {
            addCriterion("club_id between", value1, value2, "clubId");
            return (Criteria) this;
        }

        public Criteria andClubIdNotBetween(Integer value1, Integer value2) {
            addCriterion("club_id not between", value1, value2, "clubId");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andMemNameIsNull() {
            addCriterion("mem_name is null");
            return (Criteria) this;
        }

        public Criteria andMemNameIsNotNull() {
            addCriterion("mem_name is not null");
            return (Criteria) this;
        }

        public Criteria andMemNameEqualTo(String value) {
            addCriterion("mem_name =", value, "memName");
            return (Criteria) this;
        }

        public Criteria andMemNameNotEqualTo(String value) {
            addCriterion("mem_name <>", value, "memName");
            return (Criteria) this;
        }

        public Criteria andMemNameGreaterThan(String value) {
            addCriterion("mem_name >", value, "memName");
            return (Criteria) this;
        }

        public Criteria andMemNameGreaterThanOrEqualTo(String value) {
            addCriterion("mem_name >=", value, "memName");
            return (Criteria) this;
        }

        public Criteria andMemNameLessThan(String value) {
            addCriterion("mem_name <", value, "memName");
            return (Criteria) this;
        }

        public Criteria andMemNameLessThanOrEqualTo(String value) {
            addCriterion("mem_name <=", value, "memName");
            return (Criteria) this;
        }

        public Criteria andMemNameLike(String value) {
            addCriterion("mem_name like", value, "memName");
            return (Criteria) this;
        }

        public Criteria andMemNameNotLike(String value) {
            addCriterion("mem_name not like", value, "memName");
            return (Criteria) this;
        }

        public Criteria andMemNameIn(List<String> values) {
            addCriterion("mem_name in", values, "memName");
            return (Criteria) this;
        }

        public Criteria andMemNameNotIn(List<String> values) {
            addCriterion("mem_name not in", values, "memName");
            return (Criteria) this;
        }

        public Criteria andMemNameBetween(String value1, String value2) {
            addCriterion("mem_name between", value1, value2, "memName");
            return (Criteria) this;
        }

        public Criteria andMemNameNotBetween(String value1, String value2) {
            addCriterion("mem_name not between", value1, value2, "memName");
            return (Criteria) this;
        }

        public Criteria andGenderIsNull() {
            addCriterion("gender is null");
            return (Criteria) this;
        }

        public Criteria andGenderIsNotNull() {
            addCriterion("gender is not null");
            return (Criteria) this;
        }

        public Criteria andGenderEqualTo(String value) {
            addCriterion("gender =", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotEqualTo(String value) {
            addCriterion("gender <>", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderGreaterThan(String value) {
            addCriterion("gender >", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderGreaterThanOrEqualTo(String value) {
            addCriterion("gender >=", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderLessThan(String value) {
            addCriterion("gender <", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderLessThanOrEqualTo(String value) {
            addCriterion("gender <=", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderLike(String value) {
            addCriterion("gender like", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotLike(String value) {
            addCriterion("gender not like", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderIn(List<String> values) {
            addCriterion("gender in", values, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotIn(List<String> values) {
            addCriterion("gender not in", values, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderBetween(String value1, String value2) {
            addCriterion("gender between", value1, value2, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotBetween(String value1, String value2) {
            addCriterion("gender not between", value1, value2, "gender");
            return (Criteria) this;
        }

        public Criteria andMemTelNumIsNull() {
            addCriterion("mem_tel_num is null");
            return (Criteria) this;
        }

        public Criteria andMemTelNumIsNotNull() {
            addCriterion("mem_tel_num is not null");
            return (Criteria) this;
        }

        public Criteria andMemTelNumEqualTo(String value) {
            addCriterion("mem_tel_num =", value, "memTelNum");
            return (Criteria) this;
        }

        public Criteria andMemTelNumNotEqualTo(String value) {
            addCriterion("mem_tel_num <>", value, "memTelNum");
            return (Criteria) this;
        }

        public Criteria andMemTelNumGreaterThan(String value) {
            addCriterion("mem_tel_num >", value, "memTelNum");
            return (Criteria) this;
        }

        public Criteria andMemTelNumGreaterThanOrEqualTo(String value) {
            addCriterion("mem_tel_num >=", value, "memTelNum");
            return (Criteria) this;
        }

        public Criteria andMemTelNumLessThan(String value) {
            addCriterion("mem_tel_num <", value, "memTelNum");
            return (Criteria) this;
        }

        public Criteria andMemTelNumLessThanOrEqualTo(String value) {
            addCriterion("mem_tel_num <=", value, "memTelNum");
            return (Criteria) this;
        }

        public Criteria andMemTelNumLike(String value) {
            addCriterion("mem_tel_num like", value, "memTelNum");
            return (Criteria) this;
        }

        public Criteria andMemTelNumNotLike(String value) {
            addCriterion("mem_tel_num not like", value, "memTelNum");
            return (Criteria) this;
        }

        public Criteria andMemTelNumIn(List<String> values) {
            addCriterion("mem_tel_num in", values, "memTelNum");
            return (Criteria) this;
        }

        public Criteria andMemTelNumNotIn(List<String> values) {
            addCriterion("mem_tel_num not in", values, "memTelNum");
            return (Criteria) this;
        }

        public Criteria andMemTelNumBetween(String value1, String value2) {
            addCriterion("mem_tel_num between", value1, value2, "memTelNum");
            return (Criteria) this;
        }

        public Criteria andMemTelNumNotBetween(String value1, String value2) {
            addCriterion("mem_tel_num not between", value1, value2, "memTelNum");
            return (Criteria) this;
        }

        public Criteria andMemDetailInfoIsNull() {
            addCriterion("mem_detail_info is null");
            return (Criteria) this;
        }

        public Criteria andMemDetailInfoIsNotNull() {
            addCriterion("mem_detail_info is not null");
            return (Criteria) this;
        }

        public Criteria andMemDetailInfoEqualTo(String value) {
            addCriterion("mem_detail_info =", value, "memDetailInfo");
            return (Criteria) this;
        }

        public Criteria andMemDetailInfoNotEqualTo(String value) {
            addCriterion("mem_detail_info <>", value, "memDetailInfo");
            return (Criteria) this;
        }

        public Criteria andMemDetailInfoGreaterThan(String value) {
            addCriterion("mem_detail_info >", value, "memDetailInfo");
            return (Criteria) this;
        }

        public Criteria andMemDetailInfoGreaterThanOrEqualTo(String value) {
            addCriterion("mem_detail_info >=", value, "memDetailInfo");
            return (Criteria) this;
        }

        public Criteria andMemDetailInfoLessThan(String value) {
            addCriterion("mem_detail_info <", value, "memDetailInfo");
            return (Criteria) this;
        }

        public Criteria andMemDetailInfoLessThanOrEqualTo(String value) {
            addCriterion("mem_detail_info <=", value, "memDetailInfo");
            return (Criteria) this;
        }

        public Criteria andMemDetailInfoLike(String value) {
            addCriterion("mem_detail_info like", value, "memDetailInfo");
            return (Criteria) this;
        }

        public Criteria andMemDetailInfoNotLike(String value) {
            addCriterion("mem_detail_info not like", value, "memDetailInfo");
            return (Criteria) this;
        }

        public Criteria andMemDetailInfoIn(List<String> values) {
            addCriterion("mem_detail_info in", values, "memDetailInfo");
            return (Criteria) this;
        }

        public Criteria andMemDetailInfoNotIn(List<String> values) {
            addCriterion("mem_detail_info not in", values, "memDetailInfo");
            return (Criteria) this;
        }

        public Criteria andMemDetailInfoBetween(String value1, String value2) {
            addCriterion("mem_detail_info between", value1, value2, "memDetailInfo");
            return (Criteria) this;
        }

        public Criteria andMemDetailInfoNotBetween(String value1, String value2) {
            addCriterion("mem_detail_info not between", value1, value2, "memDetailInfo");
            return (Criteria) this;
        }

        public Criteria andMemJoinTimeIsNull() {
            addCriterion("mem_join_time is null");
            return (Criteria) this;
        }

        public Criteria andMemJoinTimeIsNotNull() {
            addCriterion("mem_join_time is not null");
            return (Criteria) this;
        }

        public Criteria andMemJoinTimeEqualTo(Date value) {
            addCriterion("mem_join_time =", value, "memJoinTime");
            return (Criteria) this;
        }

        public Criteria andMemJoinTimeNotEqualTo(Date value) {
            addCriterion("mem_join_time <>", value, "memJoinTime");
            return (Criteria) this;
        }

        public Criteria andMemJoinTimeGreaterThan(Date value) {
            addCriterion("mem_join_time >", value, "memJoinTime");
            return (Criteria) this;
        }

        public Criteria andMemJoinTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("mem_join_time >=", value, "memJoinTime");
            return (Criteria) this;
        }

        public Criteria andMemJoinTimeLessThan(Date value) {
            addCriterion("mem_join_time <", value, "memJoinTime");
            return (Criteria) this;
        }

        public Criteria andMemJoinTimeLessThanOrEqualTo(Date value) {
            addCriterion("mem_join_time <=", value, "memJoinTime");
            return (Criteria) this;
        }

        public Criteria andMemJoinTimeIn(List<Date> values) {
            addCriterion("mem_join_time in", values, "memJoinTime");
            return (Criteria) this;
        }

        public Criteria andMemJoinTimeNotIn(List<Date> values) {
            addCriterion("mem_join_time not in", values, "memJoinTime");
            return (Criteria) this;
        }

        public Criteria andMemJoinTimeBetween(Date value1, Date value2) {
            addCriterion("mem_join_time between", value1, value2, "memJoinTime");
            return (Criteria) this;
        }

        public Criteria andMemJoinTimeNotBetween(Date value1, Date value2) {
            addCriterion("mem_join_time not between", value1, value2, "memJoinTime");
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