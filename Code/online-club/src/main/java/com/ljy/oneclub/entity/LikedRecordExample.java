package com.ljy.oneclub.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LikedRecordExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public LikedRecordExample() {
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

        public Criteria andLikeIdIsNull() {
            addCriterion("like_id is null");
            return (Criteria) this;
        }

        public Criteria andLikeIdIsNotNull() {
            addCriterion("like_id is not null");
            return (Criteria) this;
        }

        public Criteria andLikeIdEqualTo(Integer value) {
            addCriterion("like_id =", value, "likeId");
            return (Criteria) this;
        }

        public Criteria andLikeIdNotEqualTo(Integer value) {
            addCriterion("like_id <>", value, "likeId");
            return (Criteria) this;
        }

        public Criteria andLikeIdGreaterThan(Integer value) {
            addCriterion("like_id >", value, "likeId");
            return (Criteria) this;
        }

        public Criteria andLikeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("like_id >=", value, "likeId");
            return (Criteria) this;
        }

        public Criteria andLikeIdLessThan(Integer value) {
            addCriterion("like_id <", value, "likeId");
            return (Criteria) this;
        }

        public Criteria andLikeIdLessThanOrEqualTo(Integer value) {
            addCriterion("like_id <=", value, "likeId");
            return (Criteria) this;
        }

        public Criteria andLikeIdIn(List<Integer> values) {
            addCriterion("like_id in", values, "likeId");
            return (Criteria) this;
        }

        public Criteria andLikeIdNotIn(List<Integer> values) {
            addCriterion("like_id not in", values, "likeId");
            return (Criteria) this;
        }

        public Criteria andLikeIdBetween(Integer value1, Integer value2) {
            addCriterion("like_id between", value1, value2, "likeId");
            return (Criteria) this;
        }

        public Criteria andLikeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("like_id not between", value1, value2, "likeId");
            return (Criteria) this;
        }

        public Criteria andUIdIsNull() {
            addCriterion("u_id is null");
            return (Criteria) this;
        }

        public Criteria andUIdIsNotNull() {
            addCriterion("u_id is not null");
            return (Criteria) this;
        }

        public Criteria andUIdEqualTo(Integer value) {
            addCriterion("u_id =", value, "uId");
            return (Criteria) this;
        }

        public Criteria andUIdNotEqualTo(Integer value) {
            addCriterion("u_id <>", value, "uId");
            return (Criteria) this;
        }

        public Criteria andUIdGreaterThan(Integer value) {
            addCriterion("u_id >", value, "uId");
            return (Criteria) this;
        }

        public Criteria andUIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("u_id >=", value, "uId");
            return (Criteria) this;
        }

        public Criteria andUIdLessThan(Integer value) {
            addCriterion("u_id <", value, "uId");
            return (Criteria) this;
        }

        public Criteria andUIdLessThanOrEqualTo(Integer value) {
            addCriterion("u_id <=", value, "uId");
            return (Criteria) this;
        }

        public Criteria andUIdIn(List<Integer> values) {
            addCriterion("u_id in", values, "uId");
            return (Criteria) this;
        }

        public Criteria andUIdNotIn(List<Integer> values) {
            addCriterion("u_id not in", values, "uId");
            return (Criteria) this;
        }

        public Criteria andUIdBetween(Integer value1, Integer value2) {
            addCriterion("u_id between", value1, value2, "uId");
            return (Criteria) this;
        }

        public Criteria andUIdNotBetween(Integer value1, Integer value2) {
            addCriterion("u_id not between", value1, value2, "uId");
            return (Criteria) this;
        }

        public Criteria andLikeTypeIsNull() {
            addCriterion("like_type is null");
            return (Criteria) this;
        }

        public Criteria andLikeTypeIsNotNull() {
            addCriterion("like_type is not null");
            return (Criteria) this;
        }

        public Criteria andLikeTypeEqualTo(Integer value) {
            addCriterion("like_type =", value, "likeType");
            return (Criteria) this;
        }

        public Criteria andLikeTypeNotEqualTo(Integer value) {
            addCriterion("like_type <>", value, "likeType");
            return (Criteria) this;
        }

        public Criteria andLikeTypeGreaterThan(Integer value) {
            addCriterion("like_type >", value, "likeType");
            return (Criteria) this;
        }

        public Criteria andLikeTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("like_type >=", value, "likeType");
            return (Criteria) this;
        }

        public Criteria andLikeTypeLessThan(Integer value) {
            addCriterion("like_type <", value, "likeType");
            return (Criteria) this;
        }

        public Criteria andLikeTypeLessThanOrEqualTo(Integer value) {
            addCriterion("like_type <=", value, "likeType");
            return (Criteria) this;
        }

        public Criteria andLikeTypeIn(List<Integer> values) {
            addCriterion("like_type in", values, "likeType");
            return (Criteria) this;
        }

        public Criteria andLikeTypeNotIn(List<Integer> values) {
            addCriterion("like_type not in", values, "likeType");
            return (Criteria) this;
        }

        public Criteria andLikeTypeBetween(Integer value1, Integer value2) {
            addCriterion("like_type between", value1, value2, "likeType");
            return (Criteria) this;
        }

        public Criteria andLikeTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("like_type not between", value1, value2, "likeType");
            return (Criteria) this;
        }

        public Criteria andLikeActiveIdIsNull() {
            addCriterion("like_active_id is null");
            return (Criteria) this;
        }

        public Criteria andLikeActiveIdIsNotNull() {
            addCriterion("like_active_id is not null");
            return (Criteria) this;
        }

        public Criteria andLikeActiveIdEqualTo(Integer value) {
            addCriterion("like_active_id =", value, "likeActiveId");
            return (Criteria) this;
        }

        public Criteria andLikeActiveIdNotEqualTo(Integer value) {
            addCriterion("like_active_id <>", value, "likeActiveId");
            return (Criteria) this;
        }

        public Criteria andLikeActiveIdGreaterThan(Integer value) {
            addCriterion("like_active_id >", value, "likeActiveId");
            return (Criteria) this;
        }

        public Criteria andLikeActiveIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("like_active_id >=", value, "likeActiveId");
            return (Criteria) this;
        }

        public Criteria andLikeActiveIdLessThan(Integer value) {
            addCriterion("like_active_id <", value, "likeActiveId");
            return (Criteria) this;
        }

        public Criteria andLikeActiveIdLessThanOrEqualTo(Integer value) {
            addCriterion("like_active_id <=", value, "likeActiveId");
            return (Criteria) this;
        }

        public Criteria andLikeActiveIdIn(List<Integer> values) {
            addCriterion("like_active_id in", values, "likeActiveId");
            return (Criteria) this;
        }

        public Criteria andLikeActiveIdNotIn(List<Integer> values) {
            addCriterion("like_active_id not in", values, "likeActiveId");
            return (Criteria) this;
        }

        public Criteria andLikeActiveIdBetween(Integer value1, Integer value2) {
            addCriterion("like_active_id between", value1, value2, "likeActiveId");
            return (Criteria) this;
        }

        public Criteria andLikeActiveIdNotBetween(Integer value1, Integer value2) {
            addCriterion("like_active_id not between", value1, value2, "likeActiveId");
            return (Criteria) this;
        }

        public Criteria andLikeTimeIsNull() {
            addCriterion("like_time is null");
            return (Criteria) this;
        }

        public Criteria andLikeTimeIsNotNull() {
            addCriterion("like_time is not null");
            return (Criteria) this;
        }

        public Criteria andLikeTimeEqualTo(Date value) {
            addCriterion("like_time =", value, "likeTime");
            return (Criteria) this;
        }

        public Criteria andLikeTimeNotEqualTo(Date value) {
            addCriterion("like_time <>", value, "likeTime");
            return (Criteria) this;
        }

        public Criteria andLikeTimeGreaterThan(Date value) {
            addCriterion("like_time >", value, "likeTime");
            return (Criteria) this;
        }

        public Criteria andLikeTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("like_time >=", value, "likeTime");
            return (Criteria) this;
        }

        public Criteria andLikeTimeLessThan(Date value) {
            addCriterion("like_time <", value, "likeTime");
            return (Criteria) this;
        }

        public Criteria andLikeTimeLessThanOrEqualTo(Date value) {
            addCriterion("like_time <=", value, "likeTime");
            return (Criteria) this;
        }

        public Criteria andLikeTimeIn(List<Date> values) {
            addCriterion("like_time in", values, "likeTime");
            return (Criteria) this;
        }

        public Criteria andLikeTimeNotIn(List<Date> values) {
            addCriterion("like_time not in", values, "likeTime");
            return (Criteria) this;
        }

        public Criteria andLikeTimeBetween(Date value1, Date value2) {
            addCriterion("like_time between", value1, value2, "likeTime");
            return (Criteria) this;
        }

        public Criteria andLikeTimeNotBetween(Date value1, Date value2) {
            addCriterion("like_time not between", value1, value2, "likeTime");
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