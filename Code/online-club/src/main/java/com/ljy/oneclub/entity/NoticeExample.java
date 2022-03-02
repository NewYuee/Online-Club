package com.ljy.oneclub.entity;

import java.util.ArrayList;
import java.util.List;

public class NoticeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public NoticeExample() {
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

        public Criteria andNoticeIdIsNull() {
            addCriterion("notice_id is null");
            return (Criteria) this;
        }

        public Criteria andNoticeIdIsNotNull() {
            addCriterion("notice_id is not null");
            return (Criteria) this;
        }

        public Criteria andNoticeIdEqualTo(Integer value) {
            addCriterion("notice_id =", value, "noticeId");
            return (Criteria) this;
        }

        public Criteria andNoticeIdNotEqualTo(Integer value) {
            addCriterion("notice_id <>", value, "noticeId");
            return (Criteria) this;
        }

        public Criteria andNoticeIdGreaterThan(Integer value) {
            addCriterion("notice_id >", value, "noticeId");
            return (Criteria) this;
        }

        public Criteria andNoticeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("notice_id >=", value, "noticeId");
            return (Criteria) this;
        }

        public Criteria andNoticeIdLessThan(Integer value) {
            addCriterion("notice_id <", value, "noticeId");
            return (Criteria) this;
        }

        public Criteria andNoticeIdLessThanOrEqualTo(Integer value) {
            addCriterion("notice_id <=", value, "noticeId");
            return (Criteria) this;
        }

        public Criteria andNoticeIdIn(List<Integer> values) {
            addCriterion("notice_id in", values, "noticeId");
            return (Criteria) this;
        }

        public Criteria andNoticeIdNotIn(List<Integer> values) {
            addCriterion("notice_id not in", values, "noticeId");
            return (Criteria) this;
        }

        public Criteria andNoticeIdBetween(Integer value1, Integer value2) {
            addCriterion("notice_id between", value1, value2, "noticeId");
            return (Criteria) this;
        }

        public Criteria andNoticeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("notice_id not between", value1, value2, "noticeId");
            return (Criteria) this;
        }

        public Criteria andNoticeToUserIdIsNull() {
            addCriterion("notice_to_user_id is null");
            return (Criteria) this;
        }

        public Criteria andNoticeToUserIdIsNotNull() {
            addCriterion("notice_to_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andNoticeToUserIdEqualTo(Integer value) {
            addCriterion("notice_to_user_id =", value, "noticeToUserId");
            return (Criteria) this;
        }

        public Criteria andNoticeToUserIdNotEqualTo(Integer value) {
            addCriterion("notice_to_user_id <>", value, "noticeToUserId");
            return (Criteria) this;
        }

        public Criteria andNoticeToUserIdGreaterThan(Integer value) {
            addCriterion("notice_to_user_id >", value, "noticeToUserId");
            return (Criteria) this;
        }

        public Criteria andNoticeToUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("notice_to_user_id >=", value, "noticeToUserId");
            return (Criteria) this;
        }

        public Criteria andNoticeToUserIdLessThan(Integer value) {
            addCriterion("notice_to_user_id <", value, "noticeToUserId");
            return (Criteria) this;
        }

        public Criteria andNoticeToUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("notice_to_user_id <=", value, "noticeToUserId");
            return (Criteria) this;
        }

        public Criteria andNoticeToUserIdIn(List<Integer> values) {
            addCriterion("notice_to_user_id in", values, "noticeToUserId");
            return (Criteria) this;
        }

        public Criteria andNoticeToUserIdNotIn(List<Integer> values) {
            addCriterion("notice_to_user_id not in", values, "noticeToUserId");
            return (Criteria) this;
        }

        public Criteria andNoticeToUserIdBetween(Integer value1, Integer value2) {
            addCriterion("notice_to_user_id between", value1, value2, "noticeToUserId");
            return (Criteria) this;
        }

        public Criteria andNoticeToUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("notice_to_user_id not between", value1, value2, "noticeToUserId");
            return (Criteria) this;
        }

        public Criteria andNoticeTypeIsNull() {
            addCriterion("notice_type is null");
            return (Criteria) this;
        }

        public Criteria andNoticeTypeIsNotNull() {
            addCriterion("notice_type is not null");
            return (Criteria) this;
        }

        public Criteria andNoticeTypeEqualTo(String value) {
            addCriterion("notice_type =", value, "noticeType");
            return (Criteria) this;
        }

        public Criteria andNoticeTypeNotEqualTo(String value) {
            addCriterion("notice_type <>", value, "noticeType");
            return (Criteria) this;
        }

        public Criteria andNoticeTypeGreaterThan(String value) {
            addCriterion("notice_type >", value, "noticeType");
            return (Criteria) this;
        }

        public Criteria andNoticeTypeGreaterThanOrEqualTo(String value) {
            addCriterion("notice_type >=", value, "noticeType");
            return (Criteria) this;
        }

        public Criteria andNoticeTypeLessThan(String value) {
            addCriterion("notice_type <", value, "noticeType");
            return (Criteria) this;
        }

        public Criteria andNoticeTypeLessThanOrEqualTo(String value) {
            addCriterion("notice_type <=", value, "noticeType");
            return (Criteria) this;
        }

        public Criteria andNoticeTypeLike(String value) {
            addCriterion("notice_type like", value, "noticeType");
            return (Criteria) this;
        }

        public Criteria andNoticeTypeNotLike(String value) {
            addCriterion("notice_type not like", value, "noticeType");
            return (Criteria) this;
        }

        public Criteria andNoticeTypeIn(List<String> values) {
            addCriterion("notice_type in", values, "noticeType");
            return (Criteria) this;
        }

        public Criteria andNoticeTypeNotIn(List<String> values) {
            addCriterion("notice_type not in", values, "noticeType");
            return (Criteria) this;
        }

        public Criteria andNoticeTypeBetween(String value1, String value2) {
            addCriterion("notice_type between", value1, value2, "noticeType");
            return (Criteria) this;
        }

        public Criteria andNoticeTypeNotBetween(String value1, String value2) {
            addCriterion("notice_type not between", value1, value2, "noticeType");
            return (Criteria) this;
        }

        public Criteria andNoticeStatusIsNull() {
            addCriterion("notice_status is null");
            return (Criteria) this;
        }

        public Criteria andNoticeStatusIsNotNull() {
            addCriterion("notice_status is not null");
            return (Criteria) this;
        }

        public Criteria andNoticeStatusEqualTo(String value) {
            addCriterion("notice_status =", value, "noticeStatus");
            return (Criteria) this;
        }

        public Criteria andNoticeStatusNotEqualTo(String value) {
            addCriterion("notice_status <>", value, "noticeStatus");
            return (Criteria) this;
        }

        public Criteria andNoticeStatusGreaterThan(String value) {
            addCriterion("notice_status >", value, "noticeStatus");
            return (Criteria) this;
        }

        public Criteria andNoticeStatusGreaterThanOrEqualTo(String value) {
            addCriterion("notice_status >=", value, "noticeStatus");
            return (Criteria) this;
        }

        public Criteria andNoticeStatusLessThan(String value) {
            addCriterion("notice_status <", value, "noticeStatus");
            return (Criteria) this;
        }

        public Criteria andNoticeStatusLessThanOrEqualTo(String value) {
            addCriterion("notice_status <=", value, "noticeStatus");
            return (Criteria) this;
        }

        public Criteria andNoticeStatusLike(String value) {
            addCriterion("notice_status like", value, "noticeStatus");
            return (Criteria) this;
        }

        public Criteria andNoticeStatusNotLike(String value) {
            addCriterion("notice_status not like", value, "noticeStatus");
            return (Criteria) this;
        }

        public Criteria andNoticeStatusIn(List<String> values) {
            addCriterion("notice_status in", values, "noticeStatus");
            return (Criteria) this;
        }

        public Criteria andNoticeStatusNotIn(List<String> values) {
            addCriterion("notice_status not in", values, "noticeStatus");
            return (Criteria) this;
        }

        public Criteria andNoticeStatusBetween(String value1, String value2) {
            addCriterion("notice_status between", value1, value2, "noticeStatus");
            return (Criteria) this;
        }

        public Criteria andNoticeStatusNotBetween(String value1, String value2) {
            addCriterion("notice_status not between", value1, value2, "noticeStatus");
            return (Criteria) this;
        }

        public Criteria andNoticeSourceIdIsNull() {
            addCriterion("notice_source_id is null");
            return (Criteria) this;
        }

        public Criteria andNoticeSourceIdIsNotNull() {
            addCriterion("notice_source_id is not null");
            return (Criteria) this;
        }

        public Criteria andNoticeSourceIdEqualTo(String value) {
            addCriterion("notice_source_id =", value, "noticeSourceId");
            return (Criteria) this;
        }

        public Criteria andNoticeSourceIdNotEqualTo(String value) {
            addCriterion("notice_source_id <>", value, "noticeSourceId");
            return (Criteria) this;
        }

        public Criteria andNoticeSourceIdGreaterThan(String value) {
            addCriterion("notice_source_id >", value, "noticeSourceId");
            return (Criteria) this;
        }

        public Criteria andNoticeSourceIdGreaterThanOrEqualTo(String value) {
            addCriterion("notice_source_id >=", value, "noticeSourceId");
            return (Criteria) this;
        }

        public Criteria andNoticeSourceIdLessThan(String value) {
            addCriterion("notice_source_id <", value, "noticeSourceId");
            return (Criteria) this;
        }

        public Criteria andNoticeSourceIdLessThanOrEqualTo(String value) {
            addCriterion("notice_source_id <=", value, "noticeSourceId");
            return (Criteria) this;
        }

        public Criteria andNoticeSourceIdLike(String value) {
            addCriterion("notice_source_id like", value, "noticeSourceId");
            return (Criteria) this;
        }

        public Criteria andNoticeSourceIdNotLike(String value) {
            addCriterion("notice_source_id not like", value, "noticeSourceId");
            return (Criteria) this;
        }

        public Criteria andNoticeSourceIdIn(List<String> values) {
            addCriterion("notice_source_id in", values, "noticeSourceId");
            return (Criteria) this;
        }

        public Criteria andNoticeSourceIdNotIn(List<String> values) {
            addCriterion("notice_source_id not in", values, "noticeSourceId");
            return (Criteria) this;
        }

        public Criteria andNoticeSourceIdBetween(String value1, String value2) {
            addCriterion("notice_source_id between", value1, value2, "noticeSourceId");
            return (Criteria) this;
        }

        public Criteria andNoticeSourceIdNotBetween(String value1, String value2) {
            addCriterion("notice_source_id not between", value1, value2, "noticeSourceId");
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